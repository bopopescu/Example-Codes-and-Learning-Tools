function endPoint = endPointDetect01(wave, fs, plotOpt, epdPrm)
% endPointDetect: �ھڭ��q�Ӷi����I����
%	Usage: endPoint = endPointDetect(wave, fs, plotOpt, epdPrm)
%		endPoint: ���I�]���׬� 2 ���V�q�^
%		wave: ��J���n���i��
%		fs: �����W�v�]�u���b�e�Ϥ~�Ψ�^
%		plotOpt: �O�_�e�X�����ϧ�
%		epdPrm: ���I�����������Ѽ�

%	Roger Jang, 20040413

if nargin==0, selfdemo; return; end
if nargin<2, fs=16000; end
if nargin<3, plotOpt=0; end
if nargin<4,
	epdPrm.frameSize = 256;
	epdPrm.overlap = 0;
	epdPrm.volumeRatio=0.1;
end

frameSize=epdPrm.frameSize;
overlap=epdPrm.overlap;

% ====== Zero adjusted
wave = double(wave);				% �ন��ƫ��A�O double ���ܼ�
wave = wave-mean(wave);				% �s�I�ե�
frameMat  = buffer(wave, frameSize, overlap);	% ���X����
frameNum = size(frameMat, 2);			% ���ت��Ӽ�
volume = sum(abs(frameMat));			% �p�⭵�q
volumeTh = max(volume)*epdPrm.volumeRatio;	% �p�⭵�q���e��
index = find(volume>volumeTh);			% ��X�W�L���q���e�Ȫ�����

if isempty(index)				% �Y�䤣��A�^�Ǫůx�}
	endPoint=[];
	return
end

endPoint=([index(1), index(end)]-1)*(frameSize-overlap)+frameSize/2;	% �� frame index �ন sample index

if plotOpt,
	subplot(2,1,1);
	time=(1:length(wave))/fs;
	plot(time, wave);
	axis tight;
	line(time(endPoint(1))*[1 1], [min(wave), max(wave)], 'color', 'm');
	line(time(endPoint(end))*[1 1], [min(wave), max(wave)], 'color', 'm');
	ylabel('Amplitude');
	title('Waveform');

	subplot(2,1,2);
	frameTime=(((1:frameNum)-1)*(frameSize-overlap)+frameSize/2)/fs;
	plot(frameTime, volume, '.-');
	axis tight;
	line([min(frameTime), max(frameTime)], volumeTh*[1 1], 'color', 'r');
	line(frameTime(index(1))*[1 1], [0, max(volume)], 'color', 'm');
	line(frameTime(index(end))*[1 1], [0, max(volume)], 'color', 'm');
	ylabel('Sum of abs.');
	title('Volume');
end

% ====== Self demo
function selfdemo
wavefile='�M�ؤj�Ǹ�T�t.wav';
[wave, fs, nbits] = wavread(wavefile);
wave=wave*(2^nbits/2);
plotOpt = 1;
epdPrm.frameSize = 256;
epdPrm.overlap = 0;
epdPrm.volumeRatio = 0.05;
out = feval(mfilename, wave, fs, plotOpt, epdPrm);