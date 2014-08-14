[x, y, z, v] = flow(10);
[xi, yi, zi] = meshgrid(.1:.25:10, -3:.25:3, -3:.25:3);
vi = interp3(x, y, z, v, xi, yi, zi);
slice(xi, yi, zi, vi, [6 9.5], 2.5, [-2 0]);	% ���ͤ�����
colorbar;	% ����C��P��ƭȪ���Ӫ�
size(xi)