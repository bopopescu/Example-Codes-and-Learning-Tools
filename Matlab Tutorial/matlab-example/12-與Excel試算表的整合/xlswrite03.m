xlsFile = 'output03.xls';
sheetName='��m��ɶ����ܤ�';
data={'�ɶ� (sec)', '��m (m)'};
for i=1:5
	data{i+1,1}=i;
	data{i+1,2}=0.5*9.8*i^2;
end
[status, message] = xlswrite(xlsFile, data, sheetName);
dos(['start ' xlsFile]);