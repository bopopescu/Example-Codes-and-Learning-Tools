//---------------------------------------------------------------------------
// �{���W��: oop_13_3.cpp
// �{���\��: �w�q<<�B��l�и��禡

#include <iostream.h>
#include <stdlib.h>

class Date{
friend istream & operator>>(istream &, Date &);
friend ostream & operator<<(ostream &,const Date &);
public:
Date(int=1900, int=1, int=1); //�w�]�޼ƫغc��
void setDate(int, int, int);
private:
int year;
int month;
int day;
};

istream & operator>>(istream &input, Date &d)
{
    //�b�o�رN��ƿ�J��Date���O
char tmp[11];
input.getline(tmp, 11);   //��J�~(4���)
tmp[4]=tmp[7]=tmp[10]='\0';
d.year=atoi(tmp);
d.month=atoi(tmp+5);
d.day=atoi(tmp+8);
return input;  
}

ostream & operator<<(ostream &output, const Date &d)
{
    //�b�o�رN��ƿ�X��ostream��y
output << d.year << "/" << d.month << "/" << d.day;
return output;  
}

//<<�B��l�и��禡
void Date::setDate(int y, int m, int d)
{    
	year = y;     
	month = m;
	day = d;
}

//Date�غc���A�ΨӪ�l�Ƹ�Ʀ��������e
Date::Date(int y, int m, int d)
{
  year=y; month=m; day=d; //�i�H�����s��private��Ʀ���
}



//---------------------------------------------------------------------------
 
