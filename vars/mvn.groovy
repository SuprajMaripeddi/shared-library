def call() {
  pipeline {
    agent any
    tools { 
        maven 'maven'  
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "JAVA_HOME = ${JAVA_HOME}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
              
            }
        }
      stage ('Scan and Build Jar File') {
            steps {
               withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'sonar') {
                sh 'mvn clean package sonar:sonar'
                }
            }
        }

        stage ('Build') {
            steps {
                echo 'This is a minimal pipeline.'
            }
        }
    }
}
}
