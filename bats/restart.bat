@echo off

rem tomcatを再起動するバッチファイル
rem 引数を指定すると"..\webapps"に格納されたフォルダを削除する
rem tomcatのbinディレクトリに配置する必要がある

set apphome=..\webapps\
set appdir=%1

echo APPHOME=%apphome%
echo APPDIR=%appdir%

rem tomcatのシャットダウン
echo -- tomcatをシャットダウンします...
call shutdown.bat

rem ディレクトリのクリーン
if {%appdir%} NEQ {} (
	echo --ディレクトリを削除します
	rmdir /s %apphome%%appdir%
) else (
	pause
)

rem tomcatのスタート
echo --tomcatを再開します...
call startup.bat





