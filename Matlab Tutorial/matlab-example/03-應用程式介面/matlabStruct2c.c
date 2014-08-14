// ���d�һ����p��� MATLAB �����c�}�C�ন C �����c�}�C
// Roger Jang, 20050402

#include "mex.h"
#include <string.h>

#define	IN	prhs[0]		// �w�q��X�ܼ�

typedef struct {
	char *name;
	double phone;
} PHONEBOOK;
PHONEBOOK *friend;	// C �����c�}�C

void
mexFunction(int nlhs,mxArray *plhs[],int nrhs,const mxArray *prhs[]) {
	int i, elementNum, strLen, status;

	// �ˬd��J�M��X�Ѽƪ��Ӽ�
	if (nrhs<1) mexErrMsgTxt("One input argument required.");
	if (nlhs>0) mexErrMsgTxt("No output argument required.");

	elementNum = mxGetNumberOfElements(IN);		// �����Ӽ�
	friend = (PHONEBOOK *)malloc(elementNum*sizeof(PHONEBOOK));	// ���� C �����c�}�C
    
	// ��J C ���c�}�C������
	for (i=0; i<elementNum; i++) {
		// ��J phone ���
		mxArray *fieldValue;
		fieldValue = mxGetField(IN, i, "phone");
		friend[i].phone = *mxGetPr(fieldValue);
		// ��J name ���
		fieldValue = mxGetField(IN, i, "name");
		strLen = (2*mxGetM(fieldValue)*mxGetN(fieldValue))+1;	// ���W2, �H��I 2-byte ����r
		friend[i].name = (char *)malloc(strLen*sizeof(char));
		status = mxGetString(fieldValue, friend[i].name, strLen);
		if(status != 0) 
			mexWarnMsgTxt("Not enough space. String is truncated.");
	}

	// �C�L C ���c�}�C
	for (i=0; i<elementNum; i++) {
		printf("friend[%d].name = %s\n", i, friend[i].name);
		printf("friend[%d].phone = %f\n", i, friend[i].phone);
	}

	// Free memory
	for (i=0; i<elementNum; i++)
		free(friend[i].name);
	free(friend);
}