# Spring AI Chat Demo

This project uses `spring-ai-starter-model-openai` with a GitHub Models (Copilot-compatible) endpoint.

## Setup (Windows PowerShell)

1. Create a GitHub token with access to GitHub Models.
2. Set the token as an environment variable:

```powershell
setx GITHUB_TOKEN "<your-token>"
```

3. Close and reopen your terminal/IDE so the new variable is loaded.
4. Verify the variable is available:

```powershell
$env:GITHUB_TOKEN
Get-ChildItem Env:GITHUB_TOKEN
```

## Run

```powershell
mvn spring-boot:run
```

## Use the UI

Open `http://localhost:8080/` in your browser and chat from the page.

## Swagger / OpenAPI

Once the app is running, open:

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Test endpoint

```powershell
Invoke-RestMethod "http://localhost:8080/chat?prompt=Hello"
```
