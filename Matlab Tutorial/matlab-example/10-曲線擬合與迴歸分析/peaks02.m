pointNum = 10;
[xx, yy, zz] = peaks(pointNum);
zz = zz + randn(size(zz))/10;	% �[�J���T
x = xx(:);	% �ର��V�q
y = yy(:);	% �ର��V�q
z = zz(:);	% �ର��V�q
A = [(1-x).^2.*exp(-(x.^2)-(y+1).^2), (x/5-x.^3-y.^5).*exp(-x.^2-y.^2), exp(-(x+1).^2-y.^2)];  
theta = A\z	% �̨Ϊ� theta ��