# QuadSolutions Trivia Application

Een full-stack trivia applicatie gebouwd met Spring Boot (backend) en Vue.js (frontend).

## 📋 Inhoudsopgave

- [Vereisten](#vereisten)
- [Project Structuur](#project-structuur)
- [Backend Setup](#backend-setup)
- [Frontend Setup](#frontend-setup)
- [Applicatie Starten](#applicatie-starten)
- [Troubleshooting](#troubleshooting)
- [API Informatie](#api-informatie)

## 🛠️ Vereisten

Zorg ervoor dat je de volgende software geïnstalleerd hebt:

### Backend (Spring Boot)

- **Java 17** of hoger
- **Maven 3.6+** (of gebruik de meegeleverde Maven wrapper)

### Frontend (Vue.js)

- **Node.js 20.19.0+** of **22.12.0+**
- **npm** (wordt automatisch meegeleverd met Node.js)

### Verificatie van installaties

```bash
# Controleer Java versie
java -version

# Controleer Maven versie (optioneel)
mvn -version

# Controleer Node.js versie
node -version

# Controleer npm versie
npm -version
```

## 📁 Project Structuur

```
apps/
├── backend/          # Spring Boot API
│   ├── src/
│   ├── pom.xml
│   ├── mvnw          # Maven wrapper (Linux/Mac)
│   └── mvnw.cmd      # Maven wrapper (Windows)
└── frontend/         # Vue.js applicatie
    ├── src/
    ├── package.json
    └── vite.config.js
```

## 🚀 Backend Setup

### Stap 1: Navigeer naar de backend folder

```bash
cd apps/backend
```

### Stap 2: Dependencies installeren en builden

```bash
# Optie A: Met Maven (als geïnstalleerd)
mvn clean install

# Optie B: Met Maven wrapper (aanbevolen)
# Voor Windows:
./mvnw.cmd clean install

# Voor Linux/Mac:
./mvnw clean install
```

### Stap 3: Backend starten

```bash
# Optie A: Met Maven
mvn spring-boot:run

# Optie B: Met Maven wrapper
# Voor Windows:
./mvnw.cmd spring-boot:run

# Voor Linux/Mac:
./mvnw spring-boot:run
```

**✅ Backend is succesvol gestart als je dit ziet:**

```
Started TriviaApplication in X.XXX seconds
```

De backend API is nu beschikbaar op: `http://localhost:8080`

## 🎨 Frontend Setup

### Stap 1: Navigeer naar de frontend folder

```bash
cd apps/frontend
```

### Stap 2: Dependencies installeren

```bash
npm install
```

### Stap 3: Development server starten

```bash
npm run dev
```

**✅ Frontend is succesvol gestart als je dit ziet:**

```
  Local:   http://localhost:5173/
  Network: http://192.168.x.x:5173/
```

De frontend applicatie is nu beschikbaar op: `http://localhost:5173`

## 🏃‍♂️ Applicatie Starten

### Complete applicatie opstarten (beide services)

1. **Terminal 1 - Backend starten:**

   ```bash
   cd apps/backend
   ./mvnw.cmd spring-boot:run    # Windows
   # of
   ./mvnw spring-boot:run        # Linux/Mac
   ```

2. **Terminal 2 - Frontend starten:**

   ```bash
   cd apps/frontend
   npm run dev
   ```

3. **Open je browser en ga naar:** `http://localhost:5173`

### Production Build

#### Backend Production Build

```bash
cd apps/backend
./mvnw.cmd clean package    # Windows
# of
./mvnw clean package        # Linux/Mac

# JAR file wordt aangemaakt in: target/trivia-1.0.0.jar
# Starten met:
java -jar target/trivia-1.0.0.jar
```

#### Frontend Production Build

```bash
cd apps/frontend
npm run build

# Build wordt aangemaakt in: dist/
# Serve met een web server naar keuze
npm run preview  # Voor lokale preview
```

## 🔧 Troubleshooting

### Veelvoorkomende Problemen

#### Backend Problemen

**Port 8080 al in gebruik:**

```
Web server failed to start. Port 8080 was already in use.
```

**Oplossing:** Stop andere services op poort 8080 of wijzig de poort in `application-dev.yaml`

**Java versie incorrect:**

```
The compiler compliance specified is 17 but a JRE 11 is used
```

**Oplossing:** Installeer Java 17+ en zet JAVA_HOME correct

#### Frontend Problemen

**Node versie te laag:**

```
The engine "node" is incompatible with this module
```

**Oplossing:** Upgrade naar Node.js 20.19.0+ of 22.12.0+

**Port 5173 al in gebruik:**

```
Port 5173 is in use, trying another one...
```

**Oplossing:** Dit is normaal, Vite zal automatisch een andere poort kiezen

**Dependencies installatie mislukt:**

```bash
# Verwijder node_modules en probeer opnieuw
rm -rf node_modules package-lock.json
npm install
```

### Logs en Debugging

#### Backend Logs

De Spring Boot applicatie toont gedetailleerde logs in de console. Voor extra debugging:

```bash
./mvnw.cmd spring-boot:run -Dlogging.level.com.jesperweijnen=DEBUG
```

#### Frontend Logs

Open Developer Tools (F12) in je browser voor JavaScript console logs.

## 📡 API Informatie

### Backend Endpoints

- **Base URL:** `http://localhost:8080`
- **Health Check:** `GET /actuator/health` (als Spring Actuator is ingeschakeld)
- **Trivia API:** Zie `TriviaController.java` voor specifieke endpoints

### Frontend Development Server

- **URL:** `http://localhost:5173`
- **Hot Reload:** Wijzigingen worden automatisch herladen
- **Vue DevTools:** Beschikbaar in development mode

## 🎯 Volgende Stappen

1. Open `http://localhost:5173` in je browser
2. De applicatie haalt trivia vragen op via de backend API
3. De backend communiceert met de OpenTDB API voor trivia content
4. Geniet van het trivia spel!

---

**💡 Tip:** Houd beide terminal vensters open tijdens development voor real-time logs van zowel frontend als backend.

**🆘 Hulp nodig?** Controleer de console output voor specifieke foutmeldingen en zoek naar de relevante sectie in deze README.
