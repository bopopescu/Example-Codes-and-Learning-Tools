copyfile('score01.mdb', 'score02.mdb');		% �N score01.mdb ������ score02.mdb
dsn = 'dsnScore02';				% �]�w��ƨӷ��W�١]���� score02.mdb�^
logintimeout(5);				% �]�w���ճs����Ʈw���ɶ�
conn = database(dsn, '', '');			% �s����Ʈw
% ��X�Ҧ������
cursor = exec(conn, 'select * from score');	% ���� SQL �R�O�A�öǦ^ cursor ����
cursor = fetch(cursor);				% �g�� cursor ����A����������
score = cursor.data;				% �N��ƶǦ� MATLAB �ܼ� score
temp = columnnames(cursor);			% ��Ʈw���W��
eval(['fieldNames = {', temp, '}'';']);		% �N���W�٫��w�� fieldNames �ܼ�
score = cell2struct(score, fieldNames, 2);	% �N����}�C score �ন���c�}�C
% ��C�@����ƶi��B��A�ñN���G�s�^��Ʈw
for i=1:length(score)
	homework=(score(i).homework1+score(i).homework2+score(i).homework3)/3;
	overallScore=homework*0.3+score(i).midterm*0.3+score(i).final*0.4;
	% �N��Ƽg�J��Ʈw
	sql = ['UPDATE score SET overall=', num2str(overallScore), ' where studentID=''', score(i).studentID, ''''];
	exec(conn, sql);
end
% �C�X score ��ƪ�
cursor = exec(conn, 'select * from score');	
cursor = fetch(cursor);
newScore = cursor.data;				% ��s�᪺���
temp = columnnames(cursor);			% ��Ʈw���W��
eval(['fieldNames = {', temp, '}'';']);		% �N���W�٫��w�� fieldNames �ܼ�
newScore = cell2struct(newScore, fieldNames, 2);% �N����}�C newScore �ন���c�}�C
close(cursor);					% ���� cursor ����
close(conn);					% ������Ʈw�s��
struct2html(newScore);				% ��ܵ��c�}�C newScore ���s����