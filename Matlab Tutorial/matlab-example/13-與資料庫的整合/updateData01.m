copyfile('score01.mdb', 'score02.mdb');		% �N score01.mdb ������ score02.mdb
dsn = 'dsnScore02';				% �]�w��ƨӷ��W�١]���� score02.mdb�^
logintimeout(5);				% �]�w���ճs����Ʈw���ɶ�
conn = database(dsn, '', '');			% �s����Ʈw
% �]�w��s��ƩҥΪ� SQL �R�O
sql = 'UPDATE score SET final=100 WHERE final>=80';
cursor = exec(conn, sql);			% ���� SQL �R�O
% �]�w�i���Ƭd�ߪ� SQL �R�O
sql = 'select studentName, final from score';	% �]�w SQL �R�O
cursor = exec(conn, sql);	
cursor = fetch(cursor);
newScore = cursor.data				% ��ܧ�s�� final ��쪺���
close(cursor);					% ���� cursor ����
close(conn);					% ������Ʈw�s��