warning off			% �R�����C�N�i���ĵ�i�T��
load census.mat
cdate2 = min(cdate):(max(cdate)+30);
curve = zeros(7, length(cdate2));
for i = 1:7
	curve(i,:) = polyval(polyfit(cdate,pop,i), cdate2);
end
plot(cdate, pop,'o', cdate2, curve);
legend('��ڸ��', 'order=1', 'order=2','order=3', 'order=4', 'order=5', 'order=6', 'order=7');
xlabel('�~��');
ylabel('�H�f�]���G�ʸU�^');