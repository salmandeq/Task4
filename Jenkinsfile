post {
    success {
        // Notify Slack of the win!
        slackSend(credentialsId: 'slack-webhook-url', color: 'good', message: "✅ Weno Jewelry Build Success!")
    }
    failure {
        // Notify Slack of the fail
        slackSend(credentialsId: 'slack-webhook-url', color: 'danger', message: "❌ Build Failed. Checking logs...")
        
        // Email the lead developer the logs
        emailext (
            to: 'awie@example.com', 
            subject: "Build Failure: ${env.JOB_NAME}",
            body: "The build failed. Last 50 lines: \${BUILD_LOG, maxLines=50}",
            credentialsId: 'team-lead-email-creds'
        )
    }
}
