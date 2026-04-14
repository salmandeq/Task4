pipeline {
    agent any

    tools {
        maven 'Maven2'
        jdk 'java21'
    }

    environment {
        // This matches the ID "Sonar-Token" from your screenshot
        SONAR_TOKEN = credentials('Sonar-Token')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn -f Task4/pom.xml clean compile -DskipTests'
            }
        }

        stage('Unit Test') {
            steps {
                bat 'mvn -f Task4/pom.xml test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Ensure the name 'SonarQube-Server' matches Manage Jenkins -> System
                withSonarQubeEnv('SonarQube-Server') {
                    bat "mvn -f Task4/pom.xml sonar:sonar -Dsonar.login=${SONAR_TOKEN}"
                }
            }
        }

        stage('Deploy to Staging') {
            steps {
                echo "Build and Analysis complete. Ready for deployment."
            }
        }
    }

    post {
        always {
            script {
                if (fileExists('Task4/target/surefire-reports')) {
                    junit 'Task4/target/surefire-reports/*.xml'
                }
            }
        }
        success {
            echo 'CI/CD Pipeline finished successfully!'
        }
    }
}
