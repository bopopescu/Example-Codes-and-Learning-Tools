A = [1 3 4; 2 4 1; 1 6 2];
p = poly(A);		% ��}���S�x�h����
r = roots(p);		% �S�x��{�����ڡA��Y�T����
det(A-r(1)*eye(3))
det(A-r(2)*eye(3))
det(A-r(3)*eye(3))