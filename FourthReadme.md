# Spring ORM (JPA + Hibernate) - Complete Revision Notes

## Overview

Spring ORM (Object Relational Mapping) is a Spring Framework module that simplifies database operations by integrating ORM frameworks such as Hibernate. Instead of writing SQL queries manually, developers work with Java objects (Entities), and Hibernate automatically converts them into SQL statements.

---

# Why ORM?

Without ORM:

* We write SQL manually.
* We manually map ResultSet to Java Objects.
* More boilerplate code.
* More chances of bugs.

With ORM:

* Work with Java Objects instead of SQL.
* Hibernate generates SQL automatically.
* Less code.
* Easier maintenance.
* Database-independent code.

---

# Spring ORM Architecture

```
Application
      │
      ▼
Spring ORM
      │
      ▼
JPA (EntityManager)
      │
      ▼
Hibernate
      │
      ▼
JDBC
      │
      ▼
Database
```

---

# Important Components

## 1. Entity

An Entity is a simple Java class that represents a database table.

Example:

```java
@Entity
@Table(name = "student")
public class Student {

    @Id
    private int studentId;

    private String studentName;

    private String studentCity;
}
```

### Interview Question

**What is an Entity?**

An Entity is a Java class mapped to a database table.

---

## 2. @Entity

Marks the class as a JPA Entity.

```
@Entity
```

Without this annotation Hibernate will ignore the class.

---

## 3. @Table

Used to specify the table name.

```
@Table(name="student")
```

Optional.

If omitted, Hibernate uses the class name.

---

## 4. @Id

Specifies the Primary Key.

```
@Id
private int studentId;
```

Every Entity must have one primary key.

---

## 5. @Column

Maps a field to a database column.

```
@Column(name="student_name")
private String studentName;
```

Optional if the column name matches the field name.

---

# JPA

JPA stands for **Java Persistence API**.

It is a specification.

It defines interfaces such as:

* EntityManager
* EntityManagerFactory
* Query

JPA does not perform database operations itself.

Hibernate is the implementation of JPA.

---

# Hibernate

Hibernate is the ORM framework that implements JPA.

Responsibilities:

* Generate SQL
* Manage entities
* Handle caching
* Dirty Checking
* Transaction management
* Object mapping

---

# EntityManager

EntityManager is the heart of JPA.

Responsibilities:

* Save Entity
* Find Entity
* Update Entity
* Delete Entity
* Execute JPQL

Common methods:

```
persist()
find()
merge()
remove()
createQuery()
```

---

# EntityManagerFactory

Creates EntityManager objects.

Only one EntityManagerFactory is generally created for an application.

```
EntityManagerFactory
        │
        ▼
EntityManager
```

---

# @PersistenceContext

Injects EntityManager.

```
@PersistenceContext
private EntityManager entityManager;
```

Spring automatically manages the EntityManager lifecycle.

---

# @Repository

Marks a DAO class.

Benefits:

* Component Scanning
* Exception Translation
* Better readability

---

# @Transactional

Used for Transaction Management.

```
@Transactional
```

Spring automatically

* Begins Transaction
* Executes Operation
* Commits Transaction
* Rolls back if an exception occurs

---

# CRUD Operations

## 1. Insert

Method

```
persist()
```

Code

```java
entityManager.persist(student);
```

Generated SQL

```sql
INSERT INTO student ...
```

---

## 2. Read

Method

```
find()
```

Code

```java
Student student = entityManager.find(Student.class,101);
```

Generated SQL

```sql
SELECT * FROM student WHERE studentId=101;
```

Returns

* Student Object
* null if not found

---

## 3. Update

Method

```
merge()
```

Code

```java
entityManager.merge(student);
```

Generated SQL

```sql
UPDATE student ...
```

---

## 4. Delete

Method

```
remove()
```

Code

```java
Student student = entityManager.find(Student.class,id);

entityManager.remove(student);
```

Generated SQL

```sql
DELETE FROM student ...
```

---

# Difference between persist() and merge()

| persist()          | merge()                 |
| ------------------ | ----------------------- |
| Inserts new entity | Updates existing entity |
| New Objects        | Existing Objects        |
| Generates INSERT   | Generates UPDATE        |

---

# JPQL (Java Persistence Query Language)

JPQL is an Object-Oriented Query Language.

It works with

* Entity Class
* Entity Fields

Not

* Table Name
* Column Name

Example

SQL

```sql
SELECT * FROM student;
```

JPQL

```java
FROM Student
```

---

Fetch all students

```java
String jpql="FROM Student";

List<Student> students =
entityManager.createQuery(jpql,Student.class)
.getResultList();
```

---

# SQL vs JPQL

| SQL                | JPQL                 |
| ------------------ | -------------------- |
| Table Names        | Entity Names         |
| Column Names       | Java Fields          |
| Database Dependent | Database Independent |
| Returns Rows       | Returns Objects      |

---

# getResultList() vs getSingleResult()

### getResultList()

Returns

```
List<Student>
```

Used when multiple records are expected.

---

### getSingleResult()

Returns

```
Student
```

Used when only one record is expected.

Throws an exception if no record is found.

---

# Entity Lifecycle

Every Entity goes through four states.

```
Transient
     │
persist()
     │
Managed
     │
EntityManager Closed
     │
Detached
     │
remove()
     │
Removed
```

---

## 1. Transient

* Newly created object
* Exists only in Java Memory
* Not stored in Database

Example

```java
Student student = new Student();
```

---

## 2. Managed (Persistent)

After

```java
entityManager.persist(student);
```

Hibernate starts tracking the object.

Changes are automatically synchronized with the database.

---

## 3. Detached

Entity exists

But Hibernate is no longer tracking it.

Example

* EntityManager Closed
* Context Closed

Changes are not automatically saved.

---

## 4. Removed

After

```java
entityManager.remove(student);
```

Entity is marked for deletion.

Deleted when transaction commits.

---

# Dirty Checking

One of Hibernate's most important features.

Example

```java
Student student = entityManager.find(Student.class,101);

student.setStudentCity("Delhi");
```

Hibernate detects the modification automatically.

No need to call SQL manually.

At transaction commit Hibernate generates

```sql
UPDATE student ...
```

---

# First Level Cache

Every EntityManager has its own cache.

Example

```java
Student s1 =
entityManager.find(Student.class,101);

Student s2 =
entityManager.find(Student.class,101);
```

Hibernate executes SQL only once.

Second time data is returned from memory.

Advantages

* Faster Performance
* Reduced Database Hits
* Enabled by Default

---

# First Level Cache vs Second Level Cache

| First Level          | Second Level               |
| -------------------- | -------------------------- |
| Per EntityManager    | Shared Across Sessions     |
| Enabled by Default   | Requires Configuration     |
| Built into Hibernate | External Provider Required |

---

# Complete Request Flow

```
Application
      │
      ▼
ApplicationContext
      │
      ▼
StudentDao
      │
      ▼
EntityManager
      │
      ▼
Hibernate
      │
      ▼
JDBC
      │
      ▼
MySQL
```

---

# Frequently Asked Interview Questions

### 1. What is ORM?

ORM is a technique that maps Java Objects to database tables.

---

### 2. Difference between JPA and Hibernate?

JPA is a specification.

Hibernate is an implementation of JPA.

---

### 3. What is an Entity?

A Java class mapped to a database table.

---

### 4. Why use EntityManager?

To perform CRUD operations and execute JPQL queries.

---

### 5. What is @PersistenceContext?

Used to inject EntityManager.

---

### 6. What is @Repository?

Marks DAO classes and enables exception translation.

---

### 7. What is @Transactional?

Manages database transactions automatically.

---

### 8. Difference between persist() and merge()?

persist() inserts a new entity.

merge() updates an existing entity.

---

### 9. Difference between find() and JPQL?

find() retrieves an entity by its primary key.

JPQL is used for custom queries.

---

### 10. Difference between SQL and JPQL?

SQL uses tables.

JPQL uses entity classes.

---

### 11. What is Dirty Checking?

Hibernate automatically detects modifications to managed entities and updates the database during transaction commit.

---

### 12. What is First Level Cache?

A cache maintained by EntityManager that avoids repeated database queries within the same persistence context.

---

### 13. What is Entity Lifecycle?

The four states of an entity are:

* Transient
* Managed
* Detached
* Removed

---

# Spring ORM Learning Summary

✔ Spring ORM Architecture

✔ JPA

✔ Hibernate

✔ Entity

✔ EntityManager

✔ EntityManagerFactory

✔ @PersistenceContext

✔ @Repository

✔ @Transactional

✔ CRUD Operations

✔ JPQL

✔ Entity Lifecycle

✔ Dirty Checking

✔ First Level Cache

---

# Key Takeaways

* Always work with Java Objects instead of SQL whenever possible.
* JPA is a specification, Hibernate is its implementation.
* EntityManager is the core interface for persistence operations.
* JPQL queries use Entity names and Java field names, not database table and column names.
* Transactions are managed using `@Transactional`.
* Hibernate automatically performs Dirty Checking for managed entities.
* First-Level Cache improves performance by reducing repeated database queries.
* Understanding the Entity Lifecycle is essential for writing efficient JPA/Hibernate applications and is one of the most frequently asked interview topics.
