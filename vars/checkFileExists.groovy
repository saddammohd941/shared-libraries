// vars/checkFileExists.groovy
def call(String filePath) {
    if (fileExists(filePath)) {
        echo "✓ File exists: ${filePath}"
        return true
    } else {
        echo "✗ File missing: ${filePath}"
        return false
    }
}
