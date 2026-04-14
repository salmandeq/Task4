pipeline {
    agent { label 'java-build-agent' }

    options {
        // Point 6: Retry logic for offline agents
        retry(2)
        timeout(time: 15, unit: 'MINUTES')
    }

    stages {
        // We removed the manual 'Checkout' stage to avoid the duplicate name error.
        // Jenkins handles the checkout automatically.

        stage('Build & Test') {
            steps {
                // Point 7: Running the Maven build
                // Using 'bat' because your agents are Windows-based
                bat 'mvn clean install' 
            }
        }
    }

    post {
        always {
            script {
                // Point 7: Collect artifacts back to Master
                if (fileExists('target/surefire-reports')) {
                    junit '**/target/surefire-reports/*.xml'
                }
                if (fileExists('target/*.jar')) {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
    }
}
