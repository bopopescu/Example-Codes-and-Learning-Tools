timer1=tic;				% ����@�}�l�p��
n=100*(1:10);
for i=1:length(n)
	timer2=tic;			% ����G�}�l�p��
	z=inv(rand(n(i)));		% inv ���O�O�Ψӭp��ϯx�}
	time(i)=toc(timer2);		% ����G����p��
end
fprintf('Overall time = %f sec\n', toc(timer1));	% ����@����p��
plot(n, time, '.-');
xlabel('Matrix dimensions');
ylabel('Elapsed time (in sec)');