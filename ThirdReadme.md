# Spring Core - Autowiring (XML & Annotation Based)

## Objective

The objective of this module is to understand how Spring automatically injects dependencies between objects using XML configuration and annotations. This topic is one of the core concepts of the Spring Framework and serves as the foundation for Dependency Injection (DI) in Spring Boot.

---

# Prerequisites

Before learning Autowiring, you should already understand:

* IoC (Inversion of Control)
* Dependency Injection (DI)
* Spring Beans
* Bean Configuration using XML
* Setter Injection
* Constructor Injection

---

# What is Autowiring?

Autowiring is a feature provided by the Spring IoC Container that automatically injects dependencies into a bean.

Instead of manually specifying every dependency in the XML configuration, Spring can identify and inject the required bean automatically.

### Without Autowiring

```xml
<bean id="employee" class="com.springcore.autowire.Employee">
    <property name="address" ref="address"/>
</bean>
```

Here, the developer explicitly tells Spring which bean to inject.

---

### With Autowiring

```xml
<bean id="employee"
      class="com.springcore.autowire.Employee"
      autowire="byType"/>
```

Spring itself determines which dependency should be injected.

---

# Why Do We Need Autowiring?

Without autowiring:

* Large XML files
* Repetitive configuration
* Difficult maintenance
* Easy to make mistakes

Autowiring reduces manual configuration and improves maintainability.

---

# Advantages

* Less XML configuration
* Faster development
* Easier maintenance
* Cleaner configuration
* Reduces human error

---

# Disadvantages

* Ambiguity when multiple beans of the same type exist
* Harder to understand in very large projects if not used properly
* Requires understanding of Spring's bean resolution process

---

# XML Autowiring Modes

Spring XML provides multiple autowiring modes.

## 1. no (Default)

No automatic injection.

Developer must configure everything manually.

Example:

```xml
<property name="address" ref="address"/>
```

---

## 2. byName

### Working Principle

Spring matches:

```
Property Name
        ↓
Bean ID
```

Example:

Employee class

```java
private Address address;
```

XML

```xml
<bean id="address"
      class="com.springcore.autowire.Address"/>
```

Spring finds:

```
address (property)
        =
address (bean id)
```

Injection succeeds.

---

### Requirements

* Setter method must exist.
* Bean ID must exactly match the property name.

---

### If Names Do Not Match

Example

Property

```
address
```

Bean ID

```
homeAddress
```

Injection fails.

The dependency remains `null`.

---

### Important Points

* Uses Setter Injection
* Matches Property Name ↔ Bean ID
* Requires setter method

---

# 3. byType

### Working Principle

Spring ignores bean IDs.

It matches:

```
Property Type
        ↓
Bean Type
```

Example

```java
private Address address;
```

XML

```xml
<bean id="homeAddress"
      class="com.springcore.autowire.Address"/>
```

Although bean id is `homeAddress`, Spring injects it because its type is `Address`.

---

### Requirements

* Setter method required
* Only one bean of the required type should exist

---

### What if Multiple Beans Exist?

Example

```
homeAddress

officeAddress
```

Both are Address beans.

Spring cannot determine which one should be injected.

Result

```
NoUniqueBeanDefinitionException
```

---

### Important Points

* Bean ID does NOT matter
* Type matters
* Uses Setter Injection

---

# 4. constructor

### Working Principle

Spring injects dependencies through constructor parameters.

Example

```java
public Employee(Address address)
```

XML

```xml
<bean id="employee"
      class="com.springcore.autowire.Employee"
      autowire="constructor"/>
```

Spring automatically searches for an Address bean and calls

```java
new Employee(address)
```

---

### Advantages

* Object becomes fully initialized immediately
* Required dependencies cannot be forgotten
* Preferred approach in modern Spring Boot

---

# Comparison of XML Autowiring Modes

| Mode        | Injection Type | Matching Rule                          |
| ----------- | -------------- | -------------------------------------- |
| no          | Manual         | Developer specifies dependency         |
| byName      | Setter         | Property Name ↔ Bean ID                |
| byType      | Setter         | Property Type ↔ Bean Type              |
| constructor | Constructor    | Constructor Parameter Type ↔ Bean Type |

---

# XML Autowiring Flow

```
Spring Starts

↓

Reads XML

↓

Creates Beans

↓

Checks autowire mode

↓

Finds dependency

↓

Injects dependency

↓

Bean Ready
```

---

# Annotation-Based Configuration

XML configuration becomes difficult in large applications.

Spring introduced annotations to reduce XML.

Instead of creating beans in XML, we annotate Java classes.

---

# @Component

Marks a class as a Spring Bean.

Example

```java
@Component
public class Address {
}
```

Equivalent XML

```xml
<bean id="address"
      class="Address"/>
```

---

# Component Scanning

XML

```xml
<context:component-scan
        base-package="com.springcore.annotation"/>
```

Spring scans every class inside the package.

Whenever it finds

```
@Component
```

it creates a bean automatically.

No `<bean>` declaration is required.

---

# @Autowired

Used to automatically inject dependencies.

Example

```java
@Autowired
private Address address;
```

Spring searches the IoC Container for an Address bean.

---

## Internal Working

```
Create Employee Bean

↓

Find @Autowired

↓

Need Address Bean

↓

Search IoC Container

↓

Inject Address Bean
```

---

# Does @Autowired Work by Name?

No.

It primarily works by **Type**.

Example

```java
@Autowired
private Address xyz;
```

Even though the field name is `xyz`, Spring injects an `Address` bean because it looks at the field type.

---

# What Happens When Multiple Beans Exist?

Suppose

```
HomeAddress

OfficeAddress
```

Both implement or extend `Address`.

Spring now finds two possible beans.

It cannot decide which one to inject.

Result

```
NoUniqueBeanDefinitionException
```

---

# @Qualifier

Used to resolve ambiguity when multiple beans of the same type exist.

Example

```java
@Autowired
@Qualifier("homeAddress")
private Address address;
```

Spring now injects only the bean named `homeAddress`.

---

# Default Bean Name

Spring converts

```
HomeAddress
```

to

```
homeAddress
```

Similarly

```
EmployeeService
```

becomes

```
employeeService
```

---

# Custom Bean Name

```java
@Component("myAddress")
public class HomeAddress {
}
```

Then

```java
@Autowired
@Qualifier("myAddress")
private Address address;
```

---

# @Autowired vs @Qualifier

| @Autowired                    | @Qualifier                      |
| ----------------------------- | ------------------------------- |
| Requests dependency injection | Selects a specific bean         |
| Works primarily by type       | Works by bean name              |
| Can be used alone             | Used together with `@Autowired` |

---

# XML vs Annotation Configuration

| XML Configuration          | Annotation Configuration |
| -------------------------- | ------------------------ |
| `<bean>`                   | `@Component`             |
| `<property ref="">`        | `@Autowired`             |
| `autowire="byType"`        | `@Autowired`             |
| XML-based bean declaration | Component scanning       |

---

# Common Exceptions

## FileNotFoundException

```
class path resource
applicationContext.xml
cannot be opened
```

### Causes

* XML file not inside `src/main/resources`
* Wrong filename
* Resources folder not marked correctly
* Wrong file path passed to `ClassPathXmlApplicationContext`

---

## NoUniqueBeanDefinitionException

Cause

More than one bean of the required type exists.

Solution

* `@Qualifier`
* `@Primary` (to be studied later)

---

# Frequently Asked Interview & KBA Questions

### 1. What is Autowiring?

Automatic dependency injection performed by the Spring IoC Container.

---

### 2. What are the XML autowiring modes?

* no
* byName
* byType
* constructor

---

### 3. Difference between byName and byType?

| byName                          | byType                            |
| ------------------------------- | --------------------------------- |
| Matches Property Name ↔ Bean ID | Matches Property Type ↔ Bean Type |
| Bean ID matters                 | Bean Type matters                 |

---

### 4. Which injection style is preferred today?

Constructor Injection.

---

### 5. What does `@Component` do?

Registers a class as a Spring-managed bean.

---

### 6. What does `@Autowired` do?

Automatically injects a dependency from the IoC container.

---

### 7. Does `@Autowired` work by name?

No. It primarily works by type.

---

### 8. Why is `@Qualifier` needed?

To specify which bean should be injected when multiple beans of the same type exist.

---

### 9. What is Component Scanning?

Spring scans packages for stereotype annotations (`@Component`, `@Service`, `@Repository`, `@Controller`) and automatically registers those classes as beans.

---

### 10. Which topics are most important for the Cognizant KBA?

* Dependency Injection (DI)
* IoC Container
* XML Bean Configuration
* XML Autowiring (`byName`, `byType`, `constructor`)
* `@Component`
* Component Scanning
* `@Autowired`
* `@Qualifier`
* Common Spring Exceptions (`NoUniqueBeanDefinitionException`, resource loading issues)

---

# Key Takeaways

* Autowiring reduces manual dependency configuration.
* XML autowiring is useful for understanding how Spring resolves dependencies.
* `byName` matches property name with bean ID.
* `byType` matches property type with bean type.
* Constructor injection is generally preferred over setter injection.
* `@Component` registers a class as a Spring bean.
* `@Autowired` injects dependencies automatically, primarily by type.
* `@Qualifier` resolves ambiguity when multiple candidate beans exist.
* Component scanning removes the need for explicit `<bean>` definitions.
* These concepts form the foundation of Spring Boot's dependency injection model.
