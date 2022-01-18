pipeline {
    agent any

   //  Checking Environment Variables
    tools{
    maven 'maven'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                    echo "JAVA_HOME = ${JAVA_HOME}"
                '''
            }
        }

        stage ('git clone') {
            steps {
              git branch: 'master', url: "https://github.com/SuprajMaripeddi/shared-library.git", credentialsId: 'github'
            }
        }
        stage ('Build') {
            steps {
                sh "mvn clean install -DskipTests"
            }
        }      
    }
}
