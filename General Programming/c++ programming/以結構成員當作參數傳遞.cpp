#include<iostream>
using namespace std;
void setscore(int &);
void show(char[],int);

struct student
{
   char name[8];
   int chinese; 
}; 

int main()
{
   struct student David={"�i�T",50};
   show(David.name,David.chinese);
   setscore(David.chinese); //or�� : setscore(&David) 
   cout<<"\n���ɦҫ�"<<endl;
   show(David.name,David.chinese);
   system("pause");
   return 0; 
}

void show(char name[],int score)
{
   cout<<"�m�W:"<<name<<endl;
   cout<<"���:"<<score<<endl;  
}

void setscore(int &score)   //���ɳo�̧令 : void setscore(struct student *stu) 
{                          
   score = 60;              //���ɳo�̧令 : stu->chinese=60; 
} 
