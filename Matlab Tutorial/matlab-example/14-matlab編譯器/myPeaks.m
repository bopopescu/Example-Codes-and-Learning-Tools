function myPeaks(n)
%myPeaks: Plot the peaks function

%	Roger Jang, 20120806

if (isstr(n))			% �Y��J�O�r��A�ন�ƭ�
	n=eval(n);
end
peaks(n);
