t1=clock;
t0=cputime;			% �O���{�b���ɶ�
a=inv(rand(1000));		% ����ϯx�}�B��
mesh(a);
myCpuTime = cputime-t0		% �p�� CPU �үӶO���ɶ�
myElapsedTime=etime(clock, t1)