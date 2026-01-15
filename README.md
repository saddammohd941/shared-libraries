# shared-libraries

This structure follows **best practices** used by many teams (including Netflix, CloudBees, and large open-source Jenkins setups). It makes your library easy to maintain, version, test, and consume.

### Recommended Repo Structure (2026 Best Practice)

```
shared-libraries/
├── vars/                        # ← Core: all reusable pipeline steps (your "global functions")
│   ├── checkFileExists.groovy
│   ├── logMessage.groovy
│   ├── notifySlack.groovy       # example: future function
│   ├── retryWithBackoff.groovy  # example
│   └── ...                      # add more as needed
│
├── src/                         # ← Optional: reusable Groovy classes (advanced / non-step code)
│   └── com/
│       └── yourcompany/
│           └── util/
│               └── StringUtils.groovy   # example class
│
├── resources/                   # ← Optional: static files (JSON templates, YAML, properties, etc.)
│   ├── config/
│   │   └── default-notifications.json
│   └── scripts/
│       └── cleanup.sh           # example shell script
│
├── test/                        # ← Unit/integration tests for your library (highly recommended)
│   ├── groovy/
│   │   └── vars/
│   │       ├── checkFileExistsTest.groovy
│   │       └── logMessageTest.groovy
│   └── resources/
│       └── test-data/           # test files, mock responses, etc.
│
├── README.md                    # ← Must-have: documentation (see template below)
├── CHANGELOG.md                 # ← Track versions, breaking changes, new functions
├── LICENSE                      # e.g., MIT or Apache 2.0
├── .gitignore                   # ignore Jenkins temp files, IDE files, etc.
└── Jenkinsfile                  # ← Optional: self-test pipeline (runs library tests on push/PR)
```

### Folder-by-Folder Explanation

| Folder/File          | Purpose                                                                 | Required? | Notes / Best Practice |
|----------------------|--------------------------------------------------------------------------|-----------|-----------------------|
| `vars/`              | Contains all reusable **pipeline steps** (your "global functions")      | Yes       | Files must end in `.groovy`. Name = function name (e.g., `logMessage.groovy` → `logMessage()` call) |
| `src/`               | Reusable **Groovy classes** (not steps) – for advanced logic             | Optional  | Use package structure (e.g., `src/com/yourcompany/util/`) |
| `resources/`         | Non-code files (JSON, YAML, shell scripts, templates)                    | Optional  | Loaded via `libraryResource 'config/default.json'` |
| `test/`              | Unit & integration tests for your library                                | Highly recommended | Use **Jenkins Pipeline Unit** framework – prevents regressions |
| `README.md`          | Main documentation – how to use, install, examples                       | Yes       | Include setup, examples, version info |
| `CHANGELOG.md`       | Track releases, new functions, fixes, breaking changes                   | Recommended | Follow Keep a Changelog format |
| `Jenkinsfile`        | Optional self-test pipeline – runs tests on push/PR                      | Recommended | Validates library works in real Jenkins |
| `.gitignore`         | Ignore temp files, IDE, Jenkins @tmp folders                             | Yes       | Prevents committing junk |

# Shared Libraries for Jenkins Pipelines

Reusable steps and utilities for Jenkins pipelines.

## Structure

- `vars/`          → Pipeline steps (callable functions like `logMessage()`, `checkFileExists()`)
- `src/`           → Groovy classes (advanced logic)
- `resources/`     → Static files (JSON, YAML, scripts)
- `test/`          → Unit & integration tests

## How to Use in Your Jenkinsfile

```groovy
@Library('shared-libraries') _

pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                logMessage "Pipeline started", "INFO"
                if (checkFileExists('my-artifact.zip')) {
                    logMessage "Artifact found – deploying!", "SUCCESS"
                } else {
                    logMessage "Artifact missing!", "ERROR"
                }
            }
        }
    }
}
```

## Setup in Jenkins (One-Time)

1. Manage Jenkins → Configure System → Global Pipeline Libraries
2. Add:
   - Name: `shared-libraries`
   - Default version: `main`
   - Retrieval: Git → `https://github.com/saddammohd941/shared-libraries.git`
   - Credentials: (if private)
3. Save

## Available Functions

- `checkFileExists(String path)` → Returns `true` if file exists in workspace
- `logMessage(String msg, String level = 'INFO')` → Logs with timestamp and level (INFO/WARN/ERROR)

## Development

- Add new functions in `vars/yourFunction.groovy`
- Test locally with Groovy or in Jenkins
- Use `test/` folder + Pipeline Unit for regression testing
- Update `CHANGELOG.md` on each release

## Versioning

Use Git tags: `v1.0.0`, `v1.1.0`, etc.  
In pipelines: `@Library('shared-libraries@v1.0.0') _`

### Next Steps for You

1. **Create the folders/files** exactly as above in your repo
2. **Push everything** to `https://github.com/saddammohd941/shared-libraries.git`
3. **Configure in Jenkins** (Global Pipeline Libraries) as per your earlier message:
   - Name: `shared-libraries`
   - Default version: `main`
   - URL: `https://github.com/saddammohd941/shared-libraries.git`
   - Load implicitly: No
4. **Test** with the `Jenkinsfile` I gave earlier

### Bonus: Make It Even Better

- Add **semantic versioning** (tags like `v1.0.0`) → pin in pipelines: `@Library('shared-libraries@v1.0.0') _`
- Add **CHANGELOG.md** using [Keep a Changelog](https://keepachangelog.com/en/1.1.0/)
- Add **Pipeline Unit tests** in `test/` 
