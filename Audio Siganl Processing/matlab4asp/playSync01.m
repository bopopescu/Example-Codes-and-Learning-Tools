[y, fs]=wavread('welcome.wav');
wavplay(y, 1.0*fs, 'sync');		% Synchronous playback (�P�B���� 1.0 ���t�ת����T)
wavplay(y, 0.8*fs, 'async');	% Asynchronous playback at 0.8 of the original speed (�D�P�B���� 0.8 ���t�ת����T)
wavplay(y, 0.6*fs);				% Asynchronous playback at 0.6 of the original speed (�D�P�B���� 0.6 ���t�ת����T)