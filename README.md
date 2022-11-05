# PdfGen

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)

REST API for creating PDF files from JSON templates.

[Live demo](https://pdfgen-demo.fly.dev/swagger-ui/index.html)


## 📖 Table of Contents

* [General Information](https://github.com/mslabek/pdfgen#-general-information)
* [Features](https://github.com/mslabek/pdfgen#-features)
* [Usage](https://github.com/mslabek/pdfgen#-usage)
* [Tech stack](https://github.com/mslabek/pdfgen#-tech-stack)
* [Road map](https://github.com/mslabek/pdfgen#%EF%B8%8F-road-map)
* [Project status](https://github.com/mslabek/pdfgen#-project-status)
* [License](https://github.com/mslabek/pdfgen#%EF%B8%8F-license)


## 📝 General Information

The application allows for creation PDF files by consuming a template and the data to fill the template. The purpose of
this is to allow the user to quickly create documents where the only thing that changes is text data. This can be useful
for people who write a lot of contracts or issue invoices.

The functionality is exposed through REST API and every request is formatted as JSON.


## ✨ Features

- Pdf generation from JSON templates
- HTML-like JSON templates:
    - Hierarchical structure with style inheritance
    - Very basic styling options: text alignment, font size
    - Element types that decide how the element is rendered - for example: text block and line break
    - Simple text templating
- Persisting templates in a database


## 🎡 Usage

1. The easiest and recommended way to use the application is by the online demo.
2. The online demo contains a couple of templates. To view the available templates send a GET request to "/template"
   endpoint. The most relevant information is the placeholders - these are the values you need to provide in the PDF
   creation request.
3. After adding a template or choosing an already persisted template send a POST request to "/pdf/{templateId}" endpoint
   specifying the id of the template in the parameter and the values in the request body in JSON.
4. In online demo's Swagger interface, standard responses with pdf content type header aren't received
   properly. Try to use the additional endpoints "pdf/swagger/" - they are the same as other endpoints for pdf
   generation, but the response has "application/octet-stream" content header. It still doesn't get displayed in 
   Swagger frame, but at least you can download the file and open it as pdf.


## 🛠 Tech Stack

Most important technologies used:

- Spring Boot:
    - Spring MVC
    - Spring Data JPA
- Build tool: Gradle
- Documentation tool: Swagger
- Database: H2
- Lombok
- Validation: Hibernate Validator
- Demo deployment:
    - Docker (the image is built with spring boot gradle plugin and deployed on Docker Hub)
    - [Fly.io](https://fly.io)


## 🏗️ Road Map

Here are some most important features / improvements that need to be implemented:

- [ ] Exception handling
- [ ] Better form validation
- [ ] More styling functionality
- [ ] Testing
- [ ] Documentation


## 🌱 Project Status

Project is in development.


## ⚖️ License

This project is open source and available under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0).