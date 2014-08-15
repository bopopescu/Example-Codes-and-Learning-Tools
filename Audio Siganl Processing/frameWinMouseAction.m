function frameWinMouseAction(action)
% This function is used in "frame2pitchSimple".

if nargin<1, action='start'; end

switch(action)
	case 'start', % ====== �}�ҹϧε���
		% �]�w�ƹ����s�Q���U�ɪ������ʧ@
		set(gcf, 'WindowButtonDownFcn', [mfilename ' down']);
	case 'down', % ====== �ƹ����s�Q���U�ɪ��������O
		% �]�w�ƹ����ʮɪ��������O
		set(gcf, 'WindowButtonMotionFcn', [mfilename, ' move']);
		% �]�w�ƹ����s�Q����ɪ��������O
		set(gcf, 'WindowButtonUpFcn', [mfilename ' up']);
		% �@�Q���U�A�Y���� checkPitch move
		feval(mfilename, 'move');
	case 'move', % ====== �ƹ����ʮɪ��������O
		% ���o�������
		amdfH=findobj(gcf, 'tag', 'amdf');
		amdfAxisH=findobj(gcf, 'tag', 'amdfAxis');
		frameH=findobj(gcf, 'tag', 'frame');
		minIndexH=findobj(gcf, 'tag', 'minIndex');
		localMinIndexH=findobj(gcf, 'tag', 'localMinIndex');
		amdfAxisH=findobj(gcf, 'tag', 'amdfAxis');
		amdfX=get(amdfH, 'xdata');
		amdfY=get(amdfH, 'ydata');
		% ���o�ƹ��y��
		axes(amdfAxisH);
		amdfXLim=get(amdfAxisH, 'xlim');
		currPt=get(gca, 'CurrentPoint');
		x=currPt(1,1);
		y=currPt(1,2);
		% ��s manualBarH
		manualBarH=findobj(gcf, 'tag', 'manualBar');
		if (amdfXLim(1)<=x) & (x<=amdfXLim(2))
			% ��X�Z���̪��I
			[minDist, amdfIndex]=min(abs(amdfX-x));
		else
			amdfIndex=0;
		end
		set(manualBarH, 'xdata', amdfIndex*[1 1]);
		% ��s mainWindow ����
		mainWindow=get(gcf, 'userdata');
		if ~isempty(mainWindow)
			frameWindow=gcf;
			figure(mainWindow);
			pitch1H=findobj(gcf, 'tag', 'pitch1');
			pitch2H=findobj(gcf, 'tag', 'pitch2');
			pitch3H=findobj(gcf, 'tag', 'pitch3');
			
			pitch1=get(pitch1H, 'ydata');
			load text/frameIndex.txt; frameIndex=text_frameIndex;
			if amdfIndex==0,
				pitch1(frameIndex)=0;
			else
				freq=10*floor((8000+floor((amdfIndex-1)/2))/(amdfIndex-1));
				pitch1(frameIndex)=freq2pitch(freq);
			end
			
			load text/lowVolIndex.txt; lowVolIndex=text_lowVolIndex;
			pitch2=pitch1; pitch2(lowVolIndex)=0;
			pitch3=smoothPitch(pitch2);
			
			pitch1(pitch1==0)=nan;
			pitch2(pitch2==0)=nan;
			pitch3(pitch3==0)=nan;
			set(pitch1H, 'ydata', pitch1);
			set(pitch2H, 'ydata', pitch2);
			set(pitch3H, 'ydata', pitch3);
			
			figure(frameWindow);
		end
	case 'up', % ====== �ƹ����s�Q����ɪ��������O
		% �M���ƹ����ʮɪ��������O
		set(gcf, 'WindowButtonMotionFcn', '');
		% �M���ƹ����s�Q����ɪ��������O
		set(gcf, 'WindowButtonUpFcn', '');
end