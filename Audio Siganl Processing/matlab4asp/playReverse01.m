[y, fs]=wavread('welcome.wav');
wavplay(y, fs, 'sync');			% Playback of the original signal (���񥿱`�����T�i��)
wavplay(-y, fs, 'sync');		% Playback of the up-down flipped signal (����W�U�A�˪����T�i��)
wavplay(flipud(y), fs, 'sync');		% Playback of the left-right flipped signal (����e���A�˪����T�i��)