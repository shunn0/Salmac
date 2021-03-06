:: This file is a sample batch file which can show status of private profile of firewall in windows
:: @AUTHOR: Nikhil Bhat
:: @DATE: 20th, March, 2020
:: @OPERATING SYSTEM: WINDOWS 10

@echo off


SETLOCAL
:: CALLING Firewall function
CALL :Firewall %~1
EXIT /B %ERRORLEVEL%

:: Firewall function definition
:Firewall

:: Initializing Variables
SET /A show = 2
SET /A currentOn=3
Set /A currentOff=4

:: Setting messages before actions
if %show%==%~1 echo " FIREWALL SCRIPT: SHOWING STATUS OF PRIVATE PROFILES "
if %currentOn%==%~1 echo " FIREWALL SCRIPT: TURNING PRIVATE PROFILES ON "
if %currentOff%==%~1 echo " FIREWALL SCRIPT: TURNING PRIVATE PROFILES OFF "

:: Action performing
if %show%==%~1 netsh advfirewall show currentprofile 
if %currentOn%==%~1 netsh advfirewall set currentprofile state on
if %currentOff%==%~1 netsh advfirewall set currentprofile state off

:: holding output
SET /p wait = "PRESS or CLICK ENTER KEY"

EXIT /B 0