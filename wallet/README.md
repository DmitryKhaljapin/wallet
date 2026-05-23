
# Wallet Service

## Requirements

- Docker
- Docker Compose

---

## Run application

### 1. Create `.env` file

Create a `.env` file in the project root:

```env
DB_NAME=wallet_db
DB_USER=wallet_user
DB_PASSWORD=wallet_password

APP_PORT=8080
DB_PORT=5432
```

### 2. Run the command

```bash
docker-compose up --build

