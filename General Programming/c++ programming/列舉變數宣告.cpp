#include<iostream>
using namespace std;

enum MouseKeys 
{
   Left=0,
   Right=1,
   Middle=2,  
}; 

int main()
{
   enum MouseKeys button;
   button=Middle;
   if(button==Left)
      cout<<"�ƹ������="<<Left<<endl; 
   else if(button==Right)
      cout<<"�ƹ��k���="<<Right<<endl; 
   else if(button==Middle)
      cout<<"�ƹ�������="<<Middle<<endl;  
   system("pause");
   return 0; 
}
