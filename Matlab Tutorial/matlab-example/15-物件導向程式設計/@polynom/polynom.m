function poly = polynom(vec)
%POLYNOM Polynomial class constructor
%	poly = POLYNOM(vec) creates a polynomial object from the given vector vec
%	which contains the coefficients of the descending-order polynomial.

if isa(vec, 'polynom')		% �Y vec �w�g�O polynom ����A�h�����]�w����X
	poly = vec;
else
	poly.c = vec(:).';		% �N�V�q�]�w�� poly ���Y��
	poly = class(poly, 'polynom');	% �N poly �[���� polynom ����
end