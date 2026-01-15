// Filename: vars/getTimestamp.groovy
def call() {
    return new Date().format("yyyyMMdd_HHmmss")
}
