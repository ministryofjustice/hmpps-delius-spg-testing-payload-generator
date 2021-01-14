#!/usr/bin/env bash

$(pwd)/scripts/clone-engineering-platform-env-configs.sh


docker run -it --rm \
    -v $(pwd):/home/tools/data \
    -v ~/.aws:/home/tools/.aws \
    -e AWS_PROFILE=hmpps-token \
    -e TF_LOG=INFO \
    -e HMPPS_BUILD_WORK_DIR=/home/tools/data/terraform \
    -e environment_name="${1}" \
    -e CUSTOM_COMMON_PROPERTIES_DIR=/home/tools/data/terraform/env_configs/common \
    -e "TERM=xterm-256color" \
    --entrypoint "scripts/${2}" \
    mojdigitalstudio/hmpps-terraform-builder-0-12