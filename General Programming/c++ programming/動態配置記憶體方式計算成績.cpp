#include<iostream>
using namespace std;

int main()
{
    int *p1=new int;
    int *p2=new int;
    cout<<"��J���@�Ӽ�:";
    cin>>*p1;
    cout<<"��J�ĤG�Ӽ�:";
    cin>>*p2;
    cout<<*p1<<"*"<<*p2<<"="<<(*p1)*(*p2)<<"\n";
    delete p1;
    delete p2;  
    system("pause");
    return 0; 
} 
