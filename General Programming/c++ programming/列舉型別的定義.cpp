#include<iostream>
using namespace std;

enum DayOfWeek
{
   Sunday,
   Monday,
   Tuesday=5,
   Wednesday,
   Thursday,
   Friday=5,
   Saturday  
}; 

int main()
{
   cout<<"Monday����="<<Monday<<endl;
   cout<<"Wednesday����="<<Wednesday<<endl;
   cout<<"Friday����="<<Friday<<endl; 
   system("pause");
   return 0; 
}
