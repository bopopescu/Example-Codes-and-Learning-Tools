#include<iostream>
using namespace std;

union student 
{
   char grade[12];
   int score; 
}; 

int main()
{
   union student David;
   char ch;
   cout<<"�п�ܿ�J����:(1)�H���Ƶ���(2)�H���ĵ���";
   cin>>ch;
   switch(ch)
   {
      case '1':
         cout<<"�п�J����:";
         cin>>David.score;
         break;
      case '2':
         cout<<"�п�J����:";
         cin>>David.grade;
         break;    
   }
   cout<<"David�e�ΰO����="<<sizeof(David)<<endl;
   cout<<"����:"<<David.score<<endl; 
   cout<<"����:"<<David.grade<<endl;
   system("pause");
   return 0; 
}
