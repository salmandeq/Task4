pipeline {
    agent { label 'java-build-agent' }
    
    tools {
        maven 'Maven3' 
    }

    stages {
        stage('Build & Test') {
            steps {
                // If your code is in a folder (e.g., 'my-app'), use 'dir'
                // Replace 'YOUR_FOLDER_NAME' with the actual folder name in your repo
                dir('YOUR_FOLDER_NAME') {
                    bat 'mvn clean install'
                }
                
                // If your code is in the root, just check if the pom.xml is named correctly
            }
        }
    }
    
    post {
        always {
            script {
                if (fileExists('**/target/surefire-reports')) {
                    junit '**/target/surefire-reports/*.xml'
                }
                if (fileExists('**/target/*.jar')) {
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
        }
    }
}
