fprintf('MATLAB version = %s\n', version);
matlabpool local 4

n = 100;
rowMedian1=zeros(1000, 1);
rowMedian2=zeros(1000, 1);
% === �Ĥ@�ؤ�k�Gcommon for-loop
tic
for i = 1:1000
	rowMedian1(i) = max(eig(rand(n)));
end
time1 = toc;
% === �ĤG�ؤ�k�Gparallel for-loop using 2 workers
tic
parfor (i = 1:1000, 2)	% �u�Ψ�Ӯ֤�
	rowMedian2(i) = max(eig(rand(n)));
end
time2 = toc;
fprintf('time1 = %g, time2 = %g, speedup factor = %g\n', time1, time2, time1/time2);

matlabpool close