waveFile='�M�ؤj�Ǹ�T�t.wav';
plotOpt = 1;
[y, fs, nbits] = wavReadInt(waveFile);
endPoint = endPointDetect(y, fs, nbits, plotOpt);