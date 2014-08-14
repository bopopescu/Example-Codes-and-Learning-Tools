function display(p)
% PERSON/DISPLAY Display of a person

out = '';
out = [out, '�m�W = ', p.name];
out = [out, ', �ʧO = ', p.gender];
if p.height>0
	out = [out, ', ���� = ', num2str(p.height) ' m'];
end
if p.weight>0
	out = [out, ', �魫 = ', num2str(p.weight) ' kg'];
end

disp([inputname(1), ': ', out])