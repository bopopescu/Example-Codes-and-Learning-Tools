DSN = 'dsnScore01';				% �]�w��ƨӷ��W�١]���� score01.mdb�^
logintimeout(5);				% �]�w���ճs����Ʈw���ɶ�
conn = database(DSN, '', '');			% �s����Ʈw
sql = 'select * from score order by studentID';	% �]�w SQL �R�O
cursor = exec(conn, sql);			% ���� SQL �R�O�A�öǦ^ cursor ����
cursor = fetch(cursor);				% �g�� cursor ����A����������
score = cursor.data;				% �N��ƶǦ� MATLAB �ܼ� score
temp = columnnames(cursor);			% ��ܸ�Ʈw���W��
eval(['fieldNames = {', temp, '}'';']);		% �N���W�٫��w�� fieldNames �ܼ�
score2 = cell2struct(score, fieldNames, 2);	% �N����}�C score �ন���c�}�C score2
close(cursor);					% ���� cursor ����
close(conn);					% ������Ʈw�s��
struct2html(score2);			% ��ܵ��c�}�C score2 ���s����