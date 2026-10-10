package pacman.platform;

import java.io.InputStream;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

/**
 * A MIDI clip backed by {@link Sequencer}. If no MIDI device is available the clip silently does nothing,
 * so the game keeps running without sound.
 */
public final class AudioClip {
    /** Loop forever. */
    public static final int LOOP_FOREVER = -1;

    private static final int END_OF_TRACK = 47;

    private Sequencer sequencer;
    private volatile boolean playing;

    private AudioClip() {
    }

    /** Loads a MIDI clip from a stream. Returns a silent clip if MIDI is unavailable. */
    public static AudioClip load(InputStream midi) {
        AudioClip clip = new AudioClip();
        try (InputStream in = midi) {
            Sequencer seq = MidiSystem.getSequencer();
            seq.open();
            seq.setSequence(in);
            MetaEventListener onEnd = meta -> {
                if (meta.getType() == END_OF_TRACK) {
                    clip.playing = false;
                }
            };
            seq.addMetaEventListener(onEnd);
            clip.sequencer = seq;
        } catch (Exception | LinkageError e) {
            clip.sequencer = null;
        }
        return clip;
    }

    /** Sets how many times the clip plays: {@link #LOOP_FOREVER} or a positive count. */
    public void setLoopCount(int count) {
        if (sequencer == null) {
            return;
        }
        sequencer.setLoopCount(count == LOOP_FOREVER ? Sequencer.LOOP_CONTINUOUSLY : Math.max(0, count - 1));
    }

    public void rewind() {
        if (sequencer != null) {
            sequencer.setMicrosecondPosition(0);
        }
    }

    public void start() {
        if (sequencer != null) {
            sequencer.start();
            playing = true;
        }
    }

    public void stop() {
        if (sequencer != null && sequencer.isOpen()) {
            sequencer.stop();
        }
        playing = false;
    }

    public boolean isPlaying() {
        return playing;
    }

    /** Releases the underlying MIDI device. */
    public void close() {
        if (sequencer != null) {
            sequencer.close();
            sequencer = null;
        }
        playing = false;
    }

}
