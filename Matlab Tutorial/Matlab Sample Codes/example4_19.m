clear
t = 0:0.01:1.27;                              % �ɶ��V�q
s1 = sin(2*pi*45*t);                          % ��l�T��
s2 = s1 + 0.5*[zeros(1,20) s1(1:108)];
xhat = cceps(s2);
plot(t,xhat)
title('complex cepstrum')
