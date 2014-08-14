/*=================================================================
 * putarray.c 
 *
 * This example demonstrates how to use mexPutArray and mexFunctionName.
 * This function takes an input argument, multiplies it by two, and put
 * it into MATLAB base workspace under a variable name "putarray_output".
 *
 * Roger Jang, 19991003
/*=================================================================*/
 
#include <stdio.h>
#include <string.h>
#include <math.h>
#include "mex.h"	/* mex.h �]�t mxArray ���c���w�q�A�H�Ψ�L������T */

/* prhs = pointer to the right-hand-side arguments�A�Y���V��J�ܼƦC������ */
#define IN prhs[0]	/* �w�q IN �����禡���Ĥ@�ӿ�J�ܼơA�H�K������� */

/* ���禡���M MATLAB ���q���D�n�禡 */
void
mexFunction(int nlhs,mxArray *plhs[],int nrhs,const mxArray *prhs[]) { 
	char array_name[40];
	mxArray *array_ptr;
	int i, status;
    
	/* �ˬd��X�ܼƭӼƬO�_��0�B��J�ܼƭӼƬO�_��1�A�䤤      */
	/* nrhs = no. of right-hand-side arguments�]��J�ܼƭӼơ^ */
	/* nlhs = no. of  left-hand-side arguments�]��X�ܼƭӼơ^ */
	if (nrhs!=1) { mexErrMsgTxt("One input arguments required."); } 
	if (nlhs>1) { mexErrMsgTxt("Too many output arguments."); }

	/* �ˬd��J�ܼƸ�ƫ��A�O�_�ŦX�n�D */
	if (!mxIsDouble(IN) || mxIsComplex(IN))
		mexErrMsgTxt("Input must be real double.");
    
	/* ����MATLAB�򥻤u�@�Ŷ����ܼƦW�١A����MEX�D�ɦW�[�W�u_output�v */
	strcpy(array_name, mexFunctionName());
	strcat(array_name,"_output");

	/* ���ͤ@�Ӹ�ƫ��A��double���}�C */
	array_ptr = mxCreateDoubleMatrix(mxGetM(IN), mxGetN(IN), mxREAL);
	/* �]�w���}�C���W�� */
	mxSetName(array_ptr, array_name);

	/* �N���}�C���곡���H2 */
	for (i=0; i<mxGetM(IN)*mxGetN(IN); i++)
		mxGetPr(array_ptr)[i] = 2*(mxGetPr(IN)[i]);

	/* �N���}�C�e��MATLAB���򥻤u�@�Ŷ� */
	status = mexPutArray(array_ptr, "base");
    
	if (status==1){	/* �L�k�N���}�C�e��MATLAB���򥻤u�@�Ŷ��A�L�X���ѰT�� */
		mexPrintf("Variable %s\n", array_name);
		mexErrMsgTxt("Could not put variable in the base workspace.\n");
	}
	/* ���\�a�N���}�C�e��MATLAB���򥻤u�@�Ŷ��A�L�X���\�T�� */
	mexPrintf("\"%s\" is created in the base workspace.\n", array_name);
    
	/* �R�����e���ͪ��}�C�A�N���Ϊ��O�����k�ٵ��@�~�t��*/
	mxDestroyArray(array_ptr);
}
