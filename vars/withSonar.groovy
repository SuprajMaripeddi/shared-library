void call(Map opts) {
  withSonarQubeEnv(installationName: '${opts.installationName}', credentialsId: '${credentialsId}') {
    sh 'mvn sonar:sonar -Dsonar.analysis.mode='
  }
}
