x = fzero(@humps, 1.5);		% �D�a�� 1.5 ���񪺮�
y = humps(x);			% �a�J�D��
fprintf('humps(%f) = %f\n', x , y);