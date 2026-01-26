@Library('jenkins-shared-library') _

pipeline {
    agent any
    tools {
        jdk 'jdk17'
        nodejs 'node16'
    }
    environment {
        SCANNER_HOME          = tool 'sonar-scanner'
        DOCKER_IMAGE          = 'myntraa'
        DOCKER_REGISTRY       = 'abhipraydh96'
        DOCKER_CREDENTIALS_ID = 'docker-cred'
        MANIFEST_FILE         = 'k8s/deployment.yml'
        GIT_REPO_NAME         = 'Project-Myntra-Clone'
        GIT_USER_NAME         = 'abhipraydhoble'
        GIT_EMAIL             = 'abhipraydh96@gmail.com'
    }

    stages {
        stage('Clean Workspace') {
            steps { cleanWorkspace() }
        }
        stage('Checkout Code') {
            steps {
                checkoutCode("https://github.com/${env.GIT_USER_NAME}/${env.GIT_REPO_NAME}.git")
            }
        }
        stage('SonarQube Analysis') {
            steps { sonarAnalysis() }
        }
        stage('Quality Gate') {
            steps { waitForQualityGate abortPipeline: false, credentialsId: 'sonar-token' }
        }
        stage('Install Dependencies') {
            steps { installDeps() }
        }
        stage('Build & Push Docker Image') {
            steps { buildAndPushImage() }
        }
        stage('Update Manifest and Push to GitHub') {
            steps { updateManifest() }
        }
    }
}
