#include<stdio.h>
#include<stdlib.h>
#include<string.h>
enum {MAX_LEN=256,MAX_LINE=1000};

int main(void)
{
   char *p[MAX_LINE];
   char buf[MAX_LEN+1];
   int i,j;
   
   i=0;
   while(i<MAX_LINE&&(fgets(buf,MAX_LEN+1,stdin)!=NULL))//fgets(buf,n,stdin)Ū���r�����n(�@���r�ꪽ�촫�氷enter)�s��buf�̱qstdin(��L) 
   {
      p[i]=(char*)malloc(strlen(buf)+1);//�[�J"\0" 
      if(p[i]!=NULL)
      {
         strcpy(p[i],buf);//�Np[i]�s�ibuf��
         i++;           
      } 
   } 
   
   for(j=0;j<i;j++)
   {
      printf("%s",p[j]);
      free(p[j]);             
   } 
   system("pause");
   return 0; 
} 

 
