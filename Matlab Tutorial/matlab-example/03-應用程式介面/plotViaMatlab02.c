/* ���d�һ����p��b�L�n���������ҤU�A�� C �{���өI�s MATLAB ���� */
#include <windows.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "engine.h"

int PASCAL WinMain (HINSTANCE hInstance,
                    HINSTANCE hPrevInstance,
                    LPSTR     lpszCmdLine,
                    int       nCmdShow){
	Engine *ep;
	mxArray *T;
	double time[10] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

	/* �Ұ� MATLAB ���� */
	if (!(ep = engOpen(NULL))) {
		MessageBox ((HWND)NULL, (LPSTR)"Can't start MATLAB engine", 
			(LPSTR)"plotViaMatlab02.c", MB_OK);
		exit(-1);
	}
	
	T = mxCreateDoubleMatrix(1, 10, mxREAL);	// ���ͤ@�� MATLAB �������ܼ� T
	memcpy((char *)mxGetPr(T), (char *)time, 10*sizeof(double));	// �N time ���ȫ����� T
	engPutVariable(ep, "T", T);					// �N T ���Ȱe�� MATLAB �u�@�Ŷ����ܼ� T

	/* �ۥѸ��骺�첾��ɶ�����ơGdistance = (1/2)g.*t.^2 */
	engEvalString(ep, "D = .5.*(-9.8).*T.^2;");			// �N�r��e�� MATLAB �h����
	engEvalString(ep, "plot(T, D, 'o-');");				// �e�X���浲�G
	engEvalString(ep, "title('�ۥѸ��骺�Z���P�ɶ����Y��');");
	engEvalString(ep, "xlabel('�ɶ� (��)');");
	engEvalString(ep, "ylabel('�Z�� (��)');");

	mxDestroyArray(T);			// ���� MATLAB �����ܼ� T
	engClose(ep);				// �̫����� MATLAB ����
	return(0);
}