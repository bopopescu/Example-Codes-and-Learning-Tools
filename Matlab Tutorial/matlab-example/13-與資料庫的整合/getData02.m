dsn = 'dsnScore01';				% �]�w��ƨӷ��W�١]���� score01.mdb�^
logintimeout(5);				% �]�w���ճs����Ʈw���ɶ�
conn = database(dsn, '', '');			% �s����Ʈw
sql = 'select * from score';			% ����]�w SQL �R�O
cursor = exec(conn, sql);			% ���� SQL �R�O�A�öǦ^ cursor ����
setdbprefs('DataReturnFormat', 'structure');	% �]�w cursor �Ǧ^��Ʈ榡�O���c�}�C
cursor = fetch(cursor, 10);			% �g�� cursor ����A��� 10 �����
score = cursor.Data				% �g��Ƴ]�w�� score �ܼ�
close(cursor);					% ���� cursor ����
close(conn);					% ������Ʈw�s��
setdbprefs('DataReturnFormat', 'cellarray');	% ��^�w�]����Ʈ榡