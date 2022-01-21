
void call(Map opts) {
  nexusArtifactUploader artifacts: [[artifactId: "${opts.artifactId}", classifier: "${opts.classifier}", file: "${opts.file}", type: "${opts.type}"]], credentialsId: "${opts.credentialsId}", groupId: "${opts.groupId}", nexusUrl: "${opts.nexusUrl}", nexusVersion: "${opts.nexusVersion}", protocol: "${opts.protocol}", repository: "${opts.repository}", version: "${opts.version}"
}
