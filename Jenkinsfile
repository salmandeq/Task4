pipeline {
    agent { label 'java-build-agent' }
    
    tools {
        maven 'Maven3' 
    }

    options {
        retry(2)
        timeout(time: 15, unit: 'MINUTES')
    }

    stages {
        stage('Build & Test') {
            steps {
                // Since pom.xml is in the root, we run mvn directly
                bat 'mvn clean install'
            }
        }
    }

    post {
        always {
            script {
                // Collect results from the target folder created by Maven
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
