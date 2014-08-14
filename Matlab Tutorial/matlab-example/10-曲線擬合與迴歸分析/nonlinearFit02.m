load data.txt
lambda0 = [0 0];
tic
lambda = fminsearch(@errorMeasure2, lambda0, [], data);
fprintf('�p��ɶ� = %g\n', toc);
x = data(:, 1);
y = data(:, 2);
A = [exp(lambda(1)*x) exp(lambda(2)*x)];
a = A\y;
y2 = A*a;
plot(x, y, 'ro', x, y2, 'b-');
legend('Sample data', 'Regression curve');
fprintf('�~�t����M = %d\n', sum((y-y2).^2));