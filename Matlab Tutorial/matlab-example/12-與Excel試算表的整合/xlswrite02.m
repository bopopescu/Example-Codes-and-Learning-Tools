xlsFile = 'output02.xls';
sheetName='7x7�]��}';
[status, message] = xlswrite(xlsFile, magic(7), sheetName)
xlswrite(xlsFile, {'�H�W�O7x7�]��}'; date}, sheetName, 'B8:B9');
dos(['start ' xlsFile]);