// Filename: vars/getLatestTag.groovy
def call() {
    return sh(script: 'git describe --tags --abbrev=0', returnStdout: true).trim()
}
