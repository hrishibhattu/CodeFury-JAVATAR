# CodeFury-JAVATAR

**For the complete git history, please refer to github.com/hrishibhattu/CodeFury-Internal and github.com/hrishibhattu/CodeFury-Internal-2**
We kept this repo to submit our final code, and used the "Internal" repos to collaborate. You will find all our code changes, commits, branches there.

## Project Description

A full-featured Order Processing System. Provides functionality to

1. Import Products from JSON file
2. Create quotes
3. Approve quotes
4. Generate and view Invoice

## Technologies Used

- Java
- JSP
- Servlets
- Javascript
- Bootstrap 4.0.0
- HTML/CSS

## Software tools

- Eclipse JEE
- phpMyAdmin 4
- Apache Tomcat 9.0.0

## Directory Structure
```elixir
.
├── README.md
├── application
│   ├── Servers
│   │   └── Tomcat\ v9.0\ Server\ at\ localhost-config
│   │       ├── catalina.policy
│   │       ├── catalina.properties
│   │       ├── context.xml
│   │       ├── server.xml
│   │       ├── tomcat-users.xml
│   │       └── web.xml
│   ├── ToYoDo
│   │   ├── WebContent
│   │   │   ├── CSS
│   │   │   │   ├── invoiceStyle.css
│   │   │   │   ├── layout.css
│   │   │   │   └── style.css
│   │   │   ├── IMG
│   │   │   │   ├── img\ (1).gif
│   │   │   │   ├── img\ (1).png
│   │   │   │   ├── img\ (2).gif
│   │   │   │   ├── img\ (2).jpg
│   │   │   │   ├── logo_dark.png
│   │   │   │   └── logo_light.png
│   │   │   ├── JS
│   │   │   │   └── costValueAutoCalculator.js
│   │   │   ├── JSP
│   │   │   │   ├── customerLogin.jsp
│   │   │   │   ├── customerOrderList.jsp
│   │   │   │   ├── customerOrderManagement.jsp
│   │   │   │   ├── customerViewQuote.jsp
│   │   │   │   ├── employeeImportProducts.jsp
│   │   │   │   ├── employeeLogin.jsp
│   │   │   │   ├── employeeOrderList.jsp
│   │   │   │   ├── employeeOrderManagement.jsp
│   │   │   │   ├── employeeQuote.jsp
│   │   │   │   ├── index.jsp
│   │   │   │   └── invoice.jsp
│   │   │   ├── META-INF
│   │   │   │   └── MANIFEST.MF
│   │   │   └── WEB-INF
│   │   │       ├── lib
│   │   │       │   ├── hamcrest-2.2.jar
│   │   │       │   ├── hamcrest-core-2.2.jar
│   │   │       │   ├── json-simple-1.1.1.jar
│   │   │       │   ├── jstl-1.2.jar
│   │   │       │   ├── junit-4.13.2.jar
│   │   │       │   └── mysql-connector-java-8.0.19.jar
│   │   │       ├── modal
│   │   │       │   └── quote.jsp
│   │   │       ├── nav
│   │   │       │   ├── customerDashboard.html
│   │   │       │   ├── customerSidebar.html
│   │   │       │   ├── dashboardNav.html
│   │   │       │   ├── indexNav.html
│   │   │       │   └── sidebarNav.html
│   │   │       └── web.xml
│   │   ├── build
│   │   │   └── classes
│   │   │       └── com
│   │   │           └── toyodo
│   │   │               ├── controller
│   │   │               │   ├── CustomerController.class
│   │   │               │   ├── EmployeeController.class
│   │   │               │   ├── EmployeeImportProducts.class
│   │   │               │   ├── LoginController.class
│   │   │               │   └── LogoutController.class
│   │   │               ├── dao
│   │   │               │   ├── CustomerDAO.class
│   │   │               │   ├── EmployeeDAO.class
│   │   │               │   ├── ExternalDAO.class
│   │   │               │   └── impl
│   │   │               │       ├── CustomerDAOImpl.class
│   │   │               │       ├── EmployeeDAOImpl.class
│   │   │               │       └── ExternalDAOImpl.class
│   │   │               ├── factory
│   │   │               │   ├── CustomerDAOFactory.class
│   │   │               │   ├── EmployeeDAOFactory.class
│   │   │               │   └── ExternalDAOFactory.class
│   │   │               ├── model
│   │   │               │   ├── Company.class
│   │   │               │   ├── Customer.class
│   │   │               │   ├── Employee.class
│   │   │               │   ├── Invoice.class
│   │   │               │   ├── Order.class
│   │   │               │   └── Products.class
│   │   │               ├── notification
│   │   │               │   └── Notify.class
│   │   │               ├── service
│   │   │               │   ├── CustomerService.class
│   │   │               │   ├── EmployeeService.class
│   │   │               │   ├── ExternalService.class
│   │   │               │   └── impl
│   │   │               │       ├── CustomerServiceImpl.class
│   │   │               │       ├── EmployeeServiceImpl.class
│   │   │               │       └── ExternalServiceImpl.class
│   │   │               └── utils
│   │   │                   ├── DBConnection.class
│   │   │                   └── dbresource.properties
│   │   └── src
│   │       └── com
│   │           └── toyodo
│   │               ├── controller
│   │               │   ├── CustomerController.java
│   │               │   ├── EmployeeController.java
│   │               │   ├── EmployeeImportProducts.java
│   │               │   ├── LoginController.java
│   │               │   └── LogoutController.java
│   │               ├── dao
│   │               │   ├── CustomerDAO.java
│   │               │   ├── EmployeeDAO.java
│   │               │   ├── ExternalDAO.java
│   │               │   └── impl
│   │               │       ├── CustomerDAOImpl.java
│   │               │       ├── EmployeeDAOImpl.java
│   │               │       └── ExternalDAOImpl.java
│   │               ├── factory
│   │               │   ├── CustomerDAOFactory.java
│   │               │   ├── EmployeeDAOFactory.java
│   │               │   └── ExternalDAOFactory.java
│   │               ├── model
│   │               │   ├── Company.java
│   │               │   ├── Customer.java
│   │               │   ├── Employee.java
│   │               │   ├── Invoice.java
│   │               │   ├── Order.java
│   │               │   └── Products.java
│   │               ├── notification
│   │               │   └── Notify.java
│   │               ├── service
│   │               │   ├── CustomerService.java
│   │               │   ├── EmployeeService.java
│   │               │   ├── ExternalService.java
│   │               │   └── impl
│   │               │       ├── CustomerServiceImpl.java
│   │               │       ├── EmployeeServiceImpl.java
│   │               │       └── ExternalServiceImpl.java
│   │               └── utils
│   │                   ├── DBConnection.java
│   │                   └── dbresource.properties
│   └── toyodo.sql
├── resources
│   ├── About\ the\ project.docx
│   ├── Class\ Diagram.png
│   ├── DFD.png
│   ├── Database\ Schema.jpeg
│   ├── ER\ Diagram.png
│   ├── Test\ Case\ Document.xlsx
│   └── Use\ Case\ Diagram.png
└── video
    ├── demonstration
```

## Installation instructions

1. Clone Project on machine.
2. Import project directory `./application/ToYoDo` into Eclipse.
3. Configure Buildpath - add all the jars from `.application/ToYoDo/WebContent/WEB-INF/lib/jstl-1.2.jar` as Add External Jar
4. Create server to deploy code.
