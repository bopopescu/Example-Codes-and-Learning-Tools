% FFT via least-squares estimate
N=8;
fs=1;
time=(0:N-1)'/fs;
x=rand(N,1)*2-1;

A=ones(N,1);
for i=1:N/2
	A=[A, cos(2*pi*(i*fs/N)*time), sin(2*pi*(i*fs/N)*time)];
end
th=A\x;

plotNum=fix(N/2)+2;
subplot(plotNum, 1, 1);
N2=(N-1)*5+1;		% Insert 4 points between every 2 points for better observation (���I�����J�|�I�A�H�K�[��i��)
timeFine=linspace(min(time), max(time), N2);
x2=th(1)*ones(N,1);
plot(timeFine, th(1)*ones(N2,1), '.-', time, x2, 'or');		% Plot the first term (�e�X�Ĥ@��)
ylabel('Term 0 (DC)'); axis([0 N/fs -1 1]); grid on

for i=1:N/2		% Plot terms 2 to 1+N/2 (�e�X�ĤG�ܲ� 1+N/2 ��)
	freq=i*fs/N;
	y=th(2*i)*cos(2*pi*freq*time)+th(2*i+1)*sin(2*pi*freq*time);	% a term (�C�@��)
	x2=x2+y;
	fprintf('i=%d, sse=%f\n', i, norm(x-x2)/sqrt(N));
	subplot(plotNum, 1, i+1);
	yFine=th(2*i)*cos(2*pi*(i*fs/N)*timeFine)+th(2*i+1)*sin(2*pi*(i*fs/N)*timeFine);	% Finer verison for plotting
	plot(timeFine, yFine, '.-', time, y, 'or'); ylabel(sprintf('Term %d', i));
	axis([0 N/fs -1 1]); grid on
end

% Plot the original signal (�e�X��ӰT��)
subplot(plotNum, 1, plotNum)
plot(time, x, 'o-'); axis([0 N/fs -1 1]); grid on
xlabel('Time'); ylabel('Orig signals');

% Transform LSE result back to fft format for comparison (�N th ��^ fft �ä�����G)
F=fft(x);
F2=[];
F2(1)=th(1)*N;
for i=1:N/2
	F2(i+1)=(th(2*i)-sqrt(-1)*th(2*i+1))*N/2;
	if (i==N/2)
		F2(i+1)=2*F2(i+1);
	end
end
% symmetric of DFT (DFT ����٩�)
for i=N/2+2:N
	F2(i)=F2(N-i+2)';
end

error1=sum(abs(F2-F.'));	% F.' is simple transpose (F.' �O���i��@�m�ഫ����m)
error2=sum(abs(F2-F'));		% F' is conjugate transpose (F' �O�i��@�m�ഫ����m)
fprintf('Errors after transforming LSE to DFT coefficients (�N LSE �ഫ�� DFT �Y�ƪ��~�t)�Gerror1=%f, error2=%f\n', error1, error2);
fprintf('Due to the symmetry of DFT, one of the above error terms should be zero. (�ѩ� DFT ����٩ʡA�W�z�~�t���Ӧ��@�����s�C)\n');