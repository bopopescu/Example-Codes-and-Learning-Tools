fplot(@humps, [-1, 2]); grid on
z1 = fzero(@humps, 1.5);
z2 = fzero(@humps, [-1, 1]);
line(z1, humps(z1), 'marker', 'o', 'color', 'r');	% �e�X�Ĥ@�ӹs�I����m
line(z2, humps(z2), 'marker', 'o', 'color', 'r');	% �e�X�ĤG�ӹs�I����m