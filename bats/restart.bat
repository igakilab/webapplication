@echo off

rem tomcat���ċN������o�b�`�t�@�C��
rem �������w�肷���"..\webapps"�Ɋi�[���ꂽ�t�H���_���폜����
rem tomcat��bin�f�B���N�g���ɔz�u����K�v������

set apphome=..\webapps\
set appdir=%1

echo APPHOME=%apphome%
echo APPDIR=%appdir%

rem tomcat�̃V���b�g�_�E��
echo -- tomcat���V���b�g�_�E�����܂�...
call shutdown.bat

rem �f�B���N�g���̃N���[��
if {%appdir%} NEQ {} (
	echo --�f�B���N�g�����폜���܂�
	rmdir /s %apphome%%appdir%
) else (
	pause
)

rem tomcat�̃X�^�[�g
echo --tomcat���ĊJ���܂�...
call startup.bat





