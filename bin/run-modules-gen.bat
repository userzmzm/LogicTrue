@echo off
echo.
echo [��Ϣ] ����modules-gen���̡�
echo.

cd %~dp0
cd ../logictrue-modules/logictrue-gen/target

set JAVA_OPTS=-Xms512m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -Dfile.encoding=utf-8 -jar %JAVA_OPTS% logictrue-modules-gen.jar

cd bin
pause