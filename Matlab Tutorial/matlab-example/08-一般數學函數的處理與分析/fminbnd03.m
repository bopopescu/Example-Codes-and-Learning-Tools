opt = optimset( 'disp', 'iter', 'TolX', 0.1);		% ��ܨC�ӨB�J�����G�ó]�w�~�t�e�ԭ�
[x, minValue] = fminbnd(@humps, 0.3, 1, opt)