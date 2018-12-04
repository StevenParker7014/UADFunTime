@echo off
pushd %~dp0
set BASE=%~dp0
set APP_NAME=${artifactId}
set DISPLAY_NAME=Application process utility
set DESCRIPTION=Does stuff to wind a user up
set JAR_NAME=${artifactId}.${packaging}
TITLE %DISPLAY_NAME%

IF "%1"=="help" GOTO  :help
IF "%1"=="run" GOTO :start
IF "%1"=="install" GOTO  :installService
IF "%1"=="remove" GOTO  :removeService
IF "%1"=="" GOTO :start
GOTO :error

:help
@echo %DISPLAY_NAME%
@echo Usage:
@echo start.bat help - displays this message
@echo start.bat start* - Starts the %DISPLAY_NAME% application in a local console window, * optional 
@echo start.bat install - Installs %DISPLAY_NAME% as a Windows Service setting it to start automatically
@echo start.bat remove - Uninstalls the %DISPLAY_NAME% Windows Service, stopping it first if necessary
GOTO :done

:start
@echo Starting %DISPLAY_NAME%
java -jar %JAR_NAME%
GOTO :done 

:installService
@echo Installing %DISPLAY_NAME% as a service
%BASE%nssm.exe install %APP_NAME% %BASE%start.bat
%BASE%nssm.exe set %APP_NAME% AppExit Default Exit
%BASE%nssm.exe set %APP_NAME% DisplayName %DISPLAY_NAME%
%BASE%nssm.exe set %APP_NAME% Description %DESCRIPTION%
%BASE%nssm.exe set %APP_NAME% Start SERVICE_AUTO_START
net start %APP_NAME%
GOTO :done

:removeService
@echo Removing %DISPLAY_NAME% service
net stop %APP_NAME%
%BASE%nssm.exe remove %APP_NAME% confirm
GOTO :done

:error
@echo %DISPLAY_NAME%
@echo Invalid arguments.
GOTO :help

:done
popd