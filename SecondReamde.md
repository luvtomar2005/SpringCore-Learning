# Spring Core - Bean Lifecycle Methods

## Overview

In Spring Framework, every object managed by the **IoC (Inversion of Control) Container** is called a **Bean**.

A Spring Bean is not simply created and forgotten. Instead, it passes through a well-defined **lifecycle** managed by the Spring Container.

Understanding the Bean Lifecycle is essential because many real-world applications need to perform initialization tasks after bean creation and cleanup tasks before bean destruction.

This topic is one of the most frequently asked Spring Core interview topics.

---

# Why Do We Need Bean Lifecycle Methods?

Consider a bean representing a database connection.

When the bean is created, we may want to:

* Open the database connection
* Load configuration
* Allocate memory
* Initialize cache
* Start background threads

When the application shuts down, we may want to:

* Close the database connection
* Release memory
* Flush cache
* Stop background threads

Without lifecycle methods, developers would need to manually manage all these operations.

Spring automates this process.

---

# What is a Bean Lifecycle?

The Bean Lifecycle is the sequence of events that occurs from the moment a bean is created until it is destroyed by the Spring Container.

```
Spring Container Starts
        │
        ▼
Bean Instantiation
(Constructor Called)
        │
        ▼
Dependency Injection
(Setter / Constructor Injection)
        │
        ▼
Initialization
(Custom Initialization Logic)
        │
        ▼
Bean Ready For Use
        │
Application Running
        │
        ▼
Bean Destruction
(Cleanup Logic)
```

---

# Complete Bean Lifecycle Sequence

The exact order followed by Spring is:

```
1. Spring Container Starts

2. Bean Object Created
   (Constructor Executes)

3. Dependencies Injected

4. Initialization Callback Executes

5. Bean Ready To Use

6. Application Executes

7. Container Shuts Down

8. Destruction Callback Executes
```

### Interview Tip

Many beginners think:

```
Constructor
↓

Bean Ready
```

This is **incorrect**.

The correct order is:

```
Constructor
↓

Dependency Injection
↓

Initialization Callback
↓

Bean Ready
```

---

# Three Ways to Implement Bean Lifecycle Methods

Spring provides three different approaches.

```
Bean Lifecycle

        │

────────────────────────────────

1. XML Configuration

2. Spring Interfaces

3. Annotations

────────────────────────────────
```

---

# Method 1 - XML Configuration

Uses:

* `init-method`
* `destroy-method`

Example:

```xml
<bean id="student"
      class="com.demo.Student"
      init-method="init"
      destroy-method="destroy"/>
```

Spring internally performs:

```
Create Bean

↓

Inject Dependencies

↓

Call init()

↓

Bean Ready

↓

Call destroy()
```

## Advantages

* Bean remains a Plain Old Java Object (POJO)
* No dependency on Spring interfaces
* Good for understanding Spring internals

## Disadvantages

* Requires XML configuration
* Rarely used in modern Spring Boot projects

---

# Method 2 - Spring Interfaces

Spring provides two interfaces.

```
InitializingBean

DisposableBean
```

Methods:

```
afterPropertiesSet()

destroy()
```

Example:

```java
public class Employee
implements InitializingBean, DisposableBean
```

Spring automatically calls

```
afterPropertiesSet()

destroy()
```

## Advantages

* No XML lifecycle configuration

## Disadvantages

* Tight coupling with Spring Framework
* Business classes depend on Spring APIs
* Not recommended for modern applications

---

# Method 3 - Annotation Based Lifecycle

Uses:

```
@PostConstruct

@PreDestroy
```

This is the most commonly used approach in Spring Boot.

Example:

```java
@PostConstruct
public void init() {

}

@PreDestroy
public void destroy() {

}
```

Spring scans the class.

Whenever it finds these annotations, it automatically invokes the methods.

---

# Internal Working of @PostConstruct

Suppose Spring creates a bean.

```
new Person()

↓

Dependency Injection

↓

Scan Methods

↓

Found @PostConstruct ?

↓

Yes

↓

Execute Method

↓

Bean Ready
```

Similarly,

```
Application Closing

↓

Scan Methods

↓

Found @PreDestroy

↓

Execute Method

↓

Bean Destroyed
```

---

# Why Not Initialize Inside Constructor?

Many beginners write:

```java
public Student() {

    connectDatabase();

}
```

This is not recommended.

Why?

Because dependency injection has **not happened yet**.

Example:

```java
private Database database;
```

During constructor execution

```
database == null
```

Only after dependency injection does Spring call:

```
@PostConstruct
```

At this point every dependency is available.

This is exactly why `@PostConstruct` exists.

---

# Why registerShutdownHook()?

This is one of the most common interview questions.

In standalone Spring applications, simply ending the `main()` method does not guarantee that destruction callbacks are executed.

Calling:

```java
context.registerShutdownHook();
```

registers a JVM Shutdown Hook.

When the JVM terminates,

Spring gets a chance to execute

* destroy-method
* destroy()
* @PreDestroy

Without it, cleanup methods may never execute.

---

# Spring 6 Important Change

Older tutorials (Spring 5) use:

```java
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
```

In Spring Framework 6 and Spring Boot 3,

these packages were moved to Jakarta.

Correct imports:

```java
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
```

Also add:

```xml
<dependency>
    <groupId>jakarta.annotation</groupId>
    <artifactId>jakarta.annotation-api</artifactId>
    <version>3.0.0</version>
</dependency>
```

---

# Comparison of All Three Approaches

| Feature               | XML             | Interfaces      | Annotations                |
| --------------------- | --------------- | --------------- | -------------------------- |
| XML Required          | ✅ Yes           | ❌ No            | ❌ No                       |
| Spring API Dependency | ❌ No            | ✅ Yes           | ❌ No (Jakarta annotations) |
| Easy To Read          | Medium          | Medium          | Excellent                  |
| Used In Spring Boot   | Rarely          | Rarely          | Very Frequently            |
| Preferred Today       | Legacy Projects | Legacy Projects | Modern Applications        |

---

# Lifecycle Execution Order

```
Constructor

↓

Dependency Injection

↓

@PostConstruct
(init-method / afterPropertiesSet())

↓

Bean Ready

↓

Application Running

↓

@PreDestroy
(destroy-method / destroy())

↓

Bean Removed
```

---

# Common Mistakes

### Mistake 1

Initializing resources inside constructor.

Correct approach:

Use `@PostConstruct`.

---

### Mistake 2

Forgetting

```java
context.registerShutdownHook();
```

Result:

Destroy methods never execute.

---

### Mistake 3

Using

```java
javax.annotation.PostConstruct
```

in Spring 6.

Correct:

```java
jakarta.annotation.PostConstruct
```

---

### Mistake 4

Thinking constructor means bean is initialized.

Incorrect.

Initialization happens only after dependency injection.

---

# Interview Questions

### What is Bean Lifecycle?

The complete sequence of stages through which a Spring Bean passes from creation to destruction.

---

### What are the three ways to implement Bean Lifecycle?

1. XML (`init-method`, `destroy-method`)
2. Spring Interfaces (`InitializingBean`, `DisposableBean`)
3. Annotations (`@PostConstruct`, `@PreDestroy`)

---

### Which lifecycle method executes first?

Constructor.

---

### Which executes after dependency injection?

* init-method
* afterPropertiesSet()
* @PostConstruct

---

### Which executes before bean destruction?

* destroy-method
* destroy()
* @PreDestroy

---

### Why is `@PostConstruct` preferred over constructor initialization?

Because dependency injection has already completed, making all injected dependencies available.

---

### Why is `@PreDestroy` useful?

It allows cleanup of resources before the bean is removed from memory.

---

### Why is `registerShutdownHook()` necessary?

It ensures Spring receives a JVM shutdown event and executes destruction callbacks before the application exits.

---

### Which approach should be used in Spring Boot?

Annotation-based lifecycle methods (`@PostConstruct` and `@PreDestroy`) are the preferred approach because they are concise, readable, and do not require implementing Spring-specific interfaces.

---

# Key Takeaways

* Every Spring Bean has a lifecycle managed by the IoC Container.
* Bean creation is not the end of initialization—dependency injection occurs before initialization callbacks.
* Spring supports three lifecycle mechanisms: XML, interfaces, and annotations.
* XML and interface-based approaches are still important for understanding legacy code and interview questions.
* Annotation-based lifecycle management is the standard approach in modern Spring Boot development.
* `registerShutdownHook()` is essential in standalone applications if you want destruction callbacks to execute.
* In Spring 6+, always use the `jakarta.annotation` package instead of the older `javax.annotation` package.
