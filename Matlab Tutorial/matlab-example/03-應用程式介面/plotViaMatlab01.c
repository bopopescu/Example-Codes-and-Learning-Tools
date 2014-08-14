/* ���d�һ����p��b�L�n���������ҤU�A�� C �{���өI�s MATLAB ���� */
#include <windows.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <direct.h>
#include "mex.h"
#include "engine.h"

#define BUFSIZE 256

int PASCAL WinMain (HINSTANCE hInstance,
                    HINSTANCE hPrevInstance,
                    LPSTR     lpszCmdLine,
                    int       nCmdShow){
	char buffer[BUFSIZE];
	mxArray *app;
	Engine *ep;

	/* �Ұ� MATLAB ���� */
	if (!(ep = engOpen(NULL))){		// ���ͤ@�� MATLAB ��������
		MessageBox ((HWND)NULL, (LPSTR)"Can't start MATLAB engine",
			(LPSTR)"plotViaMatlab01.c", MB_OK);
		exit(-1);
	}

	/* �����ؿ��ð��� plotSine.m */
	_getcwd(buffer, BUFSIZE);		// �N���{���Ҧb�ؿ��s�J�r�� buffer
	app = mxCreateString(buffer);		// ���� MATLAB �������r���ܼ� app
	engPutVariable(ep, "appDir", app);	// �N�r���ܼ� app �ܤJ�u�@�Ŷ����ܼ� appDir
	engEvalString(ep, "cd(appDir)");	// �N MATLAB ���u�@�ؿ������ܦr�� appDir �ҫ��w���ؿ�
	engEvalString(ep, "plotSine");		// ����P�ؿ��U�� plotSine.m
	
	/* ���o MATLAB ��X�T�� */
	engOutputBuffer(ep, buffer, BUFSIZE);	// �]�w buffer �i�H���� MATLAB ����X�T��
	engEvalString(ep, "whos");		// �b MATLAB �������� whos ���O
	MessageBox((HWND)NULL, (LPSTR)buffer, (LPSTR) "MATLAB - whos", MB_OK);		// ��� buffer �����e

	engClose(ep);				// �̫����� MATLAB ����
	return(0);
}