% ��ܭ��q�ιL�s�v
recordViaMic=0;		% �Y�n�ۦ�����A�N���ȧאּ 1

% ====== Get audio data
if recordViaMic,	% �ۦ����
	fs=16000;				% �����W�v
	duration=3;				% �����ɶ�
	waveFile='test.wav';	% ���T�ɮצW��
	dataType='uint8';		% �ѪR�� 8 bits/sample
	nbits=8;
	% ====== Record sound
	fprintf('�����N���}�l %g ������G', duration); pause
	fprintf('������...');
	y=wavrecord(duration*fs, dataType);
	fprintf('��������\n');
else	% Ū�����T�ɮ�
	waveFile='�M�ؤj�Ǹ�T�t.wav';
	[y, fs, nbits]=wavread(waveFile);
	y=y*2^nbits/2;		% �^�_�����ɮשҰO���� uint8 ��ƭ�
end

% ====== Plot time-domain audio data
y=double(y);			% MATLAB 6.5 needs this line
y=y-round(mean(y));		% �s�I�ե�
subplot(4,1,1);
plot((1:length(y))/fs, y);
ylabel('Amplitude'); title(waveFile);
axis([-inf, inf, -2^nbits/2, 2^nbits/2]);

% ====== Frame blocking
frameSize=256;
overlap=84;
frameRate=fs/(frameSize-overlap);
framedY=buffer(y, frameSize, overlap);
frameNum=size(framedY, 2);

% ====== Compute volume
volume=10*log10(mean(framedY.^2));
frameTime=(1:frameNum)*(frameSize-overlap)/fs;
subplot(4,1,2);
plot(frameTime, volume, '.-');
title('���q'); ylabel('Volume (Decibel)');
set(gca, 'xlim', [min(frameTime) max(frameTime)])

% ====== Compute zero crossing rate
zcr=sum(framedY(1:end-1, :).*framedY(2:end, :)<=0);
frameTime=(1:frameNum)*(frameSize-overlap)/fs;
subplot(4,1,3);
plot(frameTime, zcr, '.-');
title('�L�s�v'); ylabel('ZCR');
set(gca, 'xlim', [min(frameTime) max(frameTime)])

% ====== Compute zero crossing rate after shifting the original signals
[minVolume, index]=min(volume);
shiftAmount=2*max(abs(framedY(:,index)));	% �H�̤p���q�����ؤ����T���̤j����Ȫ��⭿�������q
framedY=framedY-shiftAmount;
zcr=sum(framedY(1:end-1, :).*framedY(2:end, :)<=0);
frameTime=(1:frameNum)*(frameSize-overlap)/fs;
subplot(4,1,4);
plot(frameTime, zcr, '.-');
xlabel('Time (Seconds)');
ylabel('ZCR');
title('�����ᤧ�L�s�v');
set(gca, 'xlim', [min(frameTime) max(frameTime)])

% ====== Play and save the recorded sound
%fprintf('�����N���}�l����G'); pause
%y2=(y-mean(y))/(2^nbits/2);	% ���� -1 �M 1 ��������
%wavplay(y2,fs);	% ���񭵰T
%wavwrite(y2, fs, 8, waveFile);	% �x�s�ɮ�