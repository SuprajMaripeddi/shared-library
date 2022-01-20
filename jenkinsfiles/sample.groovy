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
              git branch: 'master', url: "https://github.com/jenkins-docs/simple-java-maven-app.git"
            }
        }
           stage ('Scan and Build Jar File') {
            steps {
               withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'sonar') {
                sh 'mvn sonar:sonar -Dsonar.analysis.mode='
                }
            }
        }
        stage ('Build') {
            steps {
                sh "mvn clean install package -DskipTests"
                //nexusPublisher nexusInstanceId: 'releases', nexusRepositoryId: 'scm', packages: [[$class: 'MavenPackage', mavenAssetList: [], mavenCoordinate: [artifactId: 'my-app', groupId: 'com.mycompany.app', packaging: 'jar', version: '1.0-SNAPSHOT']]]
                nexusArtifactUploader artifacts: [[artifactId: 'my-app', classifier: '', file: 'devopp/target/my-app-1.0-SNAPSHOT.jar', type: 'jar']], credentialsId: 'nexus', groupId: 'com.mycompany.app', nexusUrl: 'http://52.15.201.38:8081/', nexusVersion: 'nexus3', protocol: 'http', repository: 'scm', version: '1.0-SNAPSHOT'            }
        }      
    }
}
