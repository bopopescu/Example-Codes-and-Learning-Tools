copyfile('score01.mdb', 'score02.mdb');		% �N score01.mdb ������ score02.mdb
dsn = 'dsnScore02';				% �]�w��ƨӷ��W�١]���� score02.mdb�^
logintimeout(5);				% �]�w���ճs����Ʈw���ɶ�
conn = database(dsn, '', '');			% �s����Ʈw
exec(conn, 'DELETE * from score');		% ���R���Ҧ������
colNames={'studentId', 'studentName', 'overall'};	% �s�W��ƪ����W��
dataValues={'0001', '������', 100; '0002', '�L�F��', 97};		% �s�W��ƹ���������
insert(conn, 'score', colNames, dataValues);	% �[�J�ⵧ���
cursor = exec(conn, 'select * from score');	
cursor = fetch(cursor);
newScore = cursor.data				% ��ܧ�s�� final ��쪺���
close(cursor);					% ���� cursor ����
close(conn);					% ������Ʈw�s��