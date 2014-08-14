function p = person(name, gender, height, weight)
%PERSON Person class constructor

if isa(name, 'person')		% �Y vec �w�g�O person ����A�h�����]�w����X
	p = name;
else
	p.name = name;
	p.gender = gender;
	if nargin>=3, p.height=height; end
	if nargin>=4, p.weight=weight; end
	
	p = class(p, 'person');	% �N p �[���� person ����
end
