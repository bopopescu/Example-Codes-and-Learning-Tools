clear
fid = fopen('F:\MATLAB6.5\work\�T���B�z�wMATLAB ������(�d�ҵ{��)\signal_1_11.txt');
x = fscanf(fid,'%7d%8d%8d%8d%8d%8d%8d%8d%8d%8d\n',50000);
fclose(fid);
plot(x,'r');
grid on;
xlabel('Sample No.')
ylabel('Amplitude')
title('Seismic Signal ANMBHZ89')
