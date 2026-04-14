pipeline {
    agent { label 'java-build-agent' }
    
    tools {
        maven 'Maven3' 
    }

    options {
        // Point 6: Retrying helps if there are network or agent glitches
        retry(2)
        timeout(time: 15, unit: 'MINUTES')
    }

    stages {
        stage('Build & Test') {
            steps {
                // Point 7: Running Maven by pointing directly to the pom.xml location
                // This command tells Maven to look inside the Task4 folder for the pom.xml
                bat 'mvn -f Task4/pom.xml clean install'
            }
        }
    }

    post {
        always {
            script {
                // Using wildcards (**) ensures results are found regardless of folder depth
                if (fileExists('**/target/surefire-reports')) {
                    junit '**/target/surefire-reports/*.xml'
                }
                if (fileExists('**/target/*.jar')) {
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
        }
    }
}
