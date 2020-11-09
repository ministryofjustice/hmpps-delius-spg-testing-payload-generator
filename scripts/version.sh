#!/usr/bin/env bash

set -e

timestamp() {
  date +"%Y%m%d%H%M%S"
}

rm -f version.full

if [ -z "${CODEBUILD_RESOLVED_SOURCE_VERSION}" ]; then
    postfix=`timestamp`
else
    postfix=${CODEBUILD_RESOLVED_SOURCE_VERSION:0:7}
fi

prefix=`cat VERSION`

echo "${prefix}-${postfix}" > version.full