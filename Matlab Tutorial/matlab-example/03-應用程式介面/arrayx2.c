/* ���禡�� MATLAB �� MEX �ɮסA���J���@�ӦV�q�A��X�h��2���H���V�q�C */

#include "mex.h"	/* mex.h �]�t mxArray ���c���w�q�A�H�Ψ�L������T */

/* prhs = pointer to the right-hand-side arguments�A�Y���V��J�ܼƦC������ */
/* prls = pointer to the  left-hand-side arguments�A�Y���V��X�ܼƦC������ */
#define IN  prhs[0]	/* �w�q IN  �����禡���Ĥ@�ӿ�J�ܼơA�H�K������� */
#define OUT plhs[0]	/* �w�q OUT �����禡���Ĥ@�ӿ�X�ܼơA�H�K������� */

/* ���禡���\�ର�N�V�q x ���C�@�Ӥ������H2�A�A�N���G�e�� y �V�q�C */
/* ���禡�N�|�Q mexFunction �ҩI�s�C */
void timestwo(double y[], double x[], int length) {
	int i;
	for (i=0; i<length; i++)
		y[i] = 2.0*x[i];
}

/* ���禡���M MATLAB ���q���D�n�禡 */
void mexFunction( int nlhs, mxArray *plhs[],
                  int nrhs, const mxArray *prhs[] ) {
	double *xr, *xi, *yr, *yi;
	int length = mxGetM(IN)*mxGetN(IN);
  
	/* �ˬd��X�M��J�ܼƭӼƬO�_���O1�A�䤤		  */
	/* nrhs = no. of right-hand-side arguments�]��J�ܼƭӼơ^*/
	/* nlhs = no. of  left-hand-side arguments�]��X�ܼƭӼơ^*/
	if(nrhs!=1)	/* �ˬd��J�ܼƭӼƬO�_�O1 */
		mexErrMsgTxt("One input required.");
	if(nlhs>1)	/* �ˬd��X�ܼƭӼƬO�_�O1 */
		mexErrMsgTxt("Too many output arguments");
  
	/* �ˬd��J�ܼƬO�_�X�� */
	if(!mxIsDouble(IN))		/* �ˬd��J�ܼƬO�_�� double */
		mexErrMsgTxt("Input must be a double.");
  
	/* �t�m�O���鵹��X�ܼ� */
	OUT = mxCreateDoubleMatrix(mxGetM(IN), mxGetN(IN), mxCOMPLEX);
  
	/* ���o��J�M��X�ܼƪ��곡���� */
	xr = mxGetPr(IN);
	yr = mxGetPr(OUT);
	/* ���o��J�M��X�ܼƪ��곡���� */
	if (mxIsComplex(IN)) {
		xi = mxGetPi(IN);
		yi = mxGetPi(OUT);
	}
  
	/* �����ڪ��B��G�N��J�V�q���곡���H2 */
	timestwo(yr, xr, length);
	/* �����ڪ��B��G�N��J�V�q���곡���H2 */
	if (mxIsComplex(IN))
		timestwo(yi, xi, length);
}
