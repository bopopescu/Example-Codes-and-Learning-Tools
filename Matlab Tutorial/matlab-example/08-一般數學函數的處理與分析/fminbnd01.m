[x, minValue] = fminbnd(@humps, 0.3, 1)			% �ϥ� fminbnd ���O��X�̤p�Ȫ��o���I
fplot(@humps, [0.3, 1]); grid on
line(x, minValue, 'marker', 'o', 'color', 'r');		% Plot the minimum point