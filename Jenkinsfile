post {
    success {
        // Sends a success message to Slack
        slackSend(credentialsId: 'slack-webhook-url', color: 'good', 
                  message: "✅ SUCCESS: Build #${env.BUILD_NUMBER} of ${env.JOB_NAME} is complete!")
    }
    
    failure {
        // Sends a failure alert to Slack
        slackSend(credentialsId: 'slack-webhook-url', color: 'danger', 
                  message: "❌ FAILURE: Build #${env.BUILD_NUMBER} of ${env.JOB_NAME} failed!")

        // Sends the HTML email with the last 50 lines of logs to the Lead
        emailext (
            to: 'awie@example.com', // Replace with your email
            subject: "Build Failure: ${env.JOB_NAME}",
            body: "Check the attached log for errors. \n\n \${BUILD_LOG, maxLines=50}",
            credentialsId: 'team-lead-email-creds'
        )
    }
}
