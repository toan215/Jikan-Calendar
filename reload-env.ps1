# Reload environment variables for current session
$env:MAVEN_HOME = 'C:\Program Files\Apache Software Foundation\apache-maven-3.9.12'
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-17'

# Get both Machine and User PATH
$machinePath = [System.Environment]::GetEnvironmentVariable('Path', 'Machine')
$userPath = [System.Environment]::GetEnvironmentVariable('Path', 'User')

# Combine and set to current session
$env:Path = "$machinePath;$userPath;C:\Program Files\Apache Software Foundation\apache-maven-3.9.12\bin"

Write-Host " reload env variables!" -ForegroundColor Green
Write-Host "MAVEN_HOME: $env:MAVEN_HOME" -ForegroundColor Cyan
Write-Host "JAVA_HOME: $env:JAVA_HOME" -ForegroundColor Cyan

# Test Maven
mvn -version
