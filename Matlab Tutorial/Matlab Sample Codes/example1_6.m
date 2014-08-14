clear
fs = 10000;               % ���˳t�v
t = 0:1/fs:1.5;

subplot(2,1,1)
y1 = sawtooth(2*pi*50*t);
plot(t,y1,'linewidth',2)
axis([0 0.2 -2 2])
xlabel('Time')
ylabel('Amplitude')
title('�����i')
grid on

subplot(2,1,2)
y2 = square(2*pi*50*t);
plot(t,y2,'linewidth',2)
axis([0 0.2 -2 2])
xlabel('Time')
ylabel('Amplitude')
title('��i')
grid on


