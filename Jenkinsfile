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
                // Add -f Task4/pom.xml to tell Maven where the project is
                bat 'mvn -f Task4/pom.xml clean compile -DskipTests'
            }
        }

        stage('Unit Test') {
            steps {
                // Add -f Task4/pom.xml here as well
                bat 'mvn -f Task4/pom.xml test'
            }
        }

        /* stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube-Server') {
                    bat "mvn -f Task4/pom.xml sonar:sonar"
                }
            }
        }
        */
    }

    post {
        always {
            script {
                // Update path for test results as well
                if (fileExists('Task4/target/surefire-reports')) {
                    junit 'Task4/target/surefire-reports/*.xml'
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
