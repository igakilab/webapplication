@echo off
echo tomcatを再起動させます...
echo -----
echo tomcatをシャットダウンします
call shutdown.bat
echo -----
echo tomcatを再起動させるには何かキーを押してください
pause
echo tomcatを起動します
call startup.bat


