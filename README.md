This is a [Spring Boot](https://projects.spring.io/spring-boot/) application that provides a RESTful API
for the [Back](https://github.com/Sillfer/MyRH_back.git) application.
## Getting Started
## Frontend with ANGULAR  and Tailwind CSS

```bash
npm install
ng serve
```
## Backend with Spring Boot and MySQL

```bash
mvn spring-boot:run
```

Open [http://localhost:8081](http://localhost:8081) with your browser to see the result.

## Folder structure

![Alt text](<Capture d’écran 2023-01-06 183056.png>)
## API endpoints

## Register and login
| Method | URL                  | Description |
| ------ |----------------------|-------------|
| `POST` | `/api/auth/register` | Register.   |
| `POST` | `/api/auth/authenticate`         | Login.      |
## Protected Routes with JWT
| Method | URL                               | Description           |
| ------ |-----------------------------------|-----------------------|
| `POST` | `/api/company/save`               | Company save.         |
| `POST` | `/api/recruiter/save`             | Recruiter Save.       |
| `POST` | `/api/api/job_offer/save`         | Offer Save.           |
| `GET`  | `/api/api/job_offer/find`         | Offer Find.           |
| `GET`  | `/api/api/job_offer`              | Offer Find All.       |
| `GET`  | `/api/api/job_offer/find/title`   | Offer find By Title   |
| `GET`  | `/api/api/job_offer/find/company` | Offer find By Company |

## Headers
| Key          | Value         |
|--------------|---------------|
| Content-Type | application/json |
| Authorization | Bearer {token} |

## Example

Just to round up, here is an example of how our response will look like:

```json
{
  "id": 1,
  "title": "Software Engineer",
  "description": "We are looking for a Software Engineer to join our team. You will be responsible for developing and maintaining software solutions.",
  "company": {
    "id": 1,
    "name": "Google",
    "address": "1600 Amphitheatre Parkway, Mountain View, CA 94043, USA",
    "email": "sillfer@gmail.com",
    "phone": "+1 650-253-0000"
    },
    "recruiter": {
      "id": 1,
      "name": "John Doe",
      "email": "google@gmail.com",
        "phone": "+1 650-253-0000",
        "company": {
          "id": 1,
          "name": "Google",
          "address": "1600 Amphitheatre Parkway, Mountain View, CA 94043, USA",
          "email": "google@gmail.com",
          "phone": "+1 650-253-0000"
        }
      
    }
}

```