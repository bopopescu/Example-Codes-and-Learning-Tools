copyfile('score01.mdb', 'score02.mdb');		% �N score01.mdb ������ score02.mdb
dsn = 'dsnScore02';				% �]�w��ƨӷ��W�١]���� score02.mdb�^
logintimeout(5);				% �]�w���ճs����Ʈw���ɶ�
conn = database(dsn, '', '');			% �s����Ʈw
% �]�w�s�W��ƩҥΪ� SQL �R�O
sql = 'INSERT INTO score (studentID, studentName, final) VALUES (''00'', ''�j�O����'', 100)';
cursor = exec(conn, sql);			% ���� SQL �R�O
% �]�w�d�߸�ƥΪ� SQL �R�O
sql = 'select * from score';
cursor = exec(conn, sql);	
cursor = fetch(cursor);
newScore = cursor.data				% ��ܧ�s�� final ��쪺���
close(cursor);					% ���� cursor ����
close(conn);					% ������Ʈw�s��