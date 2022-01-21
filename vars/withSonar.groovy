void call(Map opts) {
  echo "${opts.installationName}"
  withSonarQubeEnv(installationName: '${opts.installationName}', credentialsId: '${opts.credentialsId}') {
    sh 'mvn sonar:sonar -Dsonar.analysis.mode='
  }
}
