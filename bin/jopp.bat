@echo off
set JAR_NAME=jopp.jar

:: Check if at least 1 argument is provided
if "%~1"=="" (
    goto :usage
    exit /b 1
)

:: Run the JAR
java -jar "%JAR_NAME%" %*
goto :end

:usage
echo Usage:
echo   %0 "<your message>"
echo   %0 -f ^<path/to/file^>
echo.
echo Examples:
echo   %0 "Hello, world!"
echo   %0 -f notes.txt

:end