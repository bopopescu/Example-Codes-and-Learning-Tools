pointNum = 10;
[xx, yy, zz] = peaks(pointNum);
zz = zz + randn(size(zz));	% �[�J���T
surf(xx, yy, zz);
axis tight