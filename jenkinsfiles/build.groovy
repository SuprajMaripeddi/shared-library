@Library('shared-library') _

pipeline {
    agent any

   //  Checking Environment Variables
    tools{
    maven 'maven'
    }
    stages {
        stage ('git clone') {
                steps {
                git branch: 'master', url: "https://github.com/SuprajMaripeddi/TomcatMavenApp.git"
                }
        }
        stage ('Scan and Build Jar File') {
            steps {
                
                withSonar(
                    installationName: 'SonarQube', 
                    credentialsId: 'sonar'
                )
            }
        }
        stage ('Build') {
            steps {
                 withMaven("mvn clean install package -DskipTests")
                    //nexusPublisher nexusInstanceId: 'releases', nexusRepositoryId: 'scm', packages: [[$class: 'MavenPackage', mavenAssetList: [], mavenCoordinate: [artifactId: 'my-app', groupId: 'com.mycompany.app', packaging: 'jar', version: '1.0-SNAPSHOT']]]
            } 
        }
        stage ('Publish') {
            steps {
                nexusArtifcatUploader(
                    artifactId: 'TomcatMavenApp', 
                    classifier: '', 
                    file: '/var/lib/jenkins/workspace/devopp/target/TomcatMavenApp-3.0.war', 
                    type: 'war', 
                    credentialsId: 'nexus', 
                    groupId: 'com.sarav', 
                    nexusUrl: '52.15.201.38:8081', 
                    nexusVersion: 'nexus3', 
                    protocol: 'http', 
                    repository: 'scm', 
                    version: '3.0' 
                )           
            } 
        }
        stage ('deploy') {
            steps {
                tomcatDeploy(
                    credentialsId: 'tomcat', 
                    path: '/var/lib/jenkins/workspace/devopp/target/TomcatMavenApp-3.0.war', 
                    url: 'http://3.135.239.250:8080/',
                    contextPath: 'app', 
                    war: '**/*.war'
                )
            }       
        }
    }
}
