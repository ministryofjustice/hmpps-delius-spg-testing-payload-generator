#!/usr/bin/env bash

set -e

source ${HMPPS_BUILD_WORK_DIR}/ci_env_configs/dev.properties
cd ${HMPPS_BUILD_WORK_DIR}/ci-components/codepipeline

terragrunt apply ${environment_name}.plan

