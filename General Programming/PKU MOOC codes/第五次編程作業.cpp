#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <iostream>
using namespace std;

int Time = 0;  //�C���ɶ� 

class weapon{
public:
	string name;
	int num;
};  //�Ъ`�N�A�b�H�Z���D�ب��A�Z�h���h��A�ͩR�ȡA���۫צb��ͦs�������i��o���ܤơA�����@�ΡA�Z�h�⤤���Z��?�ۨϥΧ����O�]�|�o���ܤơC

class warrior{
public:
	string name;
	int num;
	int strength;
	float mind;  //�h�� 
	int loyalty;  //���۫� 
	int weapon1;
	int weapon2;
};

class redhq{
public:
	int life;   //�ͩR�� 
	int assign;  //�Z�h���� 
	warrior war[5];
	weapon weap[3];
	redhq(int N){
		assign = 0;
		war[0].name="iceman";
		war[1].name="lion";
		war[2].name="wolf";
		war[3].name="ninja";
		war[4].name="dragon";
		weap[0].name="sword";
		weap[1].name="bomb";
		weap[2].name="arrow";
		for(int i = 0; i < 5; i++)
			war[i].num = 0;
		for(int j = 0; j < 2; j++)
			weap[j].num = j;
		life = N;
	}
	bool enough(){
		for(int i = 0; i<5; i++)
			if(life>=war[i].strength)
				return true;
		return false;
	}  //�P�_�ٯण��A�y 
	int born(int a_time,int red_pos){
		int pos;
		for(int i = 0 ; i< 5; i++){
			pos = (red_pos+i)%5;
			if(life >= war[pos].strength){
				assign = pos;
				break;
			}
		}  //�P�_���ӪZ�h�ٯ�A�y 
		war[assign].num++;
		life -= war[assign].strength;
		if(assign==1){war[assign].loyalty=life;}
		if(assign==0){war[assign].weapon1=(a_time+1)%3;}
		if(assign==4){war[assign].weapon1=(a_time+1)%3;war[assign].mind = (float)life/war[assign].strength;}
		if(assign==3){war[assign].weapon1=(a_time+1)%3;war[assign].weapon2=(a_time+2)%3;}
		printf("%03d red %s %d born with strength %d,%d %s in red headquarter\n",a_time,war[assign].name.c_str(),a_time+1,
			war[assign].strength,war[assign].num,war[assign].name.c_str());  // .c_str()��Nstring�নchar* 
		if(assign==4){printf("It has a %s,and it's morale is %.2f\n",weap[war[assign].weapon1].name.c_str(),war[assign].mind);}
		if(assign==3){printf("It has a %s and a %s\n",weap[war[assign].weapon1].name.c_str(),weap[war[assign].weapon2].name.c_str());}
		if(assign==0){printf("It has a %s\n",weap[war[assign].weapon1].name.c_str());}
		if(assign==1){printf("It's loyalty is %d\n",war[assign].loyalty);}

		return assign+1;  //��s�i��,�i���U�@�� 
	}
};
class bluehq{
public:
	int life;
	int assign;
	warrior war[5];
	weapon weap[3];
	bluehq(int N){
		assign = 0;
		war[0].name="lion";
		war[1].name="dragon";
		war[2].name="ninja";
		war[3].name="iceman";
		war[4].name="wolf";
		weap[0].name="sword";
		weap[1].name="bomb";
		weap[2].name="arrow";
		for(int i = 0; i < 5; i++)
			war[i].num = 0;
		for(int j = 0; j < 2; j++)
			weap[j].num = j;
		life = N;
	}
	bool enough(){
		for(int i = 0; i<5; i++)
			if(life>=war[i].strength)
				return true;
		return false;
	}  //�P�_�ٯण��A�y 
	int born(int a_time,int blue_pos){
		int pos;
		for(int i = 0 ; i< 5; i++){
			pos = (blue_pos+i)%5;
			if(life >= war[pos].strength){
				assign = pos;
				break;
			}
		}  //�P�_���ӪZ�h�ٯ�A�y 
		war[assign].num++;
		life -= war[assign].strength;
		if(assign==0){war[assign].loyalty=life;}
		if(assign==3){war[assign].weapon1=(a_time+1)%3;}
		if(assign==1){war[assign].weapon1=(a_time+1)%3;war[assign].mind = (float)life/war[assign].strength;}  //���w�ন�B�I�� 
		if(assign==2){war[assign].weapon1=(a_time+1)%3;war[assign].weapon2=(a_time+2)%3;}
		printf("%03d blue %s %d born with strength %d,%d %s in blue headquarter\n",a_time,war[assign].name.c_str(),a_time+1,
			war[assign].strength,war[assign].num,war[assign].name.c_str());  //// .c_str()��Nstring�নchar* 
		if(assign==1){printf("It has a %s,and it's morale is %.2f\n",weap[war[assign].weapon1].name.c_str(),war[assign].mind);}
		if(assign==2){printf("It has a %s and a %s\n",weap[war[assign].weapon1].name.c_str(),weap[war[assign].weapon2].name.c_str());}
		if(assign==3){printf("It has a %s\n",weap[war[assign].weapon1].name.c_str());}
		if(assign==0){printf("It's loyalty is %d\n",war[assign].loyalty);}

		return assign+1;  //��s�i��,�i���U�@�� 
	}
};

int main(int argc, char* argv[])
{
	int n,life;
	int value[5]={0,0,0,0,0};
	
	cin >> n;  //case�� 
	for(int j = 1; j<=n; j++)
	{
		int redMade = 0;  //�������i�� 
    	int blueMade = 0;  //�Ŧ����i�� 
        Time = 0;
		cin >> life;  //�ͩR�� 
		for(int i=0; i<5; i++)
			cin >> value[i];

		cout << "Case:" << j << endl;
		redhq red(life);
		red.war[0].strength = value[2];
		red.war[1].strength = value[3];
		red.war[2].strength = value[4];
		red.war[3].strength = value[1];
		red.war[4].strength = value[0];

		bluehq blue(life);
		blue.war[0].strength = value[3];
		blue.war[1].strength = value[0];
		blue.war[2].strength = value[1];
		blue.war[3].strength = value[2];
		blue.war[4].strength = value[4];

		int flagRed = 1;
		int flagBlue = 1;
		while(red.enough() || blue.enough())
		{
			if(red.enough())
			{
                redMade = red.born(Time,redMade);
			}
			else if(flagRed)
			{	
				printf("%03d red headquarter stops making warriors\n",Time);
				flagRed = 0;
			}

			if(blue.enough())
			{
				blueMade = blue.born(Time,blueMade);
			}
			else if(flagBlue)
			{	
				printf("%03d blue headquarter stops making warriors\n",Time);
				flagBlue = 0;
			}
			Time++;
		}
		if(flagRed)
			printf("%03d red headquarter stops making warriors\n",Time);

		if(flagBlue)
			printf("%03d blue headquarter stops making warriors\n",Time);

	}
	system("pause");
	return 0;
}
