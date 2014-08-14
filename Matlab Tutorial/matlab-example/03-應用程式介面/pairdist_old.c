#include <math.h>
#include "mex.h"	/* mex.h �]�t mxArray ���c���w�q�A�H�Ψ�L������T */

/* prhs = pointer to the right-hand-side arguments�A�Y���V��J�ܼƦC������ */
/* prls = pointer to the  left-hand-side arguments�A�Y���V��X�ܼƦC������ */
#define	MAT1 prhs[0] /* �w�q MAT1 �����禡���Ĥ@�ӿ�J�ܼơA�H�K������� */
#define	MAT2 prhs[1] /* �w�q MAT1 �����禡���ĤG�ӿ�J�ܼơA�H�K������� */
#define	OUT  plhs[0] /* �w�q OUT  �����禡���Ĥ@�ӿ�X�ܼơA�H�K������� */

/* ���禡���M MATLAB ���q���D�n�禡 */
void mexFunction(
	int nlhs,	mxArray *plhs[],
	int nrhs, const mxArray *prhs[]) {
	double	*out, *mat1, *mat2, square_sum;
	int m, p, n, p2, i, j, k;

	/* �ˬd��X�ܼƭӼƬO�_��2�B��J�ܼƭӼƬO�_��1�A�䤤	  */
	/* nrhs = no. of right-hand-side arguments�]��J�ܼƭӼơ^*/
	/* nlhs = no. of  left-hand-side arguments�]��X�ܼƭӼơ^*/
	if (nrhs!=2)
		mexErrMsgTxt("PAIRDIST requires two input arguments.");

	/* �ˬd���׬O�_�ŦX�n�D */
	m  = mxGetM(MAT1);
	p  = mxGetN(MAT1);
	n  = mxGetM(MAT2);
	p2 = mxGetN(MAT2);
	if (p != p2)
		mexErrMsgTxt("Matrix sizes mismatch!");

	/* �ˬd��J�ܼƸ�ƫ��A�O�_�ŦX�n�D */
	if (!mxIsNumeric(MAT1) || mxIsSparse(MAT1)  || !mxIsDouble(MAT1)) {
		mexErrMsgTxt("Input 1 is not a full numerical array!");
	}
	if (!mxIsNumeric(MAT2) || mxIsSparse(MAT2)  || !mxIsDouble(MAT2)) {
		mexErrMsgTxt("Input 2 is not a full numerical array!");
	}

	/* �t�m�O���鵹��X�ܼ� */
	OUT = mxCreateDoubleMatrix(m, n, mxREAL);

	/* ���o��J�M��X�ܼƪ��곡���� */
	out = mxGetPr(OUT);
	mat1 = mxGetPr(MAT1);
	mat2 = mxGetPr(MAT2);

	/* ��ڭp�ⳡ�� */
	/* MAT1(i, j) ���ȬO mat1[(j-1)*m+(i-1)] */
	/* MAT2(i, j) ���ȬO mat2[(j-1)*n+(i-1)] */
	/*  OUT(i, j) ���ȬO  out[(j-1)*m+(i-1)] */

	for (i=1; i<=m; i++)
		for (j=1; j<=n; j++) {
			square_sum = 0;
			for (k=1; k<=p; k++)
				square_sum += pow(mat1[(k-1)*m+(i-1)]-mat2[(k-1)*n+(j-1)], 2.0);
			out[(j-1)*m+(i-1)] = sqrt(square_sum);
		}
}
