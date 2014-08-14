x = 6*rand(100, 1)-3;	% [-3, 3] ������ 100 �ӧ��ä��G�ü�  
y = 6*rand(100, 1)-3;	% [-3, 3] ������ 100 �ӧ��ä��G�ü�  
z = peaks(x, y);  
[xi, yi] = meshgrid(-3:0.2:3, -3:0.2:3);  
zi = griddata(x, y, z, xi, yi);
mesh(xi, yi, zi);	% �e�X����
hold on; plot3(x, y, z, 'o'); hold off	% �e�X����I
axis tight; hidden off;
figure; mesh(xi, yi, zi); view(2); axis image	% ����������
hold on; plot3(x, y, z+1, 'o'); hold off		% �e�X����I
k = convhull(x, y);
line(x(k), y(k), 10*ones(1,length(k)), 'color', 'r');	% Add convex hull