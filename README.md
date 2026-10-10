# PAC-MAN (Java SE port)

A desktop Java SE port of the Namco PAC-MAN J2ME mobile game (v1.3.9), decompiled from the original `pacman.jar`
and moved off J2ME (MIDP/CLDC, JSR-82 Bluetooth) onto Swing/AWT, `javax.sound.midi` and plain files.

The game code, graphics, sounds and text belong to Namco/Bandai Namco. This port is for personal use and
preservation; don't redistribute it.

## Run

Requires a JDK 17 or newer (JRE 17+ is enough to run the jar).

    ./build.sh          # produces build/pacman.jar
    ./run.sh            # or: java -jar build/pacman.jar

## Controls

| Action              | Keys                                 | Mouse                |
|---------------------|--------------------------------------|----------------------|
| Move / menu cursor  | Arrow keys or WASD (also 2/4/6/8)    | click / drag in maze |
| Select / fire       | Enter or Space                       | click an item        |
| Left soft key       | Z or F1                              | click the bottom-left label |
| Right soft key/back | X, F2, Esc or Backspace              | click the bottom-right label |

The 360x640 portrait screen scales to the window; resize it freely.

## What changed from the phone version

* **High Scores**: the main menu has a local "HIGH SCORES" entry showing a persistent top 10. Every finished
  one-player game is recorded (name `PAC` unless a VS nickname was set). The original entry sent scores to a Namco
  server; that server no longer exists.
* **VS.GAME (2-4 players) now uses your local network instead of Bluetooth.**
  * On the host machine choose *VS.GAME > HOST GAME*; on every other machine *VS.GAME > FIND HOST*.
  * Hosts and guests find each other with a UDP broadcast on port 47236; the match runs over TCP on a
    port chosen automatically. All machines must be on the same subnet, and firewalls must allow both.
  * Two copies on one computer also work (via loopback), which is handy for testing.
* **Removed**: online score upload / authentication, the "More Games" catalogue and SMS "tell a friend"
  (all depended on servers that are gone).
* **Saves** (options, personal bests, nickname, local top 10) live in `~/.pacman-se/`. Override the folder with
  `-Dpacman.data.dir=/some/path`.
* Application settings that used to be the MIDlet manifest are in `src/main/resources/app.properties`.

## Layout

    src/main/java/pacman            game code (decompiled; see the name map below)
    src/main/java/pacman/platform   Swing/AWT/MIDI/file replacements for the J2ME APIs
    src/main/java/pacman/net        LAN discovery and connection (replaces Bluetooth)
    src/test/java/pacman/Harness    developer tool: runs the game headless, sends keys, saves screenshots

Renamed classes (original obfuscated name in brackets): `GameScreen` [g], `MenuUi` [n], `NicknameEditor` [h],
`MessageScreens` [i], `BitmapFont` [e], `Localization` [f], `SoundPlayer` [m, rewritten], `AcceptThread` [c].
Fields and methods are still named `field_N` / `method_N` (the obfuscator discarded the originals); each carries a
`// $VF: renamed from:` comment with its old name and signature.

## Status and known limits

Tested (headless with screenshots, plus a real window under Xvfb): start-up, language and sound prompts, menus,
one-player gameplay including a full game over, high-score recording and display, mouse and keyboard input, window
scaling, and a two-process LAN match (host and guest both reached live play with synchronised timers).

Not tested: audible sound (no audio device was available; with no MIDI device the game runs silently), LAN play
between two *different* computers, matches with 3-4 players, and the Help/Options screens beyond opening the menu.
The decompiled logic was only checked where it failed to compile or visibly misbehaved, so rare code paths may
still hide decompiler mistakes. Text uses the system sans-serif font where the original used the phone's font.
