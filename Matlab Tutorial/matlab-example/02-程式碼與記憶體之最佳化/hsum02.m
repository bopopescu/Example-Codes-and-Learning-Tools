fprintf('MATLAB version = %s\n', version);
ns = 1000*(1:1000);
for i=1:length(ns)
	n=ns(i);
	% �Ĥ@�ؤ�k�Gfor-loop operation
	tic
	total1 = 0;
	for j = 1:n
		total1 = total1+1/j;
	end
	time1 = toc;
	% �ĤG�ؤ�k�Gvectorized operation
	tic
	total2 = sum(1./(1:n));
	time2 = toc;
	% �p�⭿��
	speedupFactor(i)=time1/time2;
end
plot(ns, speedupFactor, '.-'); grid on
xlabel('n'); ylabel('time1/time2');