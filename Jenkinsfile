pipeline {
    agent any

    tools {
        maven 'Maven2'
        jdk 'java21'
    }

    environment {
        STAGING_CREDS = credentials('staging-server-ssh')
        SONAR_TOKEN   = credentials('sonar-token')
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

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube-Server') {
                    sh "mvn sonar:sonar -Dsonar.login=${SONAR_TOKEN}"
                }
            }
        }

        stage('Deploy to Staging') {
            steps {
                sh "scp target/*.jar ${STAGING_CREDS}@staging-server:/apps/"
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
        failure {
            mail to: 'team@example.com',
                 subject: "Failed: ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
                 body: "Build failed. Check logs at: ${env.BUILD_URL}"
        }
        success {
            echo 'Pipeline completed successfully!'
        }
    }
}
