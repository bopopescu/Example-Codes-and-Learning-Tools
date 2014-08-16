% This demo program was developed Hsiao-Lung Chan, Ph.D. September 28, 2009
% Cross spectrum and Coherence

clear

figure(1)
% Generate two sinudoids with the same frequency
fs=5000;
t=0:1/fs:1;
x = sin(2*pi*500*t) + randn(size(t));
y = sin(2*pi*500*t+pi/10) + randn(size(t));

% Welch periodogram
NFFT=512;  % length of window
[P1,f] = pwelch(x,hanning(NFFT),NFFT/2,NFFT);  
f=f/pi*fs/2;
subplot(2,2,1)
plot(f,P1)
ylabel('PSD');
xlabel('Frequency, Hz');
title('Welch Periodogram of x(n)')
axis([min(f) max(f) 0 max(P1)*1.1]);

[P2,f] = pwelch(y,hanning(NFFT),NFFT/2,NFFT);  
f=f/pi*fs/2;
subplot(2,2,2)
plot(f,P2)
ylabel('PSD');
xlabel('Frequency, Hz');
title('Welch Periodogram of y(n)')
axis([min(f) max(f) 0 max(P2)*1.1]);

% Cross spectral analysis
[Pxy,f] = cpsd(x,y,hanning(NFFT),NFFT/2,NFFT);  
f=f/pi*fs/2;
subplot(2,2,3)
plot(f,Pxy)
ylabel('PSD');
xlabel('Frequency, Hz');
title('Cross spectrum of x(n) and y(n)')
axis([min(f) max(f) 0 max(Pxy)*1.1]);

% Coherence
[Cxy,f] = mscohere(x,y,hanning(NFFT),NFFT/2,NFFT); 
f=f/pi*fs/2;
subplot(2,2,4)
plot(f,Cxy)
ylabel('PSD');
xlabel('Frequency, Hz');
title('Coherence of x(n) and y(n)')
axis([min(f) max(f) 0 1]);


figure(2)
% Generate two sinudoids with differenct frequency
fs=5000;
t=0:1/fs:1;
x = sin(2*pi*500*t) + randn(size(t));
y = sin(2*pi*600*t+pi/10) + randn(size(t));

% Welch periodogram
NFFT=512;  % length of window
[P1,f] = pwelch(x,hanning(NFFT),NFFT/2,NFFT);  
f=f/pi*fs/2;
subplot(2,2,1)
plot(f,P1)
ylabel('PSD');
xlabel('Frequency, Hz');
title('Welch Periodogram of x(n)')
axis([min(f) max(f) 0 max(P1)*1.1]);

[P2,f] = pwelch(y,hanning(NFFT),NFFT/2,NFFT);  
f=f/pi*fs/2;
subplot(2,2,2)
plot(f,P2)
ylabel('PSD');
xlabel('Frequency, Hz');
title('Welch Periodogram of y(n)')
axis([min(f) max(f) 0 max(P2)*1.1]);

% Cross spectral analysis
[Pxy,f] = cpsd(x,y,hanning(NFFT),NFFT/2,NFFT);  % PWELCH(X,WINDOW,NOVERLAP,NFFT)
f=f/pi*fs/2;
subplot(2,2,3)
plot(f,abs(Pxy))
ylabel('PSD');
xlabel('Frequency, Hz');
title('Cross spectrum of x(n) and y(n)')
axis([min(f) max(f) 0 max(abs(Pxy))*1.1]);

% Coherence
[Cxy,f] = mscohere(x,y,hanning(NFFT),NFFT/2,NFFT);  % PWELCH(X,WINDOW,NOVERLAP,NFFT)
f=f/pi*fs/2;
subplot(2,2,4)
plot(f,Cxy)
ylabel('PSD');
xlabel('Frequency, Hz');
title('Coherence of x(n) and y(n)')
axis([min(f) max(f) 0 1]);

indexDelta=2:5;
indexTheta=6:8;
indexAlpha=9:13;
indexBeta=14:31;
CohDelta=sum(Cxy(indexDelta))/length(indexDelta);
CohTheta=sum(Cxy(indexTheta))/length(indexDelta);
CohAlpha=sum(Cxy(indexAlpha))/length(indexDelta);
CohBeta=sum(Cxy(indexBeta))/length(indexDelta);
