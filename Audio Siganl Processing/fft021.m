% ���d�Үi�ܤ@��²�楿���i���ť߸��ഫ�A�H�����W�Ш����
% �������i���W�v�ꥩ�O freqStep ���D��ƭ��A�ҥH�����W�з|�u���}�v(Smearing)

k = 10.5;			% �D��ƭ� 
N = 256;			% �I��
fs = 8000;			% �����W�v
freqStep = fs/N;		% �W�쪺�W�v���ѪR��
f = k*freqStep;			% �����i���W�v
freq = freqStep*(-N/2:N/2-1);	% �W�쪺�W�v���
time = (0:N-1)/fs;		% �ɰ쪺�ɶ����
y = cos(2*pi*f*time);		% Signal to analyze
Y = fft(y);			% Spectrum
Y = fftshift(Y);

% Plot time data
subplot(3,1,1);
time2 = (0:0.1:N-1)/fs;		% Interpolated time axis
plot(time2, cos(2*pi*f*time2));
line(time, y, 'color', 'r', 'marker', '.', 'linestyle', 'none');
title('Sinusoidal signals');
xlabel('Time (seconds)'); ylabel('Amplitude');
axis tight

% Plot spectral magnitude
magY = abs(Y);
subplot(3,1,2);
plot(freq, magY, '.-b'); grid on
xlabel('Frequency)'); 
ylabel('Magnitude (Linear)');

% Plot phase
phaseY = unwrap(angle(Y));
subplot(3,1,3);
plot(freq, phaseY, '.-b'); grid on
xlabel('Frequency)'); 
ylabel('Phase (Radian)');
