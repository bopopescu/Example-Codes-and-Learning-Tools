load census.mat				% ���J�H�f���
A = [ones(size(cdate)), cdate, cdate.^2];
theta = A\pop;				% �Q�Ρu�����v�A��X�̨Ϊ� theta ��
t=2000; pop2000 = [1, t, t^2]*theta;	% �b 2000 �~����H�f�u�ƹw����
t=2010; pop2010 = [1, t, t^2]*theta;	% �b 2010 �~����H�f�u�ƹw����
fprintf('����H�f�b2000�~���w���� = %g �]�ʸU�H�^\n', pop2000);
fprintf('����H�f�b2010�~���w���� = %g �]�ʸU�H�^\n', pop2010);