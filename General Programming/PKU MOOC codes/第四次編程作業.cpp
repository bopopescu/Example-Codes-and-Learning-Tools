#include <stdio.h>
#include <stdlib.h>
#include <string.h> 
#include <stdio.h>
#include <string>
#include <stdlib.h>
#include <iostream>
using namespace std;

#define LEN 550 

int InputBigNum(char n[])
{
  char tmp[LEN];  int i,m; 
  for (i=0;i<LEN;i++)  {  n[i]=0;  }
  if(scanf("%s",tmp)<1)  {  return -1;  }
  m=strlen(tmp);
  for(i=0;i<m;i++)  {  n[i]=tmp[m-i-1]-'0';  }
}

void OutputBigNum(char n[])
{
  int i,p,f=0;
  for(p=LEN-1;p>=0;p--)
  {  if(n[p]!=0)  {  f=1;  break;  }  }
  if(f)
  {  for(p;p>=0;p--)  printf("%c",n[p]+'0');  }
  else  {  printf("0");  }
}     
 
void AddBigNum(char a[], char b[], char c[] ) 
{
  int i,tmp=0; 
  for(i=0;i<LEN;i++)
  {
    c[i]=a[i]+b[i]+tmp;
    if(c[i]>=10)  {  tmp=1; c[i]=c[i]%10;  }
    else{ tmp=0;  } 
  }
} 


int SubBigNum(char a[], char b[], char c[])
{
  int i,tmp=0;
  for(i=LEN-1;i>=0;i--)
  {
    if(a[i]>b[i])  break;
    if(a[i]<b[i])  {  return 0; }  
  } 

  for(i=0;i<LEN;i++)
  {
    c[i]=a[i]-b[i]-tmp+10;
    if(c[i]<10)  {  tmp=1;  }
    else  {  tmp=0;  c[i]=c[i]-10;  }
  }
  return 1; 
} 

void MulBigNum(char a[], char b[], char c[])
{
  int i,j,tmp=0,m=0,d[LEN];
  for(i=0;i<LEN;i++)  {  d[i]=0;  } 
  
  for(i=LEN-1;i>=0;i--)  {if (a[i]!=0)  break;  } 
  m+=i;
  for(i=LEN-1;i>=0;i--)  {  if (b[i]!=0)  break;  } 
  m+=i; 
  m++;
   
  for(i=0;i<=m;i++)
  {
    for(j=i;j>=0;j--)
    {  d[i]+=a[j]*b[i-j];  }
    d[i]+=tmp;
    if(d[i]>=10)  {  tmp=d[i]/10;  d[i]=d[i]%10;  }
    else tmp=0;  
  } 
  for(i=0;i<LEN;i++)  {  c[i]=d[i];  } 
}

int DivBigNum(char a[], char b[], char c[])
{
  int i,j,k,p,m,n; 
  char d[LEN],e[LEN];  
  for(i=LEN-1;i>=0;i--)  {  d[i]=b[i];  e[i]=0;  } 
  for(i=LEN-1;i>=0;i--)  {  if(a[i]!=0)  break;  }
  m=i+1;  
  p=0; 
  for(i=LEN-1;i>=0;i--)  
  {
    if(b[i]!=0)
    {  p=1;  break;  }
  } 
  if(!p)  return 0; 
  n=i+1;
  if(m>n)  
  {
    for(i=n-1;i>=0;i--)  {  d[i+m-n]=d[i];  }  
    for(i=m-n-1;i>=0;i--)  {  d[i]=0;  }
  }
  for(i=m-n;i>=0;i--)
  {
    while(1)
    {
      p=SubBigNum(a,d,a); 
      if(!p)  break;
      else{  e[i]++;  }
    } 
    for(j=0;j<LEN-1;j++)  {  d[j]=d[j+1];  }
    
  }  

  for(i=LEN-1;i>=0;i--)  {  c[i]=e[i];  }  
  return 1;
}  

int main()
{
  while(1){
  char a[LEN],b[LEN],c[LEN],n[LEN];
  char command;
  int i,j,m;
  InputBigNum(a);
  cin>>command;
  InputBigNum(b);
  
  switch(command){
   case '+':
         AddBigNum(a,b,c); 
         OutputBigNum(c);
         printf("\n\n");
         break;
   case '-':
        for(i=LEN-1;i>=0;i--){
           m=a[i]-b[i];
           if(m<0)break;
        }
         if(m<0)
         {
         SubBigNum(b,a,c);
         printf("-"); 
         OutputBigNum(c); 
         printf("\n\n");            
         }
         else{
         SubBigNum(a,b,c); 
         OutputBigNum(c);
         printf("\n\n");
         }
         break;
   case '*':
         MulBigNum(a,b,c); 
         OutputBigNum(c);
         printf("\n\n");
         break;
    case '/':
         DivBigNum(a,b,c); 
         OutputBigNum(c);
         printf("\n\n");
         break;
    default:
         cout<<"��J���~"<<endl;
    }
  }
  system("pause");
  return 0;
}

/*#include <stdio.h>
#include <stdlib.h>
#include <string.h> 
�j�ƥ|���B��A�Hchar�}�C�s�Ʀr ��string.h 

#define LEN 550 �]�w�B�z�̤j���

INPUT:�N�Ʀr�s�J�Y�}�C
 
��J�G�s��ؼа}�C�W ��X�G0�N���\ -1�N����
1.�M�ťؼа}�C=0  
2.������J��J�Ȧs�}�C�A���ѫh�Ǧ^-1 
3.�p��Ʀr���(strlen) 
4.�q�Ӧ�}�l�̧ǩ�J�ؼа}�C�A���e�q�r���ন�Ʀr
  �Y�Ӧ쬰1 ,n[0]��1(���O1��ASCII) 

int InputBigNum(char n[])
{
  char tmp[LEN];  int i,m; 
  for (i=0;i<LEN;i++)  {  n[i]=0;  }
  if(scanf("%s",tmp)<1)  {  return -1;  }
  m=strlen(tmp);
  for(i=0;i<m;i++)  {  n[i]=tmp[m-i-1]-'0';  }
}
 
OutputBigNum:�ɦL�X�Ʀr

��J�G�n�Q�L�X�Ӫ��ư}�C�W �Lreturn 
1.���D0���Ʀr�}�Y������p ���f=1 @�n�O�o�{�������O0�䤣��}�Y�hf=0
2.�˧��ন�r���L�X�A�Ӧ�̫�ɦL�A�L�������� 
  @�n�O�o�{�������O0�|�L�X�@��0


void OutputBigNum(char n[])
{
  int i,p,f=0;
  for(p=LEN-1;p>=0;p--)
  {  if(n[p]!=0)  {  f=1;  break;  }  }
  if(f)
  {  for(p;p>=0;p--)  printf("%c",n[p]+'0');  }
  else  {  printf("0");  }
}     
 
AddBigNum: ��Ƭۥ[

��J�G�Q�[��a�A�[��b�A���G�s��}�Cc �Lreturn
1.�q�Ӧ�}�l�@�ӭӬۥ[(a+b+tmp)�A�p���i��s��btmp

void AddBigNum(char a[], char b[], char c[] ) 
{
  int i,tmp=0; 
  for(i=0;i<LEN;i++)
  {
    c[i]=a[i]+b[i]+tmp;
    if(c[i]>=10)  {  tmp=1; c[i]=c[i]%10;  }
    else{ tmp=0;  } 
  }
} 

SubBigNum ��Ƭ۴�

��J�G�Q���a�A���b,���G�s��}�Cc ��return(���\�۴�:1,a<b:0) 
IF a<b�hc����; 
0;�ˬda�O�_>=b�A�Y�_�h�Ǧ^-1���� 
1.�q�Ӧ�}�l�@�ӭӬ۴�A���G�[�W10�w���t��(a-b-tmp)
 �Y��X<10(�쬰�t��)
 �htmp=1�Χ�w���t�ƪ�10�q���G�̴  


int SubBigNum(char a[], char b[], char c[])
{
  int i,tmp=0;
  for(i=LEN-1;i>=0;i--)
  {
    if(a[i]>b[i])  break;
    if(a[i]<b[i])  {  return 0; }  
  } 

  for(i=0;i<LEN;i++)
  {
    c[i]=a[i]-b[i]-tmp+10;
    if(c[i]<10)  {  tmp=1;  }
    else  {  tmp=0;  c[i]=c[i]-10;  }
  }
  return 1; 
} 

MulBigNum ��Ƭۭ�

��J�G�Q����a�A����b,���G�s��}�Cc �Lreturn 
0.�M�żȦs���G���}�Cd (��d���ت��O�קKc�Ma.b�ۦP�ɥX��) 
1.m=a,b��̪���ƩM+1�A�@�����G�i�઺�̤j�s��   
2.�n����n���=a[n]b[0]+a[n-1]b[1]+.....a[0]b[n]+�W�@�Ӧ�ƪ��i��tmp �̦���[0]�p��C��d 
3.��d���Ƽg�Jc 


void MulBigNum(char a[], char b[], char c[])
{
  int i,j,tmp=0,m=0,d[LEN];
  for(i=0;i<LEN;i++)  {  d[i]=0;  } 
  
  for(i=LEN-1;i>=0;i--)  {if (a[i]!=0)  break;  } 
  m+=i;
  for(i=LEN-1;i>=0;i--)  {  if (b[i]!=0)  break;  } 
  m+=i; 
  m++;
   
  for(i=0;i<=m;i++)
  {
    for(j=i;j>=0;j--)
    {  d[i]+=a[j]*b[i-j];  }
    d[i]+=tmp;
    if(d[i]>=10)  {  tmp=d[i]/10;  d[i]=d[i]%10;  }
    else tmp=0;  
  } 
  for(i=0;i<LEN;i++)  {  c[i]=d[i];  } 
}

 

DivBigNum ��Ƭ۰�

*����ƻݭnSubBigNum&AddBigNum���
��J�G�Q����a�A����b,���G�s��}�Cc ��return(���\�۰�:1,b==0:0)
b=0��c���� 
�����k��z �Ө��ƵL����˥h 
1.���oa[]��b[]����Ƽƥ�m,n
2.�N����b���W10��(m-n)����A�]�N�O�e��(m-n)��
3.��i=0�}�l�Na�Mb*i����A��X����b*i��A��B*(i+1)��i
4.�Ӫ�m-n+1��N�Oi,a=a-b*i,b=b/10
5.����m-n��


int DivBigNum(char a[], char b[], char c[])
{
  int i,j,k,p,m,n; 
  char d[LEN],e[LEN];  
  for(i=LEN-1;i>=0;i--)  {  d[i]=b[i];  e[i]=0;  } 
  for(i=LEN-1;i>=0;i--)  {  if(a[i]!=0)  break;  }
  m=i+1;  
  p=0; 
  for(i=LEN-1;i>=0;i--)  
  {
    if(b[i]!=0)
    {  p=1;  break;  }
  } 
  if(!p)  return 0; 
  n=i+1;
  if(m>n)  
  {
    for(i=n-1;i>=0;i--)  {  d[i+m-n]=d[i];  }  
    for(i=m-n-1;i>=0;i--)  {  d[i]=0;  }
  }
  for(i=m-n;i>=0;i--)
  {
    while(1)
    {
      p=SubBigNum(a,d,a); 
      if(!p)  break;
      else{  e[i]++;  }
    } 
    for(j=0;j<LEN-1;j++)  {  d[j]=d[j+1];  }
    
  }  

  for(i=LEN-1;i>=0;i--)  {  c[i]=e[i];  }  
  return 1;
}  


 
int main()
{
  char a[LEN],b[LEN],c[LEN];
  int p;
  while(1)
  {
    InputBigNum(a);
    InputBigNum(b);
    p=DivBigNum(a,b,c); 
    OutputBigNum(c);
    printf("\n\n");
  }
}*/ 
