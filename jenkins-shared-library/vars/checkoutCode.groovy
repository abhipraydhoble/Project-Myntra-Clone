def call(String repo, String branch = 'main') {
    git branch: branch, url: repo
}
