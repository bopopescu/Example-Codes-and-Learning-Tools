string = 'Draft      beer, 			not   people.';
pattern = '\s+';
string2 = regexprep(string, pattern, ' ');	% �N�h�Ӫť����Y���@��
fprintf('��r��G%s\n', string);
fprintf('�ק��G%s\n', string2);