package pacman;

import pacman.platform.AudioClip;

/**
 * Plays the game's MIDI sound effects and music on four logical channels. Each sound either has a fixed channel
 * or is assigned dynamically; when no channel is free, the lowest-priority playing sound is replaced.
 */
public final class SoundPlayer {
    private static final int CHANNELS = 4;

    private String[] files;
    private int[] fixedChannel;
    private int[] priority;
    private int[] loops;
    private AudioClip[] clips;
    private boolean[] resumeAfterPause;
    private int[] channelSound;
    private int[] soundsPerChannel;
    private boolean soundOn = true;
    private boolean suspended = false;
    private int loadIndex = 0;

    /**
     * Describes the sound set.
     *
     * @param files        resource path per sound, or null for an unused slot
     * @param fixedChannel channel 0-3 the sound must use, or -1 for dynamic assignment
     * @param priority     higher wins when a channel must be stolen
     * @param loops        0 = loop forever, 1 = once, n &gt; 1 = n times
     */
    public void configure(String[] files, int[] fixedChannel, int[] priority, int[] loops) {
        this.files = files;
        this.fixedChannel = fixedChannel;
        this.priority = priority;
        this.loops = loops;
        if (files.length != fixedChannel.length || files.length != priority.length || files.length != loops.length) {
            throw new IllegalArgumentException("configure: lengths of arrays do not match");
        }
        clips = new AudioClip[files.length];
        resumeAfterPause = new boolean[files.length];
        channelSound = new int[CHANNELS];
        soundsPerChannel = new int[CHANNELS];
        java.util.Arrays.fill(channelSound, -1);

        boolean hasDynamic = false;
        for (int i = fixedChannel.length - 1; i >= 0; i--) {
            if (fixedChannel[i] >= 0 && fixedChannel[i] < CHANNELS) {
                soundsPerChannel[fixedChannel[i]]++;
            } else if (fixedChannel[i] == -1) {
                hasDynamic = true;
            } else {
                files[i] = null;
            }
        }
        boolean hasUnreservedChannel = false;
        for (int c = 0; c < CHANNELS; c++) {
            if (soundsPerChannel[c] == 0) {
                hasUnreservedChannel = true;
                break;
            }
        }
        if (hasDynamic && !hasUnreservedChannel) {
            throw new IllegalArgumentException("configure: dynamic assignment not possible");
        }
    }

    /**
     * Loads the next sound in the list.
     *
     * @return 1 if a slot was processed, 0 when everything has been loaded
     */
    public int loadNext() {
        int index = loadIndex++;
        if (index >= files.length) {
            return 0;
        }
        String file = files[index];
        if (file == null) {
            return 1;
        }
        if (!file.toLowerCase().endsWith(".mid")) {
            throw new IllegalStateException("Unsupported sound type: " + file);
        }
        clips[index] = AudioClip.load(SoundPlayer.class.getResourceAsStream(file));
        if (loops != null) {
            if (loops[index] == 0) {
                clips[index].setLoopCount(AudioClip.LOOP_FOREVER);
            } else if (loops[index] > 1) {
                clips[index].setLoopCount(loops[index]);
            }
        }
        return 1;
    }

    /** Starts sound {@code index}, possibly replacing a lower-priority sound. Sounds 1 and 2 are unused. */
    public void play(int index) {
        if (index == 1 || index == 2 || index < 0 || index >= clips.length || suspended || clips[index] == null) {
            return;
        }
        int channel = fixedChannel != null ? fixedChannel[index] : -1;
        if (channel == -1) {
            int freeChannel = -1;
            int weakestBusy = -1;
            for (int c = CHANNELS - 1; c >= 0; c--) {
                if (soundsPerChannel != null && soundsPerChannel[c] != 0) {
                    continue;
                }
                if (channelSound[c] == index) {
                    channel = c;
                    break;
                }
                if (channelSound[c] == -1 || !clips[channelSound[c]].isPlaying()) {
                    freeChannel = c;
                } else if (priority == null || weakestBusy == -1
                        || priority[channelSound[c]] < priority[channelSound[weakestBusy]]) {
                    weakestBusy = c;
                }
            }
            if (channel == -1) {
                channel = freeChannel != -1 ? freeChannel : weakestBusy;
            }
        }
        int current = channelSound[channel];
        if (current == -1 || !clips[current].isPlaying() || priority[index] >= priority[current]) {
            if (current != -1 && index != current) {
                clips[current].stop();
                resumeAfterPause[current] = false;
            }
            clips[index].stop();
            clips[index].rewind();
            if (soundOn) {
                clips[index].start();
            }
            channelSound[channel] = index;
            if (loops[index] == 0) {
                resumeAfterPause[index] = true;
            }
        }
    }

    /** Turns sound on (user option); looping sounds resume. */
    public void enableSound() {
        soundOn = true;
        if (!suspended) {
            restartLooping();
        }
    }

    /** Turns sound off (user option) and silences everything playing. */
    public void disableSound() {
        soundOn = false;
        stopChannels();
    }

    /** Silences playback while the game is paused or in the background. */
    public void suspend() {
        suspended = true;
        if (channelSound != null && soundOn) {
            stopChannels();
        }
    }

    /** Ends {@link #suspend()}; looping sounds resume. */
    public void resume() {
        suspended = false;
        if (soundOn) {
            restartLooping();
        }
    }

    /** Stops all channel sounds and forgets which ones were looping. */
    public void stopAndForgetLooping() {
        stopChannels();
        if (resumeAfterPause != null) {
            java.util.Arrays.fill(resumeAfterPause, false);
        }
    }

    /** Stops every sound and clears pause state. */
    public void reset() {
        if (clips != null) {
            for (int i = clips.length - 1; i >= 0; i--) {
                if (clips[i] != null) {
                    clips[i].stop();
                    resumeAfterPause[i] = false;
                }
            }
        }
        suspended = false;
    }

    /** Whether sound {@code index} is currently playing. */
    public boolean isPlaying(int index) {
        return clips[index] != null && clips[index].isPlaying();
    }

    private void stopChannels() {
        if (channelSound == null) {
            return;
        }
        for (int c = CHANNELS - 1; c >= 0; c--) {
            if (channelSound[c] != -1) {
                clips[channelSound[c]].stop();
            }
        }
    }

    private void restartLooping() {
        if (channelSound == null) {
            return;
        }
        for (int c = CHANNELS - 1; c >= 0; c--) {
            if (channelSound[c] != -1 && resumeAfterPause[channelSound[c]]) {
                clips[channelSound[c]].start();
            }
        }
    }
}
