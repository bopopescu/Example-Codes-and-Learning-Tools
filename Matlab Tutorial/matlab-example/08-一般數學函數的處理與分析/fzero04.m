opt = optimset('disp', 'iter');		% ��ܨC�� iteration �����G    
a = fzero(@humps, [-1, 1], opt)