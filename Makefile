default: sandpit-plan
.PHONY: sandpit-plan

# Payload generator Pipeline
eng-ci-plan: ## Run eng-ci-plan with ci_components_flag=true
	scripts/terraform-local-builder.sh delius-core-sandpit terraform-local-plan.sh true

eng-ci-apply: ## Run eng-ci-plan with ci_components_flag=true
	scripts/terraform-local-builder.sh delius-core-sandpit terraform-local-apply.sh true

