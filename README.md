# hmpps-delius-spg-testing-payload-generator
Generates payloads for testing with the SPG


Upon merge to master, this will trigger a githook that runs this jenkins job:
https://jenkins.engineering-dev.probation.hmpps.dsd.io/job/DAMS/job/Artefacts/job/SPG/job/spg-testing-payload-generator-master/

which in turn will publish the jar to this s3 bucket in engineering account:
https://s3.console.aws.amazon.com/s3/buckets/tf-eu-west-2-hmpps-eng-dev-maven-repo-s3bucket/releases/spg-testing/spg-payload-generator/

Version numbers are derived from /version.properties. This can be updated with the gradle semver plugin,
but this process has not been fully matured.