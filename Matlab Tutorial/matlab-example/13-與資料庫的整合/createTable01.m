copyfile('score01.mdb', 'score02.mdb');	% �N score01.mdb ������ score02.mdb
dsn = 'dsnScore02';			% �]�w��ƨӷ��W�١]���� score02.mdb�^
logintimeout(5);			% �]�w���ճs����Ʈw���ɶ�
conn = database(dsn, '', '');		% �s����Ʈw
% ���ͷs��ƪ� friend
sql = 'CREATE TABLE friend (fullName char(6), birthday date)';
exec(conn, sql);
% ���J�Ĥ@�����
sql = 'INSERT INTO friend (fullName, birthday) VALUES (''��ã��'', ''1983/11/03'')';
exec(conn, sql);
% ���J�ĤG�����
sql = 'INSERT INTO friend (fullName, birthday) VALUES (''���μz'', ''1982/09/22'')';
exec(conn, sql);
% �C�X�Ҧ����
cursor = exec(conn, 'select * from friend');	
cursor = fetch(cursor);
friend = cursor.data				% ��ܧ�s�� friend ��ƪ����
close(cursor);					% ���� cursor ����
close(conn);					% ������Ʈw�s��