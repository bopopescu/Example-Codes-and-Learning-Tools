load data2.txt
x = data2(:, 1);		% �w������I�� x �y��
y = data2(:, 2);		% �w������I�� y �y��
A = [ones(size(x)) x];
theta = A\log(y);
subplot(2,1,1)
plot(x, log(y), 'o', x, A*theta); xlabel('x'); ylabel('ln(y)');
title('ln(y) vs. x');
legend('Actual value', 'Predicted value');
a = exp(theta(1))			% ���ѱo�줧�Ѽ�
b = theta(2)				% ���ѱo�줧�Ѽ�
y2 = a*exp(b*x);
subplot(2,1,2);
plot(x, y, 'o', x, y2); xlabel('x'); ylabel('y');
legend('Actual value', 'Predicted value');
title('y vs. x');
fprintf('�~�t����M = %d\n', sum((y-y2).^2));