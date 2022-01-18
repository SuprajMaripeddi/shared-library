
   
@Library('shared-library') _

import com.aws.ce.pipeline.GlobalVars

def config = [name: 'Newman', dayOfWeek: 'Friday']
pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                helloWorld(config)
               
                script{
                    log.info 'Starting'
                log.warning 'Nothing to do!'
                    echo GlobalVars.GITHUB_HOST
                }
            }
        }
    }
}
