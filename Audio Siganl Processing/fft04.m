% This example demonstrates the DFT of a real-world audio signal (��ܤ@�ӻy�����ت������W��)
[y, fs]=wavread('welcome.wav');
signal=y(2047:2047+237-1);
[mag, phase, freq]=fftOneSide(signal, fs, 1);