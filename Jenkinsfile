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
                script {
                    def scannerHome = tool 'SonarScanner' // Make sure 'SonarScanner' is the name in Global Tool Config
                    withSonarQubeEnv('SonarQube-Server') {
                        bat "${scannerHome}/bin/sonar-scanner.bat " +
                            "-Dsonar.projectKey=Task4 " +
                            "-Dsonar.sources=Task4/src/main/java " +
                            "-Dsonar.java.binaries=Task4/target/classes " +
                            "-Dsonar.login=${SONAR_TOKEN}"
                    }
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
