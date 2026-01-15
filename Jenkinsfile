// Example Jenkinsfile in your main project repo

@Library('shared-libraries') _   // ← name must match the library name you configured in Jenkins

pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                script {
                    logMessage("Build stage started", "INFO")
                    
                    // Simulate build
                    sh 'echo "Building..." > build-output.txt'
                }
            }
        }
        
        stage('Check Artifacts') {
            steps {
                script {
                    if (checkFileExists('build-output.txt')) {
                        logMessage("Build artifact found – ready to archive", "INFO")
                        archiveArtifacts artifacts: 'build-output.txt', fingerprint: true
                    } else {
                        logMessage("Build artifact missing – marking unstable", "WARN")
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }
        
        stage('Deploy') {
            when {
                expression { currentBuild.result != 'UNSTABLE' && currentBuild.result != 'FAILURE' }
            }
            steps {
                logMessage("Deploying to production", "INFO")
                // Your deploy steps here
            }
        }
    }
}
