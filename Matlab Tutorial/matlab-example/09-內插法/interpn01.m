x1 = -2:0.4:2;
x2 = -2:0.5:2;
x3 = -2:0.3:2;
[x1, x2, x3] = ndgrid(x1, x2, x3);
z = x2.*exp(-x1.^2-x2.^2-x3.^2);
subplot(2,1,1);
slice(x2, x1, x3, z, [-1, 1], [], [0]);
shading interp		% �R����u�A�ç�Υ��ƪ��C��
view(-20, 15);
colorbar;		% ����C��P��ƭȪ���Ӫ�
y1 = -2:0.1:2;
y2 = -2:0.1:2;
y3 = -2:0.1:2;
[y1, y2, y3] = ndgrid(y1, y2, y3);
zz = interpn(x1, x2, x3, z, y1, y2, y3);
subplot(2,1,2);
slice(y2, y1, y3, zz, [-1, 1], [], [0]);
shading interp		% �R����u�A�ç�Υ��ƪ��C��
view(-20, 15);
colorbar;		% ����C��P��ƭȪ���Ӫ�