pipeline {
    agent any

    tools {
        maven 'Maven2'
        jdk 'java21'
    }

    environment {
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
                withSonarQubeEnv('SonarQube-Server') {
                    // Using the modern plugin with a fallback property for older server communication
                    bat "mvn -f Task4/pom.xml org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184:sonar -Dsonar.login=${SONAR_TOKEN} -Dsonar.scanner.force-deprecated-java-version=true"
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
