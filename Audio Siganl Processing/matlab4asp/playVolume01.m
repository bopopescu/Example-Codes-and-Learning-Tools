[y, fs]=wavread('welcome.wav');
wavplay(1*y, fs, 'sync');	% Playback with original amplitude (���� 1 ���_�T�����T)
wavplay(3*y, fs, 'sync');	% Playback with 3 times the original amplitude (���� 3 ���_�T�����T)
wavplay(5*y, fs, 'sync');	% Playback with 5 times the original amplitude (���� 5 ���_�T�����T)