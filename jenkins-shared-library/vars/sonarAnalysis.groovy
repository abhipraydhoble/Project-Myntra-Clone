def call() {
    withSonarQubeEnv('sonar-server') {
        sh """
            ${env.SCANNER_HOME}/bin/sonar-scanner \
            -Dsonar.projectName=Myntra \
            -Dsonar.projectKey=Myntra
        """
    }
}
