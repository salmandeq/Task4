pipeline {
    agent any 
    stages {
        stage('Cleanup') {
            steps {
                deleteDir() // Forces a clean start
            }
        }
        stage('Final Success') {
            steps {
                echo 'Build Successful'
            }
        }
    }
}
