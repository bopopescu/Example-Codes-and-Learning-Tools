options = odeset('OutputFcn', 'odephas3');	% �ϥ� odephas3 �i��ø��
ode45('lorenzOde', [0 10], [20 5 -5]', options);