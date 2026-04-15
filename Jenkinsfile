pipeline {
    agent any
    options {
        timeout(time: 30, unit: 'SECONDS') // Kills it if it takes more than 30s
    }
    stages {
        stage('Fast Build') {
            steps {
                echo 'Weno Jewelry Build Started...'
                echo 'Processing metadata...'
                echo 'Build Finished Successfully.'
            }
        }
    }
    post {
        success {
            // This is the main thing for your demo
            slackSend(credentialsId: 'slack-webhook-url', 
                      color: 'good', 
                      message: "✅ SUCCESS: Build #${env.BUILD_NUMBER} passed in seconds!")
        }
    }
}
