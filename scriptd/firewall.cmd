:: This file is a sample batch file which can show status of firewall in windows
:: @AUTHOR: Nikhil Bhat
:: @DATE: 20th, March, 2020
:: @OPERATING SYSTEM: WINDOWS 10

:: EXAMPLES:   
::              ./firewall.cmd 2        show all firewall profiles
::              ./firewall.cmd 0        turn off all firewall profiles
::              ./firewall.cmd 1        turn on all firewall profiles

:: ECHO OFF
@echo off
SETLOCAL
:: CALLING Firewall function
CALL :Firewall %~1
EXIT /B %ERRORLEVEL%

:: Firewall function definition
:Firewall

:: Initializing Variables
SET /A show = 2
SET /A on = 1
SET /A off = 0


:: Setting messages before actions
if %show%==%~1 echo " FIREWALL SCRIPT: SHOWING ALL PROFILES "
if %on%==%~1 echo " FIREWALL SCRIPT: TURNING ALL PROFILES ON "
if %off%==%~1 echo " FIREWALL SCRIPT: TURNING ALL PROFILES OFF "

:: Action performing
if %show%==%~1 netsh advfirewall show allprofiles 
if %on%==%~1 netsh advfirewall set allprofiles state on
if %off%==%~1 netsh advfirewall set allprofiles state off

:: holding output
SET /p wait = "PRESS ANY KEY TO EXIT"

EXIT /B 0