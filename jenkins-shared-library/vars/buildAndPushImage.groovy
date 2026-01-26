def call() {
    script {
        def imageTag = "${env.DOCKER_IMAGE}:${env.BUILD_NUMBER}"
        def registryImageTag = "${env.DOCKER_REGISTRY}/${imageTag}"

        sh "docker build -t ${imageTag} ."
        withDockerRegistry(credentialsId: env.DOCKER_CREDENTIALS_ID, toolName: 'docker') {
            sh """
                docker tag ${imageTag} ${registryImageTag}
                docker push ${registryImageTag}
            """
        }
    }
}
