pipeline {
    agent { label 'java-build-agent' }
    
    // This tells Jenkins to use the Maven configuration defined in Global Tool Configuration
    tools {
        maven 'Maven3' 
    }

    stages {
        stage('Build & Test') {
            steps {
                bat 'mvn clean install'
            }
        }
    }
    // ... keep the rest of your post actions
}
