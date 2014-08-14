#include <math.h>
#include "mex.h"	/* mex.h �]�t mxArray ���c���w�q�A�H�Ψ�L������T */

/* prhs = pointer to the right-hand-side arguments�A�Y���V��J�ܼƦC������ */
/* prls = pointer to the  left-hand-side arguments�A�Y���V��X�ܼƦC������ */
#define	MAT1 prhs[0]	/* �w�q MAT1 ������ƪ��Ĥ@�ӿ�J�ܼơA�H�K������� */
#define	MAT2 prhs[1]	/* �w�q MAT1 ������ƪ��ĤG�ӿ�J�ܼơA�H�K������� */
#define	OUT  plhs[0]	/* �w�q OUT  ������ƪ��Ĥ@�ӿ�X�ܼơA�H�K������� */

/* ����Ƭ��M MATLAB ���q���D�n��� */
void mexFunction(
	int nlhs,	mxArray *plhs[],
	int nrhs, const mxArray *prhs[]) {
	double	*out, *mat1, *mat2, squareSum;
	int m, p, n, p2, i, j, k;

	/* �ˬd��X�ܼƭӼƬO�_��2�B��J�ܼƭӼƬO�_��1�A�䤤	  */
	/* nrhs = no. of right-hand-side arguments�]��J�ܼƭӼơ^*/
	/* nlhs = no. of  left-hand-side arguments�]��X�ܼƭӼơ^*/
	if (nrhs!=2)
		mexErrMsgTxt("PAIRDIST requires two input arguments.");

	/* �ˬd���׬O�_�ŦX�n�D */
	p  = mxGetM(MAT1);
	m  = mxGetN(MAT1);
	p2  = mxGetM(MAT2);
	n = mxGetN(MAT2);
	if (p != p2)
		mexErrMsgTxt("Matrix sizes mismatch!");

	/* �ˬd��J�ܼƸ�ƫ��A�O�_�ŦX�n�D */
	if (!mxIsNumeric(MAT1) || mxIsSparse(MAT1)  || !mxIsDouble(MAT1))
		mexErrMsgTxt("Input 1 is not a full numerical array!");
	if (!mxIsNumeric(MAT2) || mxIsSparse(MAT2)  || !mxIsDouble(MAT2))
		mexErrMsgTxt("Input 2 is not a full numerical array!");

	/* �t�m�O���鵹��X�ܼ� */
	OUT = mxCreateDoubleMatrix(m, n, mxREAL);

	/* ���o��J�M��X�ܼƪ��곡���� */
	out = mxGetPr(OUT);
	mat1 = mxGetPr(MAT1);
	mat2 = mxGetPr(MAT2);

	/* ��ڭp�ⳡ�� */
	/* MAT1(i+1, j+1) ���ȬO mat1[j*m+i] */
	/* MAT2(i+1, j+1) ���ȬO mat2[j*n+i] */
	/*  OUT(i+1, j+1) ���ȬO  out[j*m+i] */

	for (i=0; i<m; i++)
		for (j=0; j<n; j++) {
			squareSum = 0;
			for (k=0; k<p; k++)
				squareSum += pow(mat1[i*p+k]-mat2[j*p+k], 2.0);
			out[j*m+i] = sqrt(squareSum);
		}
}
