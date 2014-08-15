% This example demonstrates the pair-wise conjugate of DFT (���d�Үi�ܹ�ưT���� DFT �Y�ƪ��@�m��)

N=64;			% Length of vector
x=randn(N, 1);
z=fft(x);
plot(z, 'o'); grid on
%compass(z);
% Connect conjugate pairs (�N�W�U��٪��@�m�I�s�_��)
for i=2:N/2+1
	twoPoint=z([i, N-i+2]);
	line(real(twoPoint), imag(twoPoint), 'color', 'r');
end