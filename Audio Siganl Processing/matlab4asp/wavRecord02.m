fs=16000;		% Sampling rate (�����W�v)
duration=2;		% Recording duration (�����ɶ�)
channel=1;		% Mono (���n�D)
fprintf('Press any key to start %g seconds of recording...', duration); pause
fprintf('Recording...');
y=wavrecord(duration*fs, fs, channel, 'uint8');		% duration*fs is the number of total sample points
fprintf('Finished recording.\n');
fprintf('Pressy any key to hear the recording...'); pause; fprintf('\n');
wavplay(y,fs);
