htkPrm=htkParamSet;
maxMixNum=8;

for i=1:maxMixNum
	htkPrm.mixtureNum=i;
	fprintf('====== %d/%d\n', i, maxMixNum);
	[trainRR(i), testRR(i)]=htkTrainTest(htkPrm);
end

plot(1:maxMixNum, trainRR, 'o-', 1:maxMixNum, testRR, 'o-');
xlabel('No. of mixtures'); ylabel('Recog. rate (%)');
legend('Inside test', 'Outside test');