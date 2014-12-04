set root=%~dp0
set config_file=%ROOT%config.ini
for /f "tokens=1,2 delims==" %%i in (%config_file%) do (
	set %%i=%%j
)

taskkill /f /im java.exe

%mvn_letter%:

cd %mvn_path%

call mvn clean package

cd %mvn_target_related_path%

xcopy "trainingsystem.war" "%tomcat_webapp_path%" /s /f /y

%tomcat_letter%:

cd %tomcat_webapp_path%

rd trainingsystem /q /s

cd %tomcat_startup_related_path%
pause
call startup.bat