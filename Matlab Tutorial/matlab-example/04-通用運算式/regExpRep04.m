str = 'are you ready';
pat = '^([^ ]+) *([^ ]+)';
rep = '$2 $1';
str2 = regexprep(str, pat, rep);
fprintf('��r��G%s\n', str);
fprintf('�ק��G%s\n', str2);