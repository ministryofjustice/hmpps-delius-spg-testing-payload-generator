#!/usr/bin/env bash

set -e

source ${HMPPS_BUILD_WORK_DIR}/ci_env_configs/dev.properties
cd ${HMPPS_BUILD_WORK_DIR}/ci-components/codepipeline


if [ -d .terraform ]; then
    rm -rf .terraform
fi
rm -f ${environment_name}.plan
sleep 1
terragrunt fmt
terragrunt init
terragrunt refresh
terragrunt plan --out ${environment_name}.plan


set -e