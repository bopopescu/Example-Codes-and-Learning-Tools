%demofile = [matlabroot '\extern\examples\eng_mat\engwindemo.c'];
%copyfile(demofile, '.');
optsfile = [matlabroot '\bin\win64\mexopts\msvc100engmatopts.bat'];
fprintf('�i��sĶ...\n');
mex('-f', optsfile, 'engwindemo.c');
dir engwindemo.exe
fprintf('���յ{��...\n');
!engwindemo