fileName='welcome.wav';
[y, fs, nbits]=wavread(fileName);
y0=y*(2^nbits/2)+(2^nbits/2);		% y0 is the original values stored in the wav file (y0 �O����x�s�b���T�ɮפ�����)
difference=sum(abs(y0-round(y0)))