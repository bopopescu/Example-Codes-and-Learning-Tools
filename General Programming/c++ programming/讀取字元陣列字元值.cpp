#include<iostream>
using namespace std;

int main()
{
    char s[]="Robin";
    cout<<"�H�}�C�覡���s�r��:"<<"\n";
    for(int i=0;i<5;i++)
       cout<<"s["<<i<<"]="<<s[i]<<"\n"; 
    cout<<"�H���Ф覡���s�r��:"<<"\n";
    for(int i=0;i<5;i++)
       cout<<"*(s+"<<i<<")="<<*(s+i)<<"\n";
    system("pause");
    return 0; 
} 
