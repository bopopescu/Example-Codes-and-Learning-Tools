clear
t = 0:.00025:1;                              % �ɶ��V�q
x = sin(2*pi*30*t) + sin(2*pi*60*t);
y = decimate(x,4);
subplot(121)
stem(x(1:120))                               % ��l�T��
axis([0 120 -2 2])          
title('��l�T��')
subplot(122)
stem(y(1:30))                                % ���W�L���T��
title('���W�L���T��')
