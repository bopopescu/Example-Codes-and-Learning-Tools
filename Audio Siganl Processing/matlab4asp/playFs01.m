[y, fs]=wavread('welcome.wav');
wavplay(y, 1.0*fs, 'sync');	% Playback at the original speed (���� 1.0 ���t�ת����T)
wavplay(y, 1.2*fs, 'sync');	% Playback at 1.2 times the original speed (���� 1.2 ���t�ת����T)
wavplay(y, 1.5*fs, 'sync');	% Playback at 1.5 times the original speed (���� 1.5 ���t�ת����T)
wavplay(y, 2.0*fs, 'sync');	% Playback at 2.0 times the original speed (���� 2.0 ���t�ת����T)