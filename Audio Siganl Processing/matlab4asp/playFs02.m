[y, fs]=wavread('welcome.wav');
wavplay(y, 1.0*fs, 'sync');	% Playback at the original speed (���� 1.0 ���t�ת����T)
wavplay(y, 0.9*fs, 'sync');	% Playback at 0.9 times the original speed (���� 0.9 ���t�ת����T)
wavplay(y, 0.8*fs, 'sync');	% Playback at 0.8 times the original speed (���� 0.8 ���t�ת����T)
wavplay(y, 0.6*fs, 'sync');	% Playback at 0.6 times the original speed (���� 0.6 ���t�ת����T)