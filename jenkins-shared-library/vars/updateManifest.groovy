def call() {
    script {
        def newImage = "${env.DOCKER_REGISTRY}/${env.DOCKER_IMAGE}:${env.BUILD_NUMBER}"
        withCredentials([usernamePassword(credentialsId: 'git-cred', usernameVariable: 'GIT_USER', passwordVariable: 'GIT_PASS')]) {
            sh """
                git config user.email "${env.GIT_EMAIL}"
                git config user.name "${env.GIT_USER_NAME}"
                sed -i 's|image: .*|image: ${newImage}|g' ${env.MANIFEST_FILE}
                git add ${env.MANIFEST_FILE}
                git commit -m "Update image to ${BUILD_NUMBER}" || echo "No changes"
                git push https://${GIT_USER}:${GIT_PASS}@github.com/${env.GIT_USER_NAME}/${env.GIT_REPO_NAME}.git HEAD:main
            """
        }
    }
}
