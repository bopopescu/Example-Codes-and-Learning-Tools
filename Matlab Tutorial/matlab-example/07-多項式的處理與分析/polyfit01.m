warning off			% �R�����C�N�i���ĵ�i�T��
load census.mat
plot(cdate, pop, 'o');
p3 = polyfit(cdate, pop, 3);
cdate2 = 1790:2002;
pop2 = polyval(p3, cdate2);
plot(cdate, pop, 'o', cdate2, pop2, '-');
xlabel('�~��');
ylabel('�H�f�]���G�ʸU�^');
legend('��ڤH�f', '�w���H�f');
popAt2002 = polyval(p3, 2002)