load census.mat				% ���J�H�f���
theta = polyfit(cdate, pop, 2);		% �i��G���h�������X�A��X theta ��
fprintf('�b2000�~���w���� = %g �]�ʸU�H�^\n', polyval(theta, 2000));
fprintf('�b2010�~���w���� = %g �]�ʸU�H�^\n', polyval(theta, 2010));