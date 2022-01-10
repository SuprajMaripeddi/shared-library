@Library('shared-library@main')_

pipeline {
    agent any
    stages {
        stage ('Run only if approval exists') {
            steps {
                welcomeJob ‘lambdatest’
                echo "The build has been approved!!!"
            }
        }
    }
}
