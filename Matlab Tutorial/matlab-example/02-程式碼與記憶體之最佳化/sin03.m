clear all;
ns = 0:2000;
for j=1:length(ns)
	t = 0:ns(j);
	% �Ĥ@�ؤ�k�Gfor-loop operation
	y=zeros(1,length(t));
	tic
	for i = 1:length(t)
		y(i) = sin(t(i)/100);
	end
	time1=toc;
	% �ĤG�ؤ�k�Gvectorized operation
	tic
	y = sin(t)/100;
	time2=toc;
	% �p�⭿��
	ratio(j)=time1/time2;
end
plot(ns, ratio, '.-');
