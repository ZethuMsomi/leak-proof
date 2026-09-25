# 🔐 Leak-Proof
Verification Code:
WTC-FC8QU3P9

> **A Java-based cybersecurity scanner that detects exposed secrets and processes security findings through an ETL data pipeline.**

---

## 🛡️ About the Project

**Leak-Proof** is a cybersecurity tool built with Java that scans software projects for accidentally exposed sensitive information.

The scanner identifies potential security leaks, assigns severity levels, stores the findings in a SQLite database, and allows the data to be analysed using SQL.

### ✨ What it does

* 🔍 Scans project directories for potential security leaks
* 🔑 Detects exposed credentials and sensitive keys
* 🚨 Assigns severity levels to findings
* 💾 Stores findings in a SQLite database
* 🔄 Processes findings through an **ETL pipeline**
* 📊 Uses SQL to analyse security findings
* 📋 Generates a report showing detected leaks and their locations

---

## 🧰 Technologies

| Technology      | Purpose                           |
| --------------- | --------------------------------- |
| ☕ Java 21       | Application development           |
| 📦 Maven        | Project and dependency management |
| 🗄️ SQLite      | Data storage and analysis         |
| 🔌 JDBC         | Database connection               |
| 🧪 JUnit        | Testing                           |
| 🌱 Git & GitHub | Version control                   |

---

## 🔄 Data Engineering Pipeline

Leak-Proof includes an **ETL (Extract, Transform, Load) pipeline** for processing security findings.

### 📥 Extract

`ProjectScanner` scans the target project and collects detected security findings as `ScanResult` objects.

### 🔄 Transform

`DataTransformer` converts the raw findings into structured `FindingRecord` objects and assigns a severity level.

### 📤 Load

`FindingRepository` loads the transformed records into a SQLite database.

### 📊 Analyse

SQL queries are used to analyse the stored security findings.

```text
        🔍 Project Scanner
               │
               ▼
          📥 EXTRACT
               │
               ▼
          ScanResult
               │
               ▼
         🔄 TRANSFORM
               │
               ▼
     FindingRecord + Severity
               │
               ▼
            📤 LOAD
               │
               ▼
        🗄️ SQLite Database
               │
               ▼
           📊 ANALYSE
```

---

## 🗃️ Database

Findings are stored in a `findings` table.

| Column        | Description                         |
| ------------- | ----------------------------------- |
| `id`          | Unique finding ID                   |
| `file`        | File where the finding was detected |
| `line_number` | Line containing the finding         |
| `leak_name`   | Type of detected leak               |
| `severity`    | Severity assigned to the finding    |

### 📈 Example Analysis

Findings can be grouped by severity:

```sql
SELECT severity, COUNT(*)
FROM findings
GROUP BY severity;
```

Example result:

```text
HIGH | 2
```

Findings can also be grouped by leak type:

```sql
SELECT leak_name, COUNT(*)
FROM findings
GROUP BY leak_name;
```

Example result:

```text
AWS Key | 2
```

---

## 🚀 How to Run

### 1️⃣ Clone the repository

```bash
git clone https://github.com/ZethuMsomi/leak-proof.git
```

### 2️⃣ Open the project

Open the project in **IntelliJ IDEA** or another Java IDE with Maven support.

### 3️⃣ Run `Main.java`

The application will ask you to enter the directory you want to scan.

Example:

```text
C:\Users\YourName\test-leak
```

### 4️⃣ View the results

The scanner will analyse the directory and display any detected leaks.

The findings will also be stored in the local SQLite database.

---

## 📁 Project Structure

```text
leak-proof/
│
├── 📂 src/
│   ├── 📂 main/
│   │   └── 📂 java/
│   │       └── 📂 org/
│   │           └── 📂 leakproof/
│   │               ├── 🔎 FileAnalyzer.java
│   │               ├── ▶️ Main.java
│   │               ├── 🔍 ProjectScanner.java
│   │               ├── 📋 ReportGenerator.java
│   │               ├── 📄 ScanResult.java
│   │               │
│   │               └── 📂 data/
│   │                   ├── 🔄 DataTransformer.java
│   │                   ├── 🗄️ DatabaseManager.java
│   │                   ├── 📄 FindingRecord.java
│   │                   └── 💾 FindingRepository.java
│   │
│   └── 📂 test/
│
├── 📦 pom.xml
├── 🚫 .gitignore
└── 📖 README.md
```

---

## 🎯 Project Goals

Leak-Proof was developed to demonstrate how **cybersecurity and data engineering** can work together.

The project combines:

🔐 **Cybersecurity** — identifying exposed sensitive information

🔄 **Data Engineering** — building an ETL pipeline

🗄️ **Database Management** — storing structured security findings

📊 **Data Analysis** — querying and aggregating security data

---

## 👩🏽‍💻 Author

**Zethu Sanelisiwe Msomi**

Software Engineering Student | Cybersecurity & Data Engineering

---

⭐ If you find this project interesting, feel free to explore the repository!
