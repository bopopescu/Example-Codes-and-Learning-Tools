dsn = 'dsnSong01';				% �]�w��ƨӷ��W�١]���� song01.mdb�^
logintimeout(5);				% �]�w���ճs����Ʈw���ɶ�
conn = database(dsn, '', '');			% �s����Ʈw
sql = 'select * from song';			% ����]�w SQL �R�O
cursor = exec(conn, sql);			% ���� SQL �R�O�A�öǦ^ cursor ����	
cursor = fetch(cursor, 8);			% �g�� cursor ����A��� 8 �����
songData = cursor.data				% �N��ƶǦ� MATLAB �ܼ� songData
close(cursor);					% ���� cursor ����
close(conn);					% ������Ʈw�s��