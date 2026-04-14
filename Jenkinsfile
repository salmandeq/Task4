pipeline {
    agent any

    tools {
        maven 'Maven2'
        jdk 'java21'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile -DskipTests'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
        }
        
        // Temporarily commented out until your SonarQube & Credentials are set up
        /*
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube-Server') {
                    sh "mvn sonar:sonar"
                }
            }
        }
        */
    }

    post {
        always {
            script {
                // Only run junit if the directory exists to avoid the error you saw
                if (fileExists('target/surefire-reports')) {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        success {
            echo 'Build and Tests passed!'
        }
    }
}
