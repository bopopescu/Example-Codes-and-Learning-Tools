fileName='flanger.wav';
[y, fs]=wavread(fileName);	% Read wave file (Ū�����T��)
sound(y, fs);			% Playback (���񭵰T)
left=y(:,1);			% Left channel (���n�D���T)
right=y(:,2);			% Right channel (�k�n�D���T)
subplot(2,1,1), plot((1:length(left))/fs, left);
subplot(2,1,2), plot((1:length(right))/fs, right);