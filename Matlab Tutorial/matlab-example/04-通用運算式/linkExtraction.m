fileName = 'regExp.htm';
string=fileread(fileName);
pattern = '<a href="(.*?)">(.*?)</a>';
[start, finish, token] = regexp(string, pattern);
fprintf('���ɮ� "%s" ����X %d �ӳs���G:\n', fileName, length(start));
for i=1:length(start)
	fprintf('\t%d: �s����r�G"%s", �s�����}�G"%s"\n', ...
	i, string(token{i}(2,1):token{i}(2,2)), string(token{i}(1,1):token{i}(1,2)));
end