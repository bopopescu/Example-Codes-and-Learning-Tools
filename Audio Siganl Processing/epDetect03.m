wavefile='�M�ؤj�Ǹ�T�t.wav';
[wave, fs, nbits] = wavReadInt(wavefile);	% wave �O���

frameSize = 256;
overlap = 128;

wave=wave-mean(wave);				% �s�I�ե�
frameMat=buffer2(wave, frameSize, overlap);	% ���X����
frameNum=size(frameMat, 2);			% ���ت��Ӽ�
sumAbs=sum(abs(frameMat));			% �p�⭵�q
sumAbsDiff1=sum(abs(diff(frameMat)));
sumAbsDiff2=sum(abs(diff(diff(frameMat))));
sumAbsDiff3=sum(abs(diff(diff(diff(frameMat)))));

subplot(5,1,1);
time=(1:length(wave))/fs;
plot(time, wave); ylabel('Amplitude'); title('Waveform'); axis([-inf inf -2^nbits/2 2^nbits/2]);
subplot(5,1,2);
frameTime=frame2sampleIndex(1:frameNum, frameSize, overlap);
plot(frameTime, sumAbs, '.-'); title('sum(abs(frameMat))'); axis tight
subplot(5,1,3);
plot(frameTime, sumAbsDiff1, '.-'); title('sum(abs(diff(frameMat)))'); axis tight
subplot(5,1,4);
plot(frameTime, sumAbsDiff2, '.-'); title('sum(abs(diff(diff(frameMat))))'); axis tight
subplot(5,1,5);
plot(frameTime, sumAbsDiff3, '.-'); title('sum(abs(diff(diff(diff(frameMat)))))'); axis tight