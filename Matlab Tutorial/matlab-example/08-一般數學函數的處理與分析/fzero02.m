x = fzero(@humps, [-1, 1]);	% �D����϶� [-1, 1] ����
y = humps(x);			% �a�J�D��
fprintf('humps(%f) = %f\n', x , y);