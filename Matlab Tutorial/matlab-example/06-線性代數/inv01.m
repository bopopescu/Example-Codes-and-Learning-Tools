A = pascal(4);		% ���� 4x4 �� Pascal ��}
B = inv(A)
I1 = A*B
I2 = B*A
maxDiff=max(max(abs(eye(4)-I1)))