@echo off
if "%1"=="create-project" goto project
if "%1"=="create-model" goto model
if "%1"=="create-view" goto view
if "%1"=="create-action" goto action

:project
mvn core:%1 -Dcp.name=%2
break
:model
mvn core:%1 -Dcp.model.name=%2
break
:view
mvn core:%1 -Dcp.view.name=%2
break
:action
mvn core:%1 -Dcp.action.name=%2
break
@echo off
