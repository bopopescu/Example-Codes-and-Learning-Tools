load seamount.mat
plot(x, y, '.');
k = convhull(x, y);
hold on, plot(x(k), y(k), 'r'), hold off	% �e�X�̤p�Y�h��