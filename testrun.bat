cd /d %~dp0
@echo off
call mvn test > test.log
exit