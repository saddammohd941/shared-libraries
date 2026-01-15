// vars/logMessage.groovy
def call(String message, String level = 'INFO') {
    def timestamp = new Date().format('yyyy-MM-dd HH:mm:ss')
    def prefix
    
    switch (level.toUpperCase()) {
        case 'INFO':
            prefix = "[INFO] "
            break
        case 'WARN':
            prefix = "[WARN] "
            break
        case 'ERROR':
            prefix = "[ERROR] "
            break
        default:
            prefix = "[${level}] "
    }
    
    echo "${timestamp} ${prefix}${message}"
}
