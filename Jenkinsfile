pipeline {
    agent { label 'java-build-agent' } // Tells Jenkins to use your new agents

    options {
        // Point 6: If an agent goes offline mid-build, 
        // it will retry the job up to 2 times on a healthy agent.
        retry(2) 
        
        // Stops the build if it takes longer than 15 minutes
        timeout(time: 15, unit: 'MINUTES') 
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-repo/java-project.git'
            }
        }

        stage('Build & Test') {
            steps {
                // Your Java build command
                sh 'mvn clean install' 
            }
        }
    }

    post {
        // Point 7: Collecting artifacts and reports from the distributed nodes
        always {
            // Pulls XML test results from the agent to display in the Jenkins UI
            junit '**/target/surefire-reports/*.xml'
            
            // Collects the final .jar file so you can download it from the Master
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        
        failure {
            echo "The build failed. Check the Agent Logs for connectivity issues."
        }
    }
}
