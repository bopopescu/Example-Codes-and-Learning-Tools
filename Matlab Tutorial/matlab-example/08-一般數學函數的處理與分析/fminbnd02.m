opt = optimset('disp', 'iter');		% ��ܨC�ӨB�J�����G
[x, minValue] = fminbnd(@humps, 0.3, 1, opt)