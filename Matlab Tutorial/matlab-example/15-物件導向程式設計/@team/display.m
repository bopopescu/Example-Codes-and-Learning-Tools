function display(team)
% TEAM/DISPLAY Display of a team
out = '';
out = [out, '�W�� = "', team.name, '", '];
out = [out, '���� = "', team.event, '", '];
out = [out, '���� = ['];
for i=1:length(team.member)
	out = [out, personName(team.member(i)), ' '];
end
out = [out, ']'];

disp([inputname(1), ': ', out])