#include<iostream>
using namespace std;

int main()
{
    char *p=new char[31];//�ʺA�}�C,�̦h30�Ӧr��
    cout<<"��J�^���r:";
    cin>>p;
    int n=strlen(p);//��X�r�����
    for(int i=0;i<n;i++)
    {
       if(*(p+i)>='A'&&*(p+i)<='Z')//�j�g�ܤp�g 
          *(p+i)+=32;
       else if(*(p+i)>='a'&&*(p+i)<='z')//�p�g�ܤj�g 
          *(p+i)-=32;  
    } 
    cout<<p<<"\n";
    delete[] p; 
    system("pause");
    return 0; 
} 
