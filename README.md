# Prawko Server

## Table of Contents

* [Table of Contents](#table-of-contents)
* [Overview](#overview)
* [Installation](#installation)
* [Usage](#usage)

---

## Overview

**Prawko Server** is a REST API Server created with Java 21 and Spring 6 for Prawko projects.

---

## Installation

Clone repository

`git@github.com:turczak/prawko-server.git`

`cd prawko-server`

By default, application is using H2 database.
It can be run with MySQL and MariaDB databases.
To configure database connection edit this [file](https://github.com/turczak/prawko-server/blob/main/src/main/resources/application.properties)
before build.

Test

`./mvnw clean install`

Build with Maven

`./mvnw package`

Run

`./mvnw spring-boot:run`

## Usage

Base URL: `http://localhost:8080/`

### Endpoints

* `/questions`
    + `POST` upload csv file with questions
