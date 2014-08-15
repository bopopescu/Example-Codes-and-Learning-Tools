fs=11025;		% Sampling rate (�����W�v)
duration=2;		% Recording duration (�����ɶ�)
waveFile='test.wav';	% Wav file to be saved (���x�s�� wav �ɮ�)
fprintf('Press any key to start %g seconds of recording...', duration); pause
fprintf('Recording...');
y=wavrecord(duration*fs, fs);
fprintf('Finished recording.\n');
fprintf('Press any key to save the sound data to %s...', waveFile); pause
nbits=8;			% Bit resolution (�C�I���ѪR�׬� 8-bit)
wavwrite(y, fs, nbits, waveFile);
fprintf('Finished writing %s\n', waveFile);
fprintf('Press any key to play %s...\n', waveFile);
dos(['start ', waveFile]);	% Start the application for .wav file (�}�һP wav �ɮ׹��������ε{��)