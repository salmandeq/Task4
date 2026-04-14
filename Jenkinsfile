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
        stage('Checkout') { steps { checkout scm } }
        stage('Build') { steps { bat 'mvn -f Task4/pom.xml clean compile -DskipTests' } }
        stage('Unit Test') { steps { bat 'mvn -f Task4/pom.xml test' } }
        
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube-Server') {
                    // We use the standalone scanner property to bypass Maven plugin version conflicts
                    bat "mvn -f Task4/pom.xml sonar:sonar -Dsonar.login=${SONAR_TOKEN} -Dsonar.java.binaries=target/classes"
                }
            }
        }

        stage('Deploy to Staging') { steps { echo "Ready for deployment." } }
    }
    post {
        always {
            script {
                if (fileExists('Task4/target/surefire-reports')) {
                    junit 'Task4/target/surefire-reports/*.xml'
                }
            }
        }
    }
}
