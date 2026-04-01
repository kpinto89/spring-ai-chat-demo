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

## Test endpoint

```powershell
Invoke-RestMethod "http://localhost:8080/chat?prompt=Hello"
```

