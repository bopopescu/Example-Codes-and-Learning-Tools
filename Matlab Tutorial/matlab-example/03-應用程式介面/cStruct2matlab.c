// ���d�һ����p��� C �����c�}�C�ন MATLAB �����c�}�C
// Roger Jang, 20050402

#include "mex.h"
#include <string.h>

#define	OUT	plhs[0]		// �w�q��X�ܼ�

struct phonebook {
	const char *name;
	double phone;
};
// C �����c�}�C
struct phonebook friends[] = {{"������", 3486},{"�L�F��",3712}, {"������", 2248}};
// �Ω� MATLAB ���c�}�C�����W��
const char *fieldNames[] = {"name", "phone"};

void
mexFunction(int nlhs,mxArray *plhs[],int nrhs,const mxArray *prhs[]) {
	int structNum = sizeof(friends)/sizeof(struct phonebook);	// ���c�}�C������
	int fieldNum = sizeof(fieldNames)/sizeof(*fieldNames);		// ��쪺�Ӽ�
	int dims[2] = {1, structNum};					// ���c�}�C������
	int i, nameFieldIndex, phoneFieldIndex;

	// �ˬd��J�M��X�Ѽƪ��Ӽ�
	if (nrhs>0) mexErrMsgTxt("No input argument required.");
	if (nlhs>1) mexErrMsgTxt("Too many output arguments.");
    
	// ���Ϳ�X���c�}�C
	OUT = mxCreateStructArray(2, dims, fieldNum, fieldNames);

	// ���o���W�ٹ��������ޭȡA�H�K�ϥ� mxSetFieldByNumber() �����ȶi��]�w
	nameFieldIndex = mxGetFieldNumber(OUT, "name");
	phoneFieldIndex = mxGetFieldNumber(OUT, "phone");

	// ��J MATLAB ���c�}�C������
	for (i=0; i<structNum; i++) {
		mxArray *fieldValue;
		// ��J���W�� name ���ȡ]����ؤ�k�^
	//	mxSetField(OUT, i, "name", mxCreateString(friends[i].name));			// ��k�@�G�Ĳv���C
		mxSetFieldByNumber(OUT, i, nameFieldIndex, mxCreateString(friends[i].name));	// ��k�G�G�Ĳv����
		// ��J���W�� phone ���ȡ]����ؤ�k�^
		fieldValue = mxCreateDoubleMatrix(1, 1, mxREAL);
		*mxGetPr(fieldValue) = friends[i].phone;
	//	mxSetField(OUT, i, "phone", fieldValue);					// ��k�@�G�Ĳv���C
		mxSetFieldByNumber(OUT, i, phoneFieldIndex, fieldValue);	// ��k�G�G�Ĳv����
	}
}
