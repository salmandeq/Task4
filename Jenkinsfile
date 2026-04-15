pipeline {
    agent any 
    stages {
        stage('Instant Build') {
            steps {
                echo 'Build successful!'
            }
        }
    }
    post {
        success {
            // Slack is fast; Email is slow. Let's stick to Slack for the demo.
            slackSend(credentialsId: 'slack-webhook-url', color: 'good', message: "✅ DONE!")
        }
    }
}
