default: sandpit-plan
.PHONY: sandpit-plan

# Payload generator Pipeline
eng-ci-plan:
	scripts/terraform-local-builder.sh delius-core-sandpit terraform-local-plan.sh

eng-ci-apply:
	scripts/terraform-local-builder.sh delius-core-sandpit terraform-local-apply.sh

eng-ci-destroy:
	scripts/terraform-local-builder.sh delius-core-sandpit terraform-local-destroy.sh
