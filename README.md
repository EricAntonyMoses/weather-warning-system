Weather Warning System 🌧️⚠️
A district‑level weather monitoring and early warning system designed to detect heavy rainfall conditions and generate alerts for flood‑prone regions. The system collects meteorological data, processes rainfall information, and visualizes warnings on an interactive map dashboard.

Although the system can be used for any coastal or high‑rainfall region, the current implementation demonstrates a case study using districts of Meghalaya, India.

Project Overview
Extreme rainfall events often lead to:

Flash floods

Landslides

Urban flooding

Infrastructure damage

This project aims to provide a real‑time weather monitoring and alert platform that helps identify high‑risk regions and issue early warnings.

The system retrieves weather data from meteorological APIs and displays alerts using color‑coded district maps.

Features
🌧️ Real‑time rainfall monitoring

⚠️ Heavy rain and weather warning detection

🗺️ Interactive district‑level map visualization

📊 Color‑coded weather alert system

📅 5‑day forecast toggle option

🔄 Automatic data refresh from weather APIs

Warning Levels
Color	Meaning
🟢 Green	Normal weather
🟡 Yellow	Moderate rainfall
🟠 Orange	Heavy rainfall warning
🔴 Red	Extreme rainfall / flood risk
Technology Stack
Backend
Java

Spring Boot

REST APIs

Maven

Frontend
HTML

CSS

JavaScript

Interactive SVG map

Database
PostgreSQL

Data Source
Weather data retrieved from
India Meteorological Department APIs.

Project Structure
weather-warning-system
│
├── src/main/java
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   └── dto
│
├── src/main/resources
│   ├── static
│   └── application.properties
│
├── pom.xml
└── README.md
How to Run the Project
1️⃣ Clone the repository
git clone https://github.com/EricAntonyMoses/weather-warning-system.git
2️⃣ Navigate to project folder
cd weather-warning-system
3️⃣ Configure PostgreSQL database
Create a database:

weather_db
Update database credentials in:

src/main/resources/application.properties
Example:

spring.datasource.url=jdbc:postgresql://localhost:5432/weather_db
spring.datasource.username=postgres
spring.datasource.password=yourpassword
4️⃣ Run the application
Using Maven:

mvn spring-boot:run
Or run WeatherApplication.java from your IDE.

5️⃣ Open the dashboard
http://localhost:8081
API Endpoints
Get current weather data
GET /api/weather
Get forecast for district
GET /api/forecast/{district}
Example:

GET /api/forecast/EAST KHASI HILLS
Future Improvements
Flood risk prediction models

AI‑based rainfall forecasting

SMS / mobile weather alerts

Integration with disaster management systems

Real‑time satellite rainfall monitoring

Author
Eric Antony Moses
