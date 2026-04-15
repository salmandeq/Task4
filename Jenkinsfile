pipeline {
    any 
    
    stages {
        stage('Build') {
            steps {
                echo 'Building Weno Jewelry System...'
            }
        }
    }

    // THE POST BLOCK MUST BE INSIDE THE PIPELINE BLOCK
    // BUT OUTSIDE THE STAGES BLOCK
    post {
        success {
            slackSend(credentialsId: 'slack-webhook-url', color: 'good', message: "Success!")
        }
        failure {
            slackSend(credentialsId: 'slack-webhook-url', color: 'danger', message: "Failed!")
        }
    }
}
