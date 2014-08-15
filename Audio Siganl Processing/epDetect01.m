wavefile='�M�ؤj�Ǹ�T�t.wav';
[wave, fs, nbits] = wavReadInt(wavefile);	% wave �O���

frameSize = 256;
overlap = 128;

wave=wave-mean(wave);				% �s�I�ե�
frameMat=buffer2(wave, frameSize, overlap);	% ���X����
frameNum=size(frameMat, 2);			% ���ت��Ӽ�
volume=sum(abs(frameMat));			% �p�⭵�q
volumeTh1=max(volume)*0.1;			% ���q���e�Ȥ��@
volumeTh2=min(volume)*5;			% ���q���e�Ȥ��G
volumeTh3=volume(1)*4;				% ���q���e�Ȥ��T
index1 = find(volume>volumeTh1);		% ��X�W�L���q���e�Ȥ��@������
index2 = find(volume>volumeTh2);		% ��X�W�L���q���e�Ȥ��G������
index3 = find(volume>volumeTh3);		% ��X�W�L���q���e�Ȥ��T������
endPoint1=frame2sampleIndex([index1(1), index1(end)], frameSize, overlap);	% �� frame index �ন sample index
endPoint2=frame2sampleIndex([index2(1), index2(end)], frameSize, overlap);	% �� frame index �ন sample index
endPoint3=frame2sampleIndex([index3(1), index3(end)], frameSize, overlap);	% �� frame index �ন sample index

subplot(2,1,1);
time=(1:length(wave))/fs;
plot(time, wave);
ylabel('Amplitude'); title('Waveform');
axis([-inf inf -2^nbits/2 2^nbits/2]);
line(time(endPoint1(  1))*[1 1], 2^nbits*[-1, 1], 'color', 'm');
line(time(endPoint1(end))*[1 1], 2^nbits*[-1, 1], 'color', 'm');
line(time(endPoint2(  1))*[1 1], 2^nbits*[-1, 1], 'color', 'g');
line(time(endPoint2(end))*[1 1], 2^nbits*[-1, 1], 'color', 'g');
line(time(endPoint3(  1))*[1 1], 2^nbits*[-1, 1], 'color', 'k');
line(time(endPoint3(end))*[1 1], 2^nbits*[-1, 1], 'color', 'k');

subplot(2,1,2);
frameTime=frame2sampleIndex(1:frameNum, frameSize, overlap);
plot(frameTime, volume, '.-');
ylabel('Sum of Abs.'); title('Volume');
axis tight;
line([min(frameTime), max(frameTime)], volumeTh1*[1 1], 'color', 'm');
line(frameTime(index1(  1))*[1 1], [0, max(volume)], 'color', 'm');
line(frameTime(index1(end))*[1 1], [0, max(volume)], 'color', 'm');
line([min(frameTime), max(frameTime)], volumeTh2*[1 1], 'color', 'g');
line(frameTime(index2(  1))*[1 1], [0, max(volume)], 'color', 'g');
line(frameTime(index2(end))*[1 1], [0, max(volume)], 'color', 'g');
line([min(frameTime), max(frameTime)], volumeTh3*[1 1], 'color', 'k');
line(frameTime(index3(  1))*[1 1], [0, max(volume)], 'color', 'k');
line(frameTime(index3(end))*[1 1], [0, max(volume)], 'color', 'k');