load census.mat			% ���J�H�f���
plot(cdate, pop, 'o');		% cdate �N��~�סApop �N��H�f�`��
A = [ones(size(cdate)), cdate, cdate.^2];
y = pop;
theta = A\y;			% �Q�Ρu�����v�A��X�̨Ϊ� theta ��
plot(cdate, pop, 'o', cdate, A*theta, '-');
legend('��ڤH�f��', '�w���H�f��');
xlabel('�~��');
ylabel('����H�f�`��');