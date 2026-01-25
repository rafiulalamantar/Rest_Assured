# 🧪 Rest Assured API Automation

A collection of automated API tests written in **Java** using **Rest Assured** and **TestNG** to validate RESTful services and web APIs.

---

## 🚀 Features

* REST API automation using **Rest Assured**
* **TestNG** test runner for structured test execution
* POJO classes for request/response **serialization and deserialization**
* External **JSON payloads** for dynamic test data
* Easily extendable for new API endpoints

---

## 📁 Repository Structure

```text
Rest_Assured/
├── .idea/                             # IDE config (optional)
├── gradle/                             # Gradle wrapper and build setup
├── src/test/java/                      # Java test files
│   ├── files/                          # Test classes
│   └── pojo/                           # POJO classes for API requests/responses
├── addPlace.json                       # Sample JSON payload
├── courses.json                        # Sample JSON payload for courses
├── ClientCredentialsOAuth.postman_collection.json  # Postman collection
├── Google+Place+APIs.postman_collection.json       # Postman collection
├── Google_APIs_Document                # Documentation folder
├── build.gradle                        # Gradle build file
├── settings.gradle                     # Gradle settings
├── gradlew / gradlew.bat               # Gradle wrapper scripts
└── README.md                           # This file
```

> Update the structure if you add more folders or files.

---

## 📦 Prerequisites

Make sure you have the following installed:

* **Java 11+**
* **Gradle**
* **TestNG** (via Gradle dependency)
* **Rest Assured** (via Gradle dependency)

---

## 🛠 Setup

1. **Clone the repository**

```bash
git clone https://github.com/rafiulalamantar/Rest_Assured.git
cd Rest_Assured
```

2. **Build the project using Gradle**

```bash
./gradlew build       # macOS/Linux
gradlew.bat build     # Windows
```

3. **Install dependencies**

Gradle will automatically download all dependencies defined in `build.gradle`.

---

## ▶️ Running Tests

To run all tests with Gradle:

```bash
./gradlew test        # macOS/Linux
gradlew.bat test      # Windows
```

To run a specific TestNG class:

```bash
./gradlew test --tests "files.YourTestClass"
```

To see detailed output:

```bash
./gradlew test --info
```

---

## 📌 Rest Assured Tips

* Use **POJO classes** for request/response objects to simplify **serialization/deserialization**.
* Externalize **JSON payloads** for reusable test data.
* Use **TestNG annotations** like `@BeforeClass`, `@BeforeMethod` for setup and teardown.
* Leverage **RequestSpecification** and **ResponseSpecification** for cleaner code.

---

## 🎯 Test Reporting

By default, TestNG generates **HTML and XML reports** under `test-output` folder.

You can integrate more advanced reporting:

* **Allure Reports** for rich visual reporting
* **Surefire HTML reports** for CI/CD pipelines

Example to generate Allure report:

```bash
./gradlew allureServe
```

---

## 📈 CI (GitHub Actions)

You can create a workflow like `.github/workflows/rest_assured.yml` to run tests on push/pull requests. Example:

```yaml
name: Rest Assured API Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Setup JDK
        uses: actions/setup-java@v3
        with:
          java-version: '11'
          distribution: 'temurin'
      - name: Build and test
        run: |
          ./gradlew build
          ./gradlew test
```

---

## 🧩 Contributing

Feel free to open issues or create pull requests. Best practices:

* Add meaningful API test cases
* Reuse **Request/Response POJOs**
* Keep JSON payloads organized
* Write clean and maintainable TestNG code

---

## 📝 License

You can optionally add your license (e.g., MIT), if desired.

---

## 🙌 Thanks

## Thank you for using this API automation suite! Questions or contributions are Welcome.
