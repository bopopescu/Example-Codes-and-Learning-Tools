function eigValue = myEigValue(mat)
%myEigValue: Generate the eigenvalues of the given matrix

%	Roger Jang, 20080210

if (isstr(mat))			% �Y��J�O�r��A�ন�ƭ�
	mat=eval(mat);
end
[a, b] = eig(mat);
eigValue=diag(b);
disp(eigValue);			% ��ܩT���� eigenValue