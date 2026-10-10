#!/usr/bin/env bash
# Builds build/pacman.jar (needs a JDK, version 17 or newer).
set -euo pipefail
cd "$(dirname "$0")"
rm -rf build/classes && mkdir -p build/classes
javac --release 17 -d build/classes $(find src/main/java -name '*.java')
cp -r src/main/resources/. build/classes/
printf 'Main-Class: pacman.Pacman\n' > build/manifest.txt
jar --create --file build/pacman.jar --manifest build/manifest.txt -C build/classes .
echo "Built build/pacman.jar - run with: java -jar build/pacman.jar"
