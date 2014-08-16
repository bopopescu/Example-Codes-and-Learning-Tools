waveFile = 'soo.wav';
[y, fs, nbits]=wavread(waveFile);
startIndex=15000;
frameSize=256;
endIndex=startIndex+frameSize-1;
frame = y(startIndex:endIndex);
zeroPaddedFrameSize=16*frameSize;
[hps, freq]=frame2hps(frame, fs, zeroPaddedFrameSize, 1);
[maxValue, maxIndex]=max(hps);
line(freq(maxIndex), hps(maxIndex), 'marker', 'o', 'color', 'r');
fprintf('Pitch frequency = %f Hz\n', freq(maxIndex));