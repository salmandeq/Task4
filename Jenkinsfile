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
                bat 'mvn clean compile -DskipTests'
            }
        }

        stage('Unit Test') {
            steps {
                bat 'mvn test'
            }
        }

        /* stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube-Server') {
                    bat "mvn sonar:sonar"
                }
            }
        }

        stage('Deploy to Staging') {
            steps {
                echo "Deploying to staging..."
            }
        }
        */
    }

    post {
        always {
            script {
                if (fileExists('target/surefire-reports')) {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        success {
            echo 'Build and Tests passed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check the console output.'
        }
    }
}
