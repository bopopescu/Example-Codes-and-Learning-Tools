//---------------------------------------------------------------------------
// �{���W��: oop_13_5.cpp
// �{���\��: �w�q�@��++�P--�B��l���и��禡

#include <iostream.h>
class Sheepwalk{
     friend ostream & operator<<(ostream &, Sheepwalk&);
public:
Sheepwalk(){SheepCount = 0;}; 
Sheepwalk &Sheepdog(int); 
operator int()const{return SheepCount;};
Sheepwalk& operator ++();
Sheepwalk& operator --();
//Sheepwalk operator ++(int);
int operator ++(int);
Sheepwalk operator --(int);
private:
int SheepCount;
};

Sheepwalk& Sheepwalk::operator++() //++�B��l�и��禡(�e�m��)
{
    ++SheepCount;
return *this;
}

//Sheepwalk Sheepwalk::operator++(int) //++�B��l�и��禡(��m��)
//{
//    Sheepwalk temp=*this;
//++SheepCount;
//return temp;
//}

int Sheepwalk::operator++(int) //++�B��l�и��禡(��m��)
{

return SheepCount++;

}

Sheepwalk& Sheepwalk::operator--() //--�B��l�и��禡(�e�m��)
{ 
    --SheepCount;
return *this;
}

Sheepwalk Sheepwalk::operator--(int) //--�B��l�и��禡(��m��)
{
    Sheepwalk temp=*this;
--SheepCount;
return temp;
}
Sheepwalk& Sheepwalk::Sheepdog(int hunt)
{
    SheepCount += hunt;
    return *this;
}

//<<�B��l�и��禡
ostream & operator<<(ostream &output, Sheepwalk &s)
{
output <<"�o�ئ�" << s.SheepCount << "����\n";
return output;
}


int main(void)
{
   Sheepwalk Chiayi;
   int sheep;

   sheep = Chiayi++;
   cout << "�����" << sheep <<"����\n";
   cout << Chiayi++;
   Chiayi.Sheepdog(15);
   sheep = Chiayi--;
   cout << "�ثe��" << sheep <<"����\n";
   cout << Chiayi;

}


//---------------------------------------------------------------------------
