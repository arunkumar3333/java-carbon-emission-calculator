# Carbon Emission Calculator

A full-stack web application that helps users calculate and monitor carbon emissions generated from transportation activities.  
The system allows users to register, log in, upload Excel files containing travel data, and view carbon emission results through an interactive dashboard.

---

## Project Overview

The **Carbon Emission Calculator** is designed to measure and analyze carbon emissions produced by different modes of transportation.  
It provides a simple platform where users can:

- Register and log in securely
- Upload Excel files containing travel details
- Automatically calculate carbon emissions
- Store data in the database
- View total and categorized emissions on the dashboard
- Visualize results using charts

This project is useful for understanding transportation-related carbon footprint and promoting environmental awareness.

---

## Purpose of the Project

The main purpose of this project is to:

- Calculate carbon emissions from transportation data
- Help users track their travel-related environmental impact
- Provide a dashboard for better analysis and visualization
- Demonstrate full-stack web application development using Java technologies

---

## Features

- User Registration
- User Login and Logout
- Session Management
- Excel File Upload
- Carbon Emission Calculation
- Travel Activity Storage
- Carbon Emission Record Storage
- Dashboard with Total Emission
- Category-wise Emission Display
- Combined Emission Analysis
- Charts using Chart.js

---

## Technologies Used

### Frontend
- HTML5
- CSS3
- JavaScript
- AngularJS
- Chart.js

### Backend
- Java
- Java Servlets
- Hibernate ORM

### Database
- MySQL

### Libraries / Frameworks
- Apache POI
- Hibernate
- AngularJS
- Chart.js

### Server
- Apache Tomcat

### Development Tools
- Eclipse IDE
- MySQL Workbench

---
## Screenshots   ✅ (ADD HERE)

![alt text](download.jpg)
## Project Modules

### 1. Registration Module
Allows a new user to create an account by entering:
- Name
- Email
- Password
- Country

### 2. Login Module
Allows registered users to log in using:
- Email
- Password

After successful login, a session is created and the user is redirected to the dashboard.

### 3. Dashboard Module
The dashboard displays:
- Uploaded travel data
- Total carbon emission
- Category-wise carbon emission
- Combined emission analysis
- Pie charts for visualization

### 4. Excel Upload Module
Users upload an Excel file containing transportation details.  
The backend reads the file using Apache POI, calculates carbon emissions, and stores the data in the database.

### 5. Logout Module
Destroys the session and redirects the user to the login page.

---

## How the Project Works

### Step 1: User Registration
The user enters details on the registration page.  
AngularJS sends the data to the backend servlet.  
The backend stores the user details in MySQL using Hibernate.

### Step 2: User Login
The user enters login credentials.  
The backend validates the user from the database.  
If valid, a session is created and the user is redirected to the dashboard.

### Step 3: Upload Excel File
The user selects an Excel file and clicks **Calculate Carbon Emission**.  
AngularJS sends the file as `FormData` to the backend.  
The backend reads the file using Apache POI.

### Step 4: Carbon Calculation
For each row in the Excel file:
- Travel data is extracted
- Emission factor is identified based on vehicle type
- Carbon emission is calculated
- Travel and emission data are stored in MySQL

### Step 5: Dashboard Update
After processing the file:
- Total carbon emission is calculated
- Category-wise results are fetched
- Combined emission data is fetched
- Charts and tables are updated dynamically on the dashboard

### Step 6: Logout
When the user clicks logout, the session is invalidated and the user is redirected to the login page.

---

## Database Tables

### 1. users
Stores user registration details.

**Fields may include:**
- id
- name
- email
- password
- country

### 2. travel_activity
Stores uploaded travel details.

**Fields may include:**
- id
- user_id
- from_city
- to_city
- vehicle_type
- number_of_vehicles
- km_travelled
- travel_time_hrs
- activity_date

### 3. carbon_record
Stores calculated carbon emission values.

**Fields may include:**
- id
- user_id
- activity_id
- emission_factor
- co2_emission
- calculation_date
- batch_id

---

## Prerequisites

Before running this project, make sure the following software is installed:

- Java JDK 8 or above
- Eclipse IDE (Enterprise Edition preferred)
- Apache Tomcat Server
- MySQL Server
- MySQL Workbench
- Maven (if your project uses Maven dependencies)

---

## Setup and Installation Guide

### 1. Clone or Download the Project
Download the project ZIP file or clone from GitHub:

```bash
git clone https://github.com/arunkumar3333/java-carbon-emission-calculator.git
2. Import Project into Eclipse
Open Eclipse

Go to File > Import

Choose Existing Maven Project or Dynamic Web Project

Select the project folder

Click Finish

3. Configure MySQL Database
Open MySQL Workbench and create a database:

CREATE DATABASE carbon_tracker;
Make sure your MySQL username and password match the values in your Hibernate configuration file.

Example hibernate.cfg.xml:

<property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/carbon_tracker?useSSL=false&amp;serverTimezone=UTC</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">your_password</property>
<property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>
4. Add Required Libraries / Dependencies
Make sure the following dependencies are available in your project:

MySQL Connector

Hibernate Core

Apache POI

Servlet API

JSP/Servlet libraries

Any other required JAR files

If using Maven, update the pom.xml and run:

Right click project

Maven > Update Project

5. Configure Tomcat Server
Open Eclipse

Go to Servers

Add Apache Tomcat

Select your Tomcat installation directory

Add the project to the server

6. Build and Run the Project
Right click project

Choose Run on Server

Select Tomcat server

Click Finish

The application will open in the browser.

Example URL:

http://localhost:8080/java-carbon-emission-calculator/
How to Use the Application
1. Register
Open the application

Go to the Register page

Enter user details

Submit the form

2. Login
Enter registered email and password

Click Login

3. Upload Excel File
Open the dashboard

Choose an Excel file

Click Calculate Carbon Emission

4. View Dashboard
The dashboard will show:

Total carbon emission

Category-wise emission

Combined emission chart

Other travel/emission data

5. Logout
Click Logout

Session will end

You will return to the login page

Excel File Format
The uploaded Excel file should contain columns similar to:

From City

To City

Vehicle Type

Number of Vehicles

KM Travelled

Travel Time

Example:

From City	To City	Vehicle Type	Number of Vehicles	KM Travelled	Travel Time
Bangalore	Mysore	Car	2	150	3
Carbon Emission Formula
The system calculates carbon emission using:

Carbon Emission = Distance Travelled × Emission Factor × Number of Vehicles
Different vehicle types may have different emission factors.

Framework and Library Usage
AngularJS
Used for:

Handling frontend logic

Binding data

Sending HTTP requests to backend

Updating dashboard dynamically

Hibernate
Used for:

Database interaction

Saving user, activity, and emission data

Reducing manual SQL handling

Apache POI
Used for:

Reading Excel files

Extracting travel data row by row

Chart.js
Used for:

Displaying carbon emission charts

Improving dashboard visualization

Project Structure
java-carbon-emission-calculator/
│
├── src/
│   ├── com.carbontracker.controller
│   ├── com.carbontracker.entity
│   ├── com.carbontracker.repository
│   ├── com.carbontracker.service
│   └── com.carbontracker.util
│
├── WebContent/ or webapp/
│   ├── index.html
│   ├── login.html
│   ├── register.html
│   ├── dashboard.html
│   ├── app.js
│   ├── style.css
│   └── other frontend files
│
├── hibernate.cfg.xml
└── pom.xml (if Maven project)
Advantages of the Project
Easy to use

Automates carbon emission calculation

Helps understand environmental impact

Supports Excel-based bulk data upload

Provides dashboard-based analytics

Future Enhancements
Add PDF report generation

Add email notification support

Add admin panel

Add more chart types

Add mobile responsive design

Add carbon reduction suggestions

Add support for CSV upload as well

Author
Arun Kumar V
Email: arungowdav3@gmail.com
LinkedIn: https://www.linkedin.com/in/arun-kumar-v
GitHub: https://github.com/arunkumar3333

Conclusion
The Carbon Emission Calculator is a useful full-stack web application that combines frontend development, backend processing, Excel file handling, database storage, and dashboard visualization. It helps users calculate and analyze transportation-related carbon emissions in an efficient and user-friendly way.