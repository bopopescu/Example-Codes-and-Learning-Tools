[y, fs]=wavread('sunday.wav');
sound(y, fs);		% Playback of the sound data (���񦹭��T)
time=(1:length(y))/fs;	% Time vector on x-axis (�ɶ��b���V�q)
plot(time, y);		% Plot the waveform w.r.t. time (�e�X�ɶ��b�W���i��)