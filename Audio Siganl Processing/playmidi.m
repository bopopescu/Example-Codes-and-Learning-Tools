function playmidi(midi, timeUnit)
%PLAYMIDI �Q�έ��ĥd�Ӽ��� midi ���֮榡���o���{��
%	�Ϊk�G
%	playmidi(midi, timeUnit)
%	�䤤 midi �O�@�ӦV�q�A�榡�� [semitone, duration, semitone, duration .........]
%	timeUnit �h�O�W�z duration �ҥΪ��ɶ����A�w�]�O 1/64 ��]�H�t�X CBMR ���Ѩt�Ρ^
%
%	�d�ҡG
%	playmidi([69 1 70 1 71 1], 1)       
%	���n���� do re me ......      �C�ӭ����j�� 1 ��
%
%	��i������J playmidi�A�N�iť�켽��u����v�����֡C

%	Roger Jang, 20010204

if nargin==0, selfdemo; return; end
if nargin<2, timeUnit = 1/64; end

% The time unit used in playmidimex is 1/1000 second.
midi = double(midi);
midi(2:2:end) = midi(2:2:end)*timeUnit*1000;
playmidimex(double(midi));	% �W�����o���{���A�ϥ� PC ��z
%playmidi2mex(double(midi));	% ���g���o���{���A�ϥέ��ĥd

function selfdemo
% ����u����v���e��y
midi = [55 23 55 23 55 23 55 23 57 23 55 35 0 9 57 23 60 69 0 18 64 69 0 18 62 23 62 23 62 23 62 12 60 12 64 23 60 35 0 9 57 12 55 12 55 127];
fprintf('����u����v���e��y ...\n');
feval(mfilename, midi, 1/64);
