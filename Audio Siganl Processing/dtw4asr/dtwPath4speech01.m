fprintf('This test script shows the DTW path between reference and test inputs.\n');

waveFile1='waveFile/�M�ؤj��01.wav';
waveFile2='waveFile/�M�ؤj��02.wav';
[y1, fs1, nbits1]=wavread(waveFile1);
[y2, fs2, nbits2]=wavread(waveFile2);
%fprintf('Please hit return to play %s...\n', waveFile1); pause; wavplay(y1, fs1, 'sync');
%fprintf('Please hit return to play %s...\n', waveFile2); pause; wavplay(y2, fs2, 'sync');

% EPD
figure;
epdPrm1=epdPrmSet(fs1); plotOpt=1; epInSampleIndex1=epdByVolHod(y1, fs1, nbits1, epdPrm1, plotOpt);
figure;
epdPrm2=epdPrmSet(fs2); plotOpt=1; epInSampleIndex2=epdByVolHod(y2, fs2, nbits2, epdPrm2, plotOpt);
y1=y1(epInSampleIndex1(1):epInSampleIndex1(2));
y2=y2(epInSampleIndex2(1):epInSampleIndex2(2));

mfcc1 = wave2mfcc(y1, fs1);
mfcc2 = wave2mfcc(y2, fs2);

% ====== Solution obtained by DTW
beginCorner=1;
endCorner=1;
[minDist1, dtwPath1, dtwTable1] = dtw1mex(mfcc1, mfcc2, beginCorner, endCorner);
[minDist2, dtwPath2, dtwTable2] = dtw2mex(mfcc1, mfcc2, beginCorner, endCorner);
%[minDist2, dtwPath2, dtwTable2] = dtw1meanDistMex(mfcc1, mfcc2);

% ====== Plotting
figure
subplot(2,2,1); dtwPlot(mfcc1, mfcc2, dtwPath1);
subplot(2,2,2); dtwPlot(mfcc1, mfcc2, dtwPath2);
subplot(2,2,3); pcolor(dtwTable1'); axis image; shading flat
subplot(2,2,4); pcolor(dtwTable2'); axis image; shading flat