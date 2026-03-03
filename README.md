# 🏦 Credo Bank — Web UI Test Automation

End-to-end UI test automation suite for [mycredo.ge](https://mycredo.ge), built with **Selenium 4**, **TestNG**, and **Allure** reporting. Tests cover the Login page across multiple languages (Georgian dialects + English).

---

## 🛠 Tech Stack

| Tool | Purpose |
|---|---|
| Java 25 | Language |
| Selenium 4 | Browser automation |
| TestNG 7 | Test framework |
| WebDriverManager | Automatic driver management |
| Allure 2 | Test reporting |
| Lombok | Boilerplate reduction |
| SLF4J + Logback | Logging |
| Maven | Build & dependency management |

---

## 📁 Project Structure

```
src/
├── main/java/ge/credo/testautomation/
│   ├── data/
│   │   ├── Constants.java              # URLs, timeouts, UI strings, browser args
│   │   ├── enums/
│   │   │   ├── Browser.java            # CHROME, EDGE
│   │   │   ├── Language.java           # SVAN, MEGRELIAN, ENGLISH — with all UI text
│   │   │   └── SubmitButtonState.java  # ENABLED, DISABLED
│   │   └── dataproviders/
│   │       └── LoginDataProvider.java  # TestNG @DataProvider for login tests
│   ├── pages/
│   │   ├── LoginPage.java              # Page Object for the login page
│   │   ├── commonpages/                # Header, navigation
│   │   └── modalpages/                 # Language switcher modal
│   ├── steps/
│   │   ├── LoginPageSteps.java         # Fluent step methods for login page
│   │   ├── commonsteps/                # Header step methods
│   │   └── modalsteps/                 # Language switcher step methods
│   └── utils/
│       ├── BaseSetup.java              # ThreadLocal WebDriver, WebDriverWait, SoftAssert
│       ├── Helpers.java                # Reusable wait/assert/JS utilities
│       ├── ScreenshotListener.java     # Auto-screenshot on test failure → Allure
│       └── RetryMaker.java             # IRetryAnalyzer — retries failed tests up to 2x
└── test/java/ge/credo/testautomation/
    └── CredoLoginTest.java             # All login page test cases
```

---

## ✅ Test Cases

| # | Test | Severity |
|---|---|---|
| 1 | Landing page headers are visible and active header is correct | NORMAL |
| 2 | Save user button, password visibility toggle | NORMAL |
| 3 | Submit button disabled when only one field is filled | CRITICAL |
| 4 | Login with invalid credentials shows correct error per language (Svan / Megrelian / English) | CRITICAL |

---

## ⚙️ Configuration

### Browsers
Supported: `chrome` (default), `edge`

Override via TestNG parameter:
```xml
<parameter name="browser" value="edge"/>
```

### Timeouts (`Constants.java`)
| Constant | Value |
|---|---|
| `PAGE_LOAD_TIMEOUT` | 30s |
| `EXPLICIT_WAIT_TIMEOUT` | 5s |

---

## 🚀 Running Tests

### From IntelliJ
Right-click `CredoLoginTest` or `TestNG.xml` → **Run**

### From Maven
```bash
mvn test
```

### Specific browser
```bash
mvn test -Dbrowser=edge
```

---

## 📊 Allure Report

### Generate & open report
```bash
allure serve target/allure-results
```

### Or via Maven
```bash
mvn allure:serve
```

Allure results are written to `target/allure-results/` on every test run.

---

## 🏗 Architecture

### Page Object Model
Each page is modelled as a Java class with `@FindBy` locators. Steps classes wrap page objects with fluent, chainable methods:
```java
loginPageSteps
    .enterUserName("user123")
    .enterUserPass("pass456")
    .clickSubmitUser()
    .checkErrorBarVisible();
```

### ThreadLocal WebDriver
`BaseSetup` stores `WebDriver`, `WebDriverWait`, and `SoftAssert` in `ThreadLocal` — safe for parallel test execution.

### Soft Assertions
Tests use `getSoftAssert()` throughout and call `getSoftAssert().assertAll()` at the end of each test. On failure, `onTestFailure` fires with the driver still alive — triggering an automatic screenshot.

### Auto Screenshots
`ScreenshotListener` implements `ITestListener`. On `onTestFailure` it calls `BaseSetup.getDriver()` directly (no reflection) and attaches the screenshot + failure message to the Allure report.

### Retry
`RetryMaker` implements `IRetryAnalyzer` with `ThreadLocal` retry counters. Failed tests are automatically retried up to **2 times** before being marked as failed.

---

## 🌍 Multi-language Testing

The `Language` enum holds all expected UI strings for each supported language:

| Language | Code |
|---|---|
| სვანური (Svan) | `SVAN` |
| მეგრული (Megrelian) | `MEGRELIAN` |
| English | `ENGLISH` |

`LoginDataProvider` feeds these into the data-driven login test.

