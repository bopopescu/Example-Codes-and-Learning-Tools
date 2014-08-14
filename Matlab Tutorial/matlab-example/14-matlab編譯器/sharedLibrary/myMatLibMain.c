/*=======================================================
 * ���d�ҵ{���i�H�I�s MATLAB �sĶ���Ҳ��ͪ��{���w matLib
 * Roger Jang, 20080211
 *=======================================================*/

#include <stdio.h>
/* �ɤJ MCR ���Y�ɥH�Ρ]�� MATLAB �sĶ�����͡^�S�w���ά��������Y�� */
#include "matLib.h"	


/* ���C��ƫ��A�� mxArray ��������ǰ}�C */
void matDisplay(const mxArray* in){
	int i, j;
	int rowNum = mxGetM(in);	/* ��C�Ӽ� */
	int colNum = mxGetN(in);	/* ����Ӽ� */
	double *data = mxGetPr(in);	/* �����J�}�C����������Ǹ�� */
    
	/* �L�X��� */
	for(i=0; i<colNum; i++){
		for(j=0; j<rowNum; j++)
			printf("%4.2f\t", data[j*colNum+i]);
		printf("\n");
	}
	printf("\n");
}

/* �D�n�u�@�禡 */
void *mainFunction(int *errorCode){
	mxArray *in1, *in2;	/* �w�q�e��{���w�禡����J�x�} */
	mxArray *out = NULL;	/* �w�q�{���w�禡����X�x�} */
	double data[] = {1, 3, 5, 2, 4, 6, 0, 8, 9};	/* ���եΪ���� */

	/* �I�s mclInitializeApplication() �H�T�{�����ε{���w�g�Q���T�a�ҩl */
	if(!mclInitializeApplication(NULL, 0)){
		fprintf(stderr, "���~�G�L�k�ҩl���ε{���I\n");
		*errorCode=-1;
		return;
	}
	/* �I�s matLibInitialize() �H�T�{ MATLAB�sĶ�����{���w�w�g�Q���T�a�ҩl */
	if (!matLibInitialize()){
		fprintf(stderr,"���~�G�L�k�ҩl�{���w�I\n");
		*errorCode=-2;
		return;
	}
    
	/* �гy��J�x�} */
	in1 = mxCreateDoubleMatrix(3, 3, mxREAL);
	in2 = mxCreateDoubleMatrix(3, 3, mxREAL);
	memcpy(mxGetPr(in1), data, 9*sizeof(double));	/* �N data ������ƫ����� in1 */
	memcpy(mxGetPr(in2), data, 9*sizeof(double));	/* �N data ������ƫ����� in2 */
    
        /* �I�s�� MATLAB �sĶ�����ͪ��{���w matLib */
	/* �p��x�}�ۭ� */
	mlfMyMatMultiply(1, &out, in1, in2);
	printf("�x�}�ۭ������G�G\n"); matDisplay(out);
	mxDestroyArray(out); out=NULL;	/* ���^�t�m�� out ���O���� */
	/* �p��ϯx�} */
	mlfMyMatInv(1, &out, in1);
	printf("�p��ϯx�}�����G�G\n"); matDisplay(out);
	mxDestroyArray(out); out=NULL;	/* ���^�t�m�� out ���O���� */

	/* ���^�t�m�� in1 �M in2 ���O���� */
	mxDestroyArray(in1); in1=NULL;
	mxDestroyArray(in2); in2=NULL;

	/* �I�s matLibTerminate() �H�פ����ε{���w */
	matLibTerminate();
	/* �I�s mclTerminateApplication() �H�פ���ε{�� */
	mclTerminateApplication();
}

/* �D�{�� */
int main(){
	int errorCode=0;
	mainFunction(&errorCode);
	return(errorCode);
}