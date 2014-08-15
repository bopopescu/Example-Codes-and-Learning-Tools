waveFile='sunday.wav';
[y, fs, nbits]=wavread(waveFile);
index1=9000;
frameSize=512*2;
index2=index1+frameSize-1;
frame=y(index1:index2);
maxShift=frameSize/2;
method=3;
amdf=frame2amdf(frame, maxShift, method);
amdf2=amdf;
maxFreq=1000;
amdf2(1:fs/maxFreq)=max(amdf);
minFreq=40;
amdf2(fs/minFreq:end)=max(amdf);
[minValue, minIndex]=min(amdf2);

subplot(2,1,1);
plot(frame, '.-'); axis tight; title('Input frame');
subplot(2,1,2);
xVec=1:length(amdf2);
plot(xVec, amdf, '.-', xVec, amdf2, '.-', minIndex, minValue, 'ro');
axis tight; title(sprintf('AMDF vector (method = %d)', method));
legend('Original AMDF', 'Truncated AMDF', 'AMDF pitch point');