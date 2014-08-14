clear
m = [0 0 1 1 0 0 1 1 0 0];
f = [0 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 1];
[b,a] = yulewalk(10,f,m);
[h,w] = freqz(b,a,128);
plot(f,m,'--',w/pi,abs(h),'-')
xlabel('Normalized Frequency  (\times\pi rad/sample)')
ylabel('Magnitude')
title('Magnitude Response')