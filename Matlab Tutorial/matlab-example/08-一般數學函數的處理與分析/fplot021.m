subplot(2,1,1);
fplot('sin(2*x)+cos(x)', [-10, 10])		% �ϥΦr����w�禡
subplot(2,1,2);
fplot(@(x)sin(2*x)+cos(x), [-10, 10])		% �ϥΨ禡����ӫ��w�禡