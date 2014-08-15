% Same as fft02.m but use one-side DFT instead (�P fft02.m�A���H�����W�Ш����)
N = 256;					% length of vector (�I��)
fs = 8000;					% sample rate (�����W�v)
freqStep = fs/N;				% freq resolution in spectrum (�W�쪺�W�v���ѪR��)
f = 10.5*freqStep;				% freq of the sinusoid (�����i���W�v�A���O freqStep ����ƭ�)
time = (0:N-1)/fs;				% time resolution in time-domain (�ɰ쪺�ɶ����)
signal = cos(2*pi*f*time);			% signal to analyze
[mag, phase, freq]=fftOneSide(signal, fs, 1);	% Compute and plot one-side DFT