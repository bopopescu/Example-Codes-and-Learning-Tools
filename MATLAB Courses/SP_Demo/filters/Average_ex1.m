% This demo program was developed Hsiao-Lung Chan, Ph.D. October 30, 2009
% Six-point average

clf

fs=1000;
t=0:1/fs:0.2;
x = sin(2*pi*50*t) + 0.5*randn(size(t));
subplot(3,2,1)
plot(t,x)
ylabel('x(n)')
xlabel('Time, s')
title('Sinusoid + random noise')
axis([min(t) max(t) min(x)*1.1 max(x)*1.1])

Xf=fft(x);
resolution=fs/length(Xf);
f=(0:length(Xf)-1)*resolution;
Xf_magnitude = abs(Xf); 
subplot(3,2,2)
index=1:length(Xf_magnitude)/2;
plot(f(index),Xf_magnitude(index))
xlabel('Frequency, Hz')
ylabel('Magnitude')
title('Spectra')
axis([min(f(index)) max(f(index)) 0 max(Xf_magnitude(index))*1.1])

b=[1 0 0 0 0 0 0 0 -2 0 0 0 0 0 0 0 1];
a=[1 -2 1];
% b=Num;
% a=Den;
NFFT=1024;
[h,f] = freqz(b,a,NFFT);
f=f/pi*fs/2;
h_magnitude=abs(h);
h_phase=angle(h);
subplot(3,2,3)
plot(f,h_magnitude);
axis([min(f) max(f) 0 max(h_magnitude)*1.1])
xlabel('Frequency, Hz')
ylabel('Magnitude')
title('Frequency response of filter')

subplot(3,2,4)
plot(f,h_phase);
axis([min(f) max(f) min(h_phase)*1.1 max(h_phase)*1.1])
xlabel('Frequency, Hz')
ylabel('Phase')
title('Frequency response of filter')


y=filter(b,a,x);

t=(0:length(y)-1)/fs;
subplot(3,2,5)
plot(t,y)
ylabel('y(n)')
xlabel('Time, s')
title('Filtered signal')
axis([min(t) max(t) min(y)*1.1 max(y)*1.1])

Yf=fft(y);
resolution=fs/length(Yf);
f=(0:length(Yf)-1)*resolution;
Yf_magnitude = abs(Yf);
subplot(3,2,6)
index=1:length(Yf_magnitude)/2;
plot(f(index),Yf_magnitude(index))
xlabel('Frequency, Hz')
ylabel('Magnitude')
title('Spectra')
axis([min(f(index)) max(f(index)) 0 max(Yf_magnitude(index))*1.1])


