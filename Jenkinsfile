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
                    // Downgrading to 3.2 which is the most stable 'old' version
                    bat "mvn -f Task4/pom.xml org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar -Dsonar.login=${SONAR_TOKEN} -Dsonar.java.binaries=target/classes"
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
