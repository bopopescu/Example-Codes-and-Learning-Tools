load data.txt
theta0 = [0 0 0 0];
tic
theta = fminsearch(@errorMeasure1, theta0, [], data);
fprintf('�p��ɶ� = %g\n', toc);
x = data(:, 1);
y = data(:, 2);
y2 = theta(1)*exp(theta(3)*x)+theta(2)*exp(theta(4)*x);  
plot(x, y, 'ro', x, y2, 'b-');  
legend('Sample data', 'Regression curve');
fprintf('�~�t����M = %d\n', sum((y-y2).^2));