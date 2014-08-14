load data2.txt
x = data2(:, 1);		% �w������I�� x �y��
y = data2(:, 2);		% �w������I�� y �y��
A = [ones(size(x)) x];
theta = A\log(y);
a = exp(theta(1))		% ���ѱo�줧�Ѽ�
b = theta(2)			% ���ѱo�줧�Ѽ�
theta0 = [a, b];		% fminsearch ���ҩl�Ѽ�
theta = fminsearch(@errorMeasure3, theta0, [], data2);
x = data2(:, 1);
y = data2(:, 2);
y2 = theta(1)*exp(theta(2)*x);
plot(x, y, 'o', x, y2); xlabel('x'); ylabel('y');
legend('Actual value', 'Predicted value');
title('y vs. x');
fprintf('�~�t����M = %d\n', sum((y-y2).^2));