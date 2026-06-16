#!/usr/bin/env sh
set -eu

MVNW_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
MAVEN_VERSION=3.9.9
MAVEN_HOME="$MVNW_DIR/.mvn/apache-maven-$MAVEN_VERSION"
MAVEN_ARCHIVE="$MVNW_DIR/.mvn/apache-maven-$MAVEN_VERSION-bin.tar.gz"
MAVEN_URL="https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz"

if [ ! -x "$MAVEN_HOME/bin/mvn" ]; then
  mkdir -p "$MVNW_DIR/.mvn"
  if [ ! -f "$MAVEN_ARCHIVE" ]; then
    curl -fsSL "$MAVEN_URL" -o "$MAVEN_ARCHIVE"
  fi
  tar -xzf "$MAVEN_ARCHIVE" -C "$MVNW_DIR/.mvn"
fi

exec "$MAVEN_HOME/bin/mvn" "$@"
