dsn = 'dsnSong01';				% �]�w��ƨӷ��W�١]���� song01.mdb�^
logintimeout(5);				% �]�w���ճs����Ʈw���ɶ�
conn = database(dsn, '', '');			% �s����Ʈw
sql = 'select * from song';			% �]�w SQL �R�O
cursor = exec(conn, sql);			% ���� SQL �R�O�A�öǦ^ cursor ����
cursor = fetch(cursor, 10);			% �g�� cursor ����A��� 10 �����
fprintf('��Ƶ��� = %d\n', rows(cursor));	% ��ܸ�Ƶ���
fprintf('���Ӽ� = %d\n', cols(cursor));	% ������Ӽ�
fprintf('���W�� = %s\n', columnnames(cursor));% ��ܸ�Ʈw���W��
fprintf('���e�� = %d\n', width(cursor, 3));	% ��ܲĤT����쪺�e��
attributes = attr(cursor, 3)			% ��ܲĤT����쪺�Ҧ���T
close(cursor);					% ���� cursor ����
close(conn);					% ������Ʈw�s��