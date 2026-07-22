# Spring MVC Notes

## Overview

Spring MVC (Model-View-Controller) is a web framework provided by the Spring Framework for building Java-based web applications. It follows the MVC architectural pattern, separating business logic, presentation logic, and request handling to create maintainable and scalable applications.

Spring MVC is built on top of the Servlet API and uses the powerful DispatcherServlet as its Front Controller.

---

# MVC Architecture

MVC stands for:

- **Model** → Contains application data and business logic.
- **View** → Responsible for presenting data to the user (JSP, Thymeleaf, HTML, etc.).
- **Controller** → Receives HTTP requests, processes them, and returns the appropriate view.

```
Browser
    │
HTTP Request
    │
    ▼
DispatcherServlet
    │
    ▼
Controller
    │
    ▼
Service Layer
    │
    ▼
Repository / DAO
    │
    ▼
Database
    │
    ▲
Model
    │
    ▼
View Resolver
    │
    ▼
JSP / View
    │
HTTP Response
    ▼
Browser
```

---

# DispatcherServlet

DispatcherServlet is the Front Controller of Spring MVC.

Responsibilities:

- Receives every incoming request.
- Finds the appropriate controller.
- Calls the controller method.
- Stores data in the Model.
- Uses View Resolver to locate the JSP.
- Returns the generated response.

---

# Project Structure

Typical Spring MVC Project:

```
src
 └── main
      ├── java
      │      └── controller
      │      └── model
      │      └── service
      └── webapp
             ├── WEB-INF
             │      ├── views
             │      ├── web.xml
             │      └── frontcontroller-servlet.xml
             └── resources
```

---

# web.xml

Registers DispatcherServlet.

Example:

```xml
<servlet>
    <servlet-name>frontcontroller</servlet-name>
    <servlet-class>
        org.springframework.web.servlet.DispatcherServlet
    </servlet-class>
</servlet>

<servlet-mapping>
    <servlet-name>frontcontroller</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

---

# Spring Configuration (frontcontroller-servlet.xml)

Responsible for configuring Spring MVC.

Common configuration:

- Component Scanning
- View Resolver
- Bean Configuration

Example:

```xml
<context:component-scan base-package="com.springmvc"/>

<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/views/"/>
    <property name="suffix" value=".jsp"/>
</bean>
```

---

# View Resolver

Converts logical view names into actual JSP files.

Example:

Controller:

```java
return "home";
```

View Resolver converts it to:

```
/WEB-INF/views/home.jsp
```

---

# Controller

Controller handles incoming HTTP requests.

Example:

```java
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home() {

        return "home";
    }
}
```

---

# @RequestMapping

Maps URLs to controller methods.

Example:

```java
@RequestMapping("/login")
```

URL:

```
localhost:8080/project/login
```

---

# Model

Used to transfer data from Controller to View.

Example:

```java
model.addAttribute("name","Luv");
```

JSP:

```jsp
${name}
```

---

# ModelMap

Alternative implementation for storing data.

Example:

```java
ModelMap map = new ModelMap();
map.addAttribute("course","Spring");
```

---

# ModelAndView

Stores both:

- Model Data
- View Name

Example:

```java
ModelAndView mv = new ModelAndView();

mv.addObject("name","Luv");
mv.setViewName("home");
```

---

# Model vs ModelMap vs ModelAndView

| Feature | Model | ModelMap | ModelAndView |
|----------|-------|----------|--------------|
| Stores Data | ✔ | ✔ | ✔ |
| Stores View | ✘ | ✘ | ✔ |
| Type | Interface | Class | Class |
| Preferred | ✔ | ✔ | Used when View + Data together |

---

# Expression Language (EL)

Used inside JSP to display model data.

Example:

```jsp
${student.name}

${course}

${marks}
```

---

# @RequestParam

Reads query parameters.

Example:

```
/login?id=10
```

```java
@RequestParam("id")
```

---

# @ModelAttribute

Binds HTML Form data directly into Java Objects.

Example:

```java
@PostMapping("/save")
public String save(
        @ModelAttribute Student student)
```

---

# Form Handling

Spring MVC automatically binds form fields with Java Beans.

Flow:

```
JSP Form

↓

Controller

↓

Java Object

↓

Database
```

---

# @PathVariable

Reads values directly from the URL.

Example:

```
/student/10
```

```java
@PathVariable
```

---

# Redirect

Redirects browser to another URL.

```java
return "redirect:/home";
```

---

# Forward

Forwards request internally.

```java
return "forward:/home";
```

---

# Multiple Controllers

Spring supports multiple controllers.

Example:

```
HomeController

StudentController

EmployeeController

ProductController
```

DispatcherServlet automatically finds the correct controller.

---

# Exception Handling

Local Exception Handling:

```java
@ExceptionHandler
```

Global Exception Handling:

```java
@ControllerAdvice
```

Advantages:

- Centralized error handling.
- Clean controllers.
- Better user experience.

---

# File Upload

Spring MVC supports Multipart File Upload.

Annotation:

```java
MultipartFile
```

Requires:

```
multipartResolver
```

---

# Validation

Uses Bean Validation (JSR-303).

Example:

```java
@NotNull

@NotBlank

@Email

@Size

@Min

@Max
```

Controller:

```java
public String save(
@Valid Student student,
BindingResult result)
```

---

# Spring MVC Annotations

Common annotations:

```
@Controller

@RestController

@RequestMapping

@GetMapping

@PostMapping

@PutMapping

@DeleteMapping

@PathVariable

@RequestParam

@ModelAttribute

@ResponseBody

@ResponseStatus

@ExceptionHandler

@ControllerAdvice

@Valid
```

---

# REST in Spring MVC

Spring MVC also supports REST APIs.

Example:

```java
@RestController
```

Returns:

```
JSON

XML
```

instead of JSP pages.

---

# Request Flow

```
Browser

↓

Tomcat

↓

DispatcherServlet

↓

Handler Mapping

↓

Controller

↓

Service

↓

DAO

↓

Database

↓

Model

↓

View Resolver

↓

JSP

↓

Browser
```

---

# Advantages of Spring MVC

- Loose Coupling
- Separation of Concerns
- Easy Testing
- Annotation Based Configuration
- Flexible View Technologies
- Easy Form Handling
- Powerful Validation
- Exception Handling
- REST Support
- Integration with Spring Ecosystem

---

# Common Interview Questions

### What is Spring MVC?

A web framework based on the MVC pattern for building Java web applications.

---

### What is DispatcherServlet?

The Front Controller responsible for receiving every HTTP request and forwarding it to the appropriate controller.

---

### Difference between Model and ModelAndView?

Model stores only data.

ModelAndView stores both data and view information.

---

### Difference between @RequestParam and @PathVariable?

@RequestParam reads query parameters.

```
/student?id=10
```

@PathVariable reads values from URL path.

```
/student/10
```

---

### Why is View Resolver required?

To convert logical view names into actual JSP pages.

---

### Why is DispatcherServlet called Front Controller?

Because every request passes through DispatcherServlet before reaching the controller.

---

### What is @ControllerAdvice?

A global exception handling mechanism shared across multiple controllers.

---

### Difference between Redirect and Forward?

Redirect creates a new request.

Forward uses the same request internally.

---

### What is @ModelAttribute?

Automatically binds request data to Java objects.

---

### Difference between Spring MVC and Spring Boot?

Spring MVC is a web framework.

Spring Boot is an opinionated framework that simplifies Spring development by providing auto-configuration and embedded servers.

---

# Key Takeaways

- Spring MVC follows the MVC design pattern.
- DispatcherServlet acts as the Front Controller.
- Controllers process HTTP requests.
- Models transfer data to Views.
- View Resolver maps logical names to JSPs.
- Form handling is simplified using @ModelAttribute.
- Validation is handled using JSR-303 annotations.
- Exception handling can be local or global.
- REST APIs can be built using @RestController.
- Spring MVC provides a clean separation of concerns, making applications scalable, testable, and maintainable.

---

## Status

✅ Spring MVC Completed

**Next Topic:** Spring Boot