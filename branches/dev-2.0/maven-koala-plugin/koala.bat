@echo off
if "%1"=="create-project" goto project
if "%1"=="create-model" goto model

:project
@echo on
rem "create the project..."
@echo off
mvn -q core:%1 -Dcp.name=%2
break
:model
@echo on
rem "create the model..."
@echo off
mvn -q core:%1 -Dcp.model.name=%2
break
@echo off
