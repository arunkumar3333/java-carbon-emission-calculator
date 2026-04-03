Project Title
Carbon Emission Calculator
Description
A full-stack web application that calculates carbon emissions based on transportation data.
Users can upload Excel files, and the system processes the data to compute CO₂ emissions and display results on an interactive dashboard.

📸 Screenshots
Home Page

<img width="1846" height="840" alt="image" src="https://github.com/user-attachments/assets/b247c36a-09a6-47e6-a987-9c6b8ab8930c" />

Login Page

<img width="1822" height="828" alt="image" src="https://github.com/user-attachments/assets/7cce33bd-98af-432b-8df8-f49f98bcc337" />

Register Page
<img width="1710" height="807" alt="image" src="https://github.com/user-attachments/assets/510e6ab7-d53b-4a39-98bc-2d4a8bc0a050" />

Dashboard Page
<img width="1892" height="862" alt="image" src="https://github.com/user-attachments/assets/bf7d7dca-1896-4cf6-991e-c65f21d843d2" />


Tech Stack
Frontend: HTML5, CSS3, JavaScript, AngularJS, Chart.js

Backend: Java Servlets, Hibernate

Database: MySQL

Libraries: Apache POI

Server: Apache Tomcat

How to Run
1. Clone the Repository
git clone https://github.com/arunkumar3333/java-carbon-emission-calculator.git
2. Import Project
Open Eclipse

File → Import → Existing Project

3. Setup Database
CREATE DATABASE carbon_tracker;
4. Configure Database
Update hibernate.cfg.xml with your MySQL username & password

5. Run the Project
Start Apache Tomcat

Run project on server

6. Open in Browser
http://localhost:8080/java-carbon-emission-calculator
⭐ That’s it!
This format is:

Clean ✅

ATS-friendly ✅

Recruiter-friendly ✅

Easy to understand ✅### Libraries / Tools
- Apache POI (Excel processing)  
- Eclipse IDE  
- XAMPP / MySQL Workbench  

### Server
- Apache Tomcat  

## 🚀 Features

- User Registration & Login  
- Session Management  
- Excel File Upload  
- Automatic Carbon Emission Calculation  
- Dashboard with Charts  
- Total Emission Display  
- Category-wise Analysis  
- Logout Functionality  

## 🧠 How It Works

1. User registers and logs in  
2. Uploads Excel file with travel data  
3. Backend reads file using Apache POI  
4. Calculates carbon emission using formula  
5. Stores data in MySQL  
6. Displays results in dashboard with charts  

## 📊 Carbon Calculation Formula
Carbon Emission = Distance × Emission Factor × Number of Vehicles

## 🗂️ Project Structure
java-carbon-emission-calculator/
│
├── src/
│ ├── controller/
│ ├── entity/
│ ├── repository/
│ ├── service/
│ └── util/
│
├── webapp/
│ ├── index.html
│ ├── login.html
│ ├── register.html
│ ├── dashboard.html
│ ├── app.js
│ └── style.css
│
├── screenshots/
├── hibernate.cfg.xml
└── pom.xml

## 🛠️ Prerequisites

Make sure you have installed:

- Java JDK 8+  
- Eclipse IDE  
- Apache Tomcat  
- MySQL Server  
- MySQL Workbench  

## 🧑‍💻 How to Run

### 1. Clone the Repository
```bash
git clone https://github.com/arunkumar3333/java-carbon-emission-calculator.git
2. Import into Eclipse
File → Import → Existing Project

Select project folder

3. Setup Database
CREATE DATABASE carbon_tracker;
4. Configure Database
Update hibernate.cfg.xml:

<property name="hibernate.connection.url">
jdbc:mysql://localhost:3306/carbon_tracker
</property>
Set your:

username

password

5. Add Dependencies
Ensure these are available:

MySQL Connector

Hibernate

Apache POI

Servlet API

6. Run on Tomcat
Right click project → Run on Server

Choose Apache Tomcat

7. Open in Browser
http://localhost:8080/java-carbon-emission-calculator
📂 Excel Format
Your Excel file should contain:

From City	To City	Vehicle Type	Vehicles	KM	Time
Bangalore	Mysore	Car	2	150	3
📌 Modules
Registration Module

Login Module

Dashboard Module

Excel Upload Module

Carbon Calculation Module

Logout Module

🚀 Future Enhancements
PDF report generation

Email notifications

Admin dashboard

Mobile responsive UI

More chart visualizations

👨‍💻 Author
Arun Kumar V
📧 arungowdav3@gmail.com
🔗 LinkedIn: https://www.linkedin.com/in/arun-kumar-v
💻 GitHub: https://github.com/arunkumar3333

⭐ Conclusion
The Carbon Emission Calculator is a complete full-stack web application that combines frontend UI, backend processing, Excel data handling, and database management to provide meaningful insights into carbon emissions.

