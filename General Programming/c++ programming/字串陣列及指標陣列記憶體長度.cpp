#include<iostream>
using namespace std;

int main()
{
    char fruit1[3][11]={"apple","watermelon","banana"};
    char *fruit2[3]={"apple","watermelon","banana"};
    cout<<"�G���r���}�C:"<<"\n";
    for(int i=0;i<3;i++)
    {
      cout<<"�� "<<i+1<<" �Ӥ���:"<<fruit1[i];
      cout<<",�Ҧ���}:"<<(int *)fruit1[i]<<"\n"; 
    }
    cout<<"\n"; 
    cout<<"���а}�C:"<<"\n";
    for(int i=0;i<3;i++)
    {
      cout<<"�� "<<i+1<<" �Ӥ���:"<<fruit2[i];
      cout<<",�Ҧ���}:"<<(int *)fruit2[i]<<"\n"; 
    }  
    system("pause");
    return 0; 
} 
