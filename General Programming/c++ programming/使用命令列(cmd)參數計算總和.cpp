#include<iostream>
using namespace std;

int main(int argc,char *argv[])
{
    int sum=0;
    if(argc==1)//�u���{���W�� 
        cout<<"����J�Ѽ�!"<<"\n";
    else
    {
        for(int i=1;i<argc;i++)
           sum+=atoi(argv[i]);//atoi()�N�r���ର��� 
        cout<<"��J�Ѽ��`�M:"<<sum<<"\n"; 
    } 
    system("pause");
    return 0; 
} 
