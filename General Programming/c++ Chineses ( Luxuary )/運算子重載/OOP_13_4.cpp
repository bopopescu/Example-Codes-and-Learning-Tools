//---------------------------------------------------------------------------
// �{���W��: oop_13_4.cpp
// �{���\��: �w�q�@��!�B��l�и��禡

#include <iostream.h>
class Sheepwalk{
     friend ostream & operator<<(ostream &, Sheepwalk&);
public:
Sheepwalk(){SheepCount = 0;}; 
Sheepwalk &Sheepdog(int); 
int operator!();
private:
int SheepCount;
};

int Sheepwalk::operator!()
{ 
    return !SheepCount;
}

Sheepwalk& Sheepwalk::Sheepdog(int hunt)
{
    SheepCount += hunt;
    return *this;
}

ostream & operator<<(ostream &output, Sheepwalk &s)
{
output <<"�o�ئ�" << s.SheepCount << "����\n";
return output;
}


int main(void)
{
   Sheepwalk Chiayi;

   cout << Chiayi;
   if (!Chiayi)  //�p�G���u�A���Chiayi���󤤦ϰ��ƶq���s
Chiayi.Sheepdog(15);
else
Chiayi.Sheepdog(5);
   cout << Chiayi;
   
}

//---------------------------------------------------------------------------
