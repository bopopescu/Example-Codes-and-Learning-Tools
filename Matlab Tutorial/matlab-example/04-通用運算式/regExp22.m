str = 'a--b---b----d';
pat1 = 'a(.*)b(.*)d';	% �u�V���V�g�v�����
[start, finish, token] = regexp(str, pat1);
fprintf('�u�V���V�g�v����ﵲ�G�G\n');
for j=1:size(token{1},1)
	fprintf('\ttoken%d = "%s"\n', j, str(token{1}(j,1):token{1}(j,2)));
end
pat2 = 'a(.*?)b(.*)d';	% �Q�ΰݸ��ӭץ�
[start, finish, token] = regexp(str, pat2);
fprintf('�Q�ΰݸ��Ӷi��u�̤p���v�����G�G\n');
for j=1:size(token{1},1)
	fprintf('\ttoken%d = "%s"\n', j, str(token{1}(j,1):token{1}(j,2)));
end