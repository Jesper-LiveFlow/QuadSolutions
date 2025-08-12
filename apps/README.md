# QuadSolutions Trivia Application

Een full-stack trivia applicatie gebouwd met Spring Boot (backend) en Vue.js (frontend).

## 🛠️ Vereisten

- **Java 17** of hoger
- **Node.js 20.19.0+** of **22.12.0+**

## 🚀 Applicatie Starten

### 1. Backend Starten (Spring Boot)

Open een terminal en navigeer naar de backend folder:

```bash
cd apps/backend
```

Start de backend met de **meegeleverde Maven wrapper** (geen Maven installatie nodig):

```bash
# Windows:
./mvnw.cmd spring-boot:run

# Linux/Mac:
./mvnw spring-boot:run
```

✅ **Backend is klaar** wanneer je dit ziet:

```
Started TriviaApplication in X.XXX seconds
```

De backend draait nu op: `http://localhost:8080`

### 2. Frontend Starten (Vue.js)

Open een **nieuwe terminal** en navigeer naar de frontend folder:

```bash
cd apps/frontend
```

Installeer de dependencies:

```bash
npm install
```

Start de frontend development server:

```bash
npm run dev
```

✅ **Frontend is klaar** wanneer je dit ziet:

```
Local:   http://localhost:5173/
```

### 3. Applicatie Gebruiken

Open je browser en ga naar: **http://localhost:5173**
