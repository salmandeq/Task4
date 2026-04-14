pipeline {
    agent { label 'java-build-agent' }

    options {
        retry(2)
        timeout(time: 15, unit: 'MINUTES')
    }

    stages {
        stage('Checkout') {
            steps {
                // Corrected the URL to your actual repo
                git 'https://github.com/salmandeq/Task4.git'
            }
        }

        stage('Build & Test') {
            steps {
                // Assuming you are using Maven for your Java project
                // Use 'bat' for Windows agents or 'sh' for Linux
                bat 'mvn clean install' 
            }
        }
    }

    post {
        always {
            script {
                // Only records results if the folder exists to avoid errors
                if (fileExists('target/surefire-reports')) {
                    junit '**/target/surefire-reports/*.xml'
                }
                if (fileExists('target/*.jar')) {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
        failure {
            echo "Build failed on the agent. Check connectivity or code errors."
        }
    }
} // Final closing bracket for the pipeline
