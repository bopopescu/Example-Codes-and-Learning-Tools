function [out, changed] = smoothPitch(in, plotOpt)
%SMOOTHPITCH Trim the pitch vector, where 0 indicates rest.
%	Note that this file should always be sync with smoothPitchMex.c

%	Roger Jang, 20010331

if nargin==0, selfdemo; return; end
if nargin<2, plotOpt=0; end

out=in; changed=0*in;
pitchScale=10;
plotNum=7; plotIndex=0;

% ��X�����
temp=in; temp(temp==0)=[]; medianPitch=median(temp);

in=out; action='������W�@�Ӽɭ��]�T�I�����O 0�A���I���i�ۮt�W�L 9 �ӥb���A�۾F�I�ۮt�W�L 4 �ӥb���^';
[out, changed]=removeSingleJump(in, pitchScale); 
if plotOpt, plotIndex=plotIndex+1; plotPitch(in, out, changed, plotNum, plotIndex, action); end

in=out; action='���ƤW�@�B';
[out, changed]=removeSingleJump(in, pitchScale); 
if plotOpt, plotIndex=plotIndex+1; plotPitch(in, out, changed, plotNum, plotIndex, action); end

in=out; action='������W�@�Ӧb��t�ɭ��]�����I���� 0�A���I�Υk�I�O 0�^';
[out, changed]=removeSingleJumpAtEdge(in, pitchScale);
if plotOpt, plotIndex=plotIndex+1; plotPitch(in, out, changed, plotNum, plotIndex, action); end

in=out; action='�����s���Ӽɭ��]���I���i�ۮt�W�L 9 �ӥb���A�۾F�I�ۮt�W�L 4 �ӥb���^';
[out, changed]=removeDoubleJump(in, pitchScale); 
if plotOpt, plotIndex=plotIndex+1; plotPitch(in, out, changed, plotNum, plotIndex, action); end

in=out; action='���ƤW�@�B';
[out, changed]=removeDoubleJump(in, pitchScale); 
if plotOpt, plotIndex=plotIndex+1; plotPitch(in, out, changed, plotNum, plotIndex, action); end

in=out; action='pitch���U�I�Pmedian�t����W�L10�ӥb��';
[out, changed]=removeOutOfBound(in, pitchScale); 
if plotOpt, plotIndex=plotIndex+1; plotPitch(in, out, changed, plotNum, plotIndex, action); end

in=out; action='�R�h 00x00';
[out, changed]=removePattern00x00(in, pitchScale); 
if plotOpt, plotIndex=plotIndex+1; plotPitch(in, out, changed, plotNum, plotIndex, action); end


% ====== Sub-functions
% ====== ������W�@�Ӽɭ��]�T�I�����O 0�A���I���i�ۮt�W�L 9 �ӥb���A�۾F�I�ۮt�W�L 4 �ӥb���^
function [out, changed]=removeSingleJump(in, pitchScale);
out=in; changed=0*in;
for i=2:length(in)-1,
	if all(in(i-1:i+1)~=0),
		if abs(in(i-1)-in(i+1))<9*pitchScale, % �]���I���i�ۮt�W�L 9 �ӥb���^
			if abs(in(i)-in(i-1))>4*pitchScale & abs(in(i)-in(i+1))>4*pitchScale,
				out(i) = floor((in(i-1)+in(i+1))/2);
				changed(i) = 1;
			end
		end
	end
end

% ====== ������W�@�Ӧb��t�ɭ��]�����I���� 0�A���I�Υk�I�O 0�^
function [out, changed]=removeSingleJumpAtEdge(in, pitchScale)
out=in; changed=0*in;
i=1;
if (in(i)~=0) & (abs(in(i)-in(i+1))>4*pitchScale) & (in(i+2)~=0)	% �B�z�Ĥ@�I
	out(i)=in(i+1);
	changed(i) = 1;
end
i=length(in);
if (in(i)~=0) & (abs(in(i)-in(i-1))>4*pitchScale) & (in(i-2)~=0)	% �B�z�̫�@�I
	out(i)=in(i-1);
	changed(i) = 1;
end
for i=2:length(in)-2,	% �����I���O 0�A���I�O 0�A�k���I���O 0 
	if (in(i-1)==0) & (in(i)~=0) & (in(i+1)~=0) & (in(i+2)~=0) & (abs(in(i)-in(i+1))>4*pitchScale),	% ���I�O 0
		out(i)=in(i+1);
		changed(i) = 1;
	end
end
for i=3:length(in)-1,	% �����I���O 0�A�k�I�O 0�A�����I���O 0
	if (in(i-2)~=0) & (in(i-1)~=0) & (in(i)~=0) & (in(i+1)==0) & (abs(in(i)-in(i-1))>4*pitchScale),	% �k�I�O 0
		out(i)=in(i-1);
		changed(i) = 1;
	end
end

% ====== �����s���Ӽɭ��]���I���i�ۮt�W�L 9 �ӥb���A�۾F�I�ۮt�W�L 4 �ӥb���^
function [out, changed]=removeDoubleJump(in, pitchScale)
out=in; changed=0*in;
for i=2:length(in)-2,
	if all(in(i-1:i+2)~=0),
		if abs(in(i-1)-in(i+2))<9*pitchScale, % �]���I���i�ۮt�W�L 7 �ӥb���^
%			if abs(in(i)-in(i+1))>4*pitchScale & abs(in(i+2)-in(i+3))>4*pitchScale & abs(in(i+1)-in(i+2))<2*pitchScale
			if abs(in(i-1)-in(i))>4*pitchScale & abs(in(i+1)-in(i+2))>4*pitchScale,
				out(i) = floor((2*in(i-1)+in(i+2))/3);
				out(i+1) = floor((in(i-1)+2*in(i+2))/3);
				changed(i) = 1;
				changed(i+1) = 1;
			end
		end
	end
end

% ====== ����00x00
function [out, changed]=removePattern00x00(in, pitchScale)
out=in; changed=0*in;
if ((in(1)~=0) & (in(2)==0) & (in(3)==0)), out(1)=0; end	% �Ĥ@�I
if ((in(1)==0) & (in(2)~=0) & (in(3)==0) & (in(4)==0)), out(2)=0; end	% �ĤG�I
if ((in(end-2)==0) & (in(end-1)==0) & (in(end)~=0)), out(end)=0; end	% �˼ƲĤ@�I
if ((in(end-3)==0) & (in(end-2)==0) & (in(end-1)~=0) & (in(end)==0)), out(end-1)=0; end	% �˼ƲĤG�I
for i=3:length(in)-2
	if ((in(i-2)==0) & (in(i-1)==0) & (in(i)~=0) & (in(i+1)==0) & (in(i+2)==0)), out(i)=0; end
end
i=1;
if (in(i)~=0) & (abs(in(i)-in(i+1))>4*pitchScale) & (in(i+2)~=0)	% �B�z�Ĥ@�I
	out(i)=in(i+1);
	changed(i) = 1;
end
i=length(in);
if (in(i)~=0) & (abs(in(i)-in(i-1))>4*pitchScale) & (in(i-2)~=0)	% �B�z�̫�@�I
	out(i)=in(i-1);
	changed(i) = 1;
end
for i=2:length(in)-2,	% �����I���O 0�A���I�O 0�A�k���I���O 0 
	if (in(i-1)==0) & (in(i)~=0) & (in(i+1)~=0) & (in(i+2)~=0) & (abs(in(i)-in(i+1))>4*pitchScale),	% ���I�O 0
		out(i)=in(i+1);
		changed(i) = 1;
	end
end
for i=3:length(in)-1,	% �����I���O 0�A�k�I�O 0�A�����I���O 0
	if (in(i-2)~=0) & (in(i-1)~=0) & (in(i)~=0) & (in(i+1)==0) & (abs(in(i)-in(i-1))>4*pitchScale),	% �k�I�O 0
		out(i)=in(i-1);
		changed(i) = 1;
	end
end

% ====== pitch ���U�I�P median �t����W�L10�ӥb��
function [out, changed]=removeOutOfBound(in, pitchScale)
out=in; changed=0*in;
temp=in;
temp(temp==0)=[];
m=median(temp);

%�ղĤ@�I�bbound��
i=1;
while in(i)==0,
    i=i+1;
end
if abs(in(i)-m)>=100,
    j=i;
    while abs(in(j)-m)>=100,
        j=j+1;
    end
    out(i)=in(j);
	changed(i) = 1;
end
%�ճ̫�@�I�bbound��
j=length(in);
while in(j)==0,
    j=j-1;
end
if abs(in(j)-m)>=100,
    k=j;
    while abs(in(k)-m)>=100,
        k=k-1;
    end
    out(j)=in(k);
 	changed(j) = 1;
end
%�դ����I�bbound��
for x=i:length(in)-1,
    if out(x)~=0 & abs(out(x)-m)>=100,
        j=x-1;
        while abs(out(j)-m)>=100,
            j=j-1;
        end
        k=x+1; 
        while abs(out(k)-m)>=100,
            k=k+1;
        end
        for x=j+1:k-1,
            if out(x)~=0,
                if out(k)-out(j) == 0,
                    out(x)=out(j);
                else
                    out(x)=out(j)+(x-j)*intDiv((out(k)-out(j)),(k-j));
                end
                changed(x) = 1;
            end
        end
    end
end


% ====== �e�X pitch
function plotPitch(in, out, changed, plotNum, plotIndex, titleStr)
in(in==0)=nan;
out(out==0)=nan;
% Find median pitch
temp=in;
temp(isnan(temp))=[];
medianPitch=median(temp);
subplot(plotNum,1,plotIndex);
% start plotting
plot(1:length(in), in, '.-b', 1:length(out), out, '.-r');
line([1, length(in)], medianPitch*[1 1], 'color', 'g');
line([1, length(in)], medianPitch*[1 1]+100, 'color', 'r');
line([1, length(in)], medianPitch*[1 1]-100, 'color', 'r');
index = find(changed==1);
line(index, out(index), 'marker', 'o', 'linestyle', 'none', 'color', 'k');
axis tight; grid on
title(titleStr);
xlabel('Time (seconds)');
ylabel('Pitch');

% ====== selfdemo
function selfdemo
% === First plot
testPitch = 10*[5 5 20 4 4];
testPitch = 10*[0 6 7 12 0 0 0 13 7 6 6 0 6 6 0 0 7 7 14 14 8 8 3 2 8 8 7 13 8 8 8 8 13 0 0];
[p1, changed] = feval(mfilename, testPitch, 1);
% === Second plot
waveFile='testWav/yankee doodle.wav';
[pitch, volume, PP]=wave2pitchVolume(waveFile);
pitch(volume<getVolumeThreshold(volume))=0;
figure;
[p1, changed] = feval(mfilename, pitch, 1);