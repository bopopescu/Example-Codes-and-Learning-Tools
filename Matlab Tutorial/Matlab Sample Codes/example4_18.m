clear
t = 0:0.001:1;                              % �ɶ��V�q
x = sin(2*pi*30*t) + sin(2*pi*60*t);
y = interp(x,4);
subplot(121)
stem(x(1:30))                               % ��l�T��
axis([0 30 -2 2])          
title('��l�T��')
subplot(122)
stem(y(1:120))                                % ���W�L���T��
axis([0 120 -2 2])
title('���W�L���T��')
