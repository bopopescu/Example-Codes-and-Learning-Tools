function s = student(name, gender, height, weight, department, year)
%STUDENT Student class constructor

p = person(name, gender, height, weight);	% Person class
s.department = department;	% student �S�����ʽ�
s.year = year;			% student �S�����ʽ�
s = class(s, 'student', p);	% �w�q s �� student ����A�B�~�Ӧ� p �����O