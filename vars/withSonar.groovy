void call(Map opts) {
  withSonarQubeEnv(installationName: '${opts.installationName}', credentialsId: '${opts.credentialsId}') {
    sh 'mvn sonar:sonar -Dsonar.analysis.mode='
  }
}
