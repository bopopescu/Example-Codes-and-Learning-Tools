n = 1000000;
clear y1;		% �M���ܼ� y1
tic
for i = 1:n
	y1(i) = i^2;
end
time1=toc;
y2 = zeros(1, n);	% �w���t�m�Ҷ��x�} y2
tic
for i = 1:n
	y2(i) = i^2;
end
time2=toc;
fprintf('time1 = %g, time2 = %g, time1/time2 = %g\n', time1, time2, time1/time2);