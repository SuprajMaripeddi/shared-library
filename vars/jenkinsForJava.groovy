
// vars
// | --- welcomeJob.groovy
// | --- jenkinsForJava.groovy
 
// jenkinsForJava.groovy
def call(String repoUrl) {
 pipeline {
agent any
    tools {
        maven 'apache-maven-3.0.1' 
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deliver') {
            steps {
                sh './jenkins/scripts/deliver.sh'
            }
        }
    }
}
//   pipeline {
//        agent any
//        tools {
//           maven 'Maven 3.3.9'
//             jdk "jdk-1.8.101"
//        }
//   steps {
//                     sh 'java -version'
//                 }
//        stages {
//            stage("Tools initialization") {
//                steps {
//                    sh "mvn --version"
//                    sh "java -version"
//                }
//            }
//            stage("Checkout Code") {
//                steps {
//                    git branch: 'master',
//                        url: "${repoUrl}"
//                }
//            }
//            stage("Cleaning workspace") {
//                steps {
//                    sh "mvn clean"
//                }
//            }
//            stage("Running Testcase") {
//               steps {
//                    sh "mvn test"
//                }
//            }
//            stage("Packing Application") {
//                steps {
//                    sh "mvn package -DskipTests"
//                }
//            }
//        }
//    }
}
