% This example demonstrates the one-side DFT of a sinusoidal function (���d�Үi�ܤ@��²�楿���i���ť߸��ഫ�A�H�����W�Ш����)
% Since the sinusoidal function has a frequency not a multiple of fs/N, the two-side DFT smears. (�������i���W�v���O freqStep ����ƭ��A�ҥH�����W�з|�u���}�v(Smearing))

N = 256;					% length of vector (�I��)
fs = 8000;					% sample rate (�����W�v)
freqStep = fs/N;				% freq resolution in spectrum (�W�쪺�W�v���ѪR��)
f = 10.5*freqStep;				% freq of the sinusoid (�����i���W�v�A���O freqStep ����ƭ�)
time = (0:N-1)/fs;				% time resolution in time-domain (�ɰ쪺�ɶ����)
signal = cos(2*pi*f*time);			% signal to analyze
[mag, phase, freq]=fftTwoSide(signal, fs, 1);	% compute and plot the two-side DFT