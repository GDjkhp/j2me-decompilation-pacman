#!/usr/bin/env bash
# Builds if needed, then runs the game.
cd "$(dirname "$0")"
[ -f build/pacman.jar ] || ./build.sh
exec java -jar build/pacman.jar "$@"
