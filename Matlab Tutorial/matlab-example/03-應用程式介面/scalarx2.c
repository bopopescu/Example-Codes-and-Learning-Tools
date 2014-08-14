/* ���禡�� MATLAB �� MEX �ɮסA���J���@�ӯ¶q�A��X�h�����¶q���⭿�C */

#include "mex.h"	/* mex.h �]�t mxArray ���c���w�q�A�H�Ψ�L������T */

/* prhs = pointer to the right-hand-side arguments�A�Y���V��J�ܼƦC������ */
/* prls = pointer to the  left-hand-side arguments�A�Y���V��X�ܼƦC������ */
#define IN  prhs[0]	/* �w�q IN  �����禡���Ĥ@�ӿ�J�ܼơA�H�K������� */
#define OUT plhs[0]	/* �w�q OUT �����禡���Ĥ@�ӿ�X�ܼơA�H�K������� */

/* ���禡���\�ର�N x ���Ĺs�Ӥ������H2�A�b�N���G�e�� y ���Ĺs�Ӥ����C */
/* ���禡�N�|�Q mexFunction �ҩI�s�C */
void timestwo(double y[], double x[]) {
	y[0] = 2.0*x[0];
}

/* ���禡���M MATLAB ���q���D�n�禡 */
void mexFunction( int nlhs, mxArray *plhs[],
                  int nrhs, const mxArray *prhs[] ) {
	double *x, *y;
	int    no_rows, no_cols;
  
	/* �ˬd��X�M��J�ܼƭӼƬO�_���O1�A�䤤		  */
	/* nrhs = no. of right-hand-side arguments�]��J�ܼƭӼơ^*/
	/* nlhs = no. of  left-hand-side arguments�]��X�ܼƭӼơ^*/
	if(nrhs!=1)	/* �ˬd��J�ܼƭӼƬO�_�O1 */
		mexErrMsgTxt("One input required.");
	if(nlhs>1)	/* �ˬd��X�ܼƭӼƬO�_�O1 */
		mexErrMsgTxt("Too many output arguments");
  
	/* �ˬd��J�ܼƬO�_�X�� */
	no_rows = mxGetM(IN);	/* ��C���� */
	no_cols = mxGetN(IN);	/* ������� */
	if(!(no_rows==1 && no_cols==1))	/* �ˬd��J�ܼƬO�_���¶q */
		mexErrMsgTxt("Input must be a scalar.");
	if(mxIsComplex(IN))		/* �ˬd��J�ܼƬO�_����� */
		mexErrMsgTxt("Input must be noncomplex.");
	if(!mxIsDouble(IN))		/* �ˬd��J�ܼƬO�_�� double */
		mexErrMsgTxt("Input must be a double.");
  
	/* �t�m�O���鵹��X�ܼ� */
	OUT = mxCreateDoubleMatrix(no_rows, no_cols, mxREAL);
  
	/* ���o��J�M��X�ܼƪ����� */
	x = mxGetPr(IN);
	y = mxGetPr(OUT);
  
	/* �����ڪ��B��G�N��J�¶q���H2 */
	timestwo(y, x);
}
