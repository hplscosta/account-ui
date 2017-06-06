#!/bin/bash
set -e

echo "Using JVM configuration [ $JAVA_OPTS ]"

IMAGE_OPTS="-Dsun.misc.URLClassPath.disableJarChecking=true -Djava.security.egd=file:/dev/./urandom"
JVM="$JAVA_OPTS $IMAGE_OPTS"

java $JVM -jar $JARFILE $@

exec "$@"