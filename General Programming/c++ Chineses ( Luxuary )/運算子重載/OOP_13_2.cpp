//---------------------------------------------------------------------------
// �{���W��: oop_13_2.cpp
// �{���\��: �Q��this���Цs����Ʀ���

#include <iostream.h>

class Sheepwalk{
public: 
Sheepwalk(){SheepCount = 0;};
Sheepwalk &Sheepdog(int);
void printCount();
private:
int SheepCount;
};

void Sheepwalk::printCount()
{ 
cout <<"�@��" << this->SheepCount << "����\n";
}

Sheepwalk& Sheepwalk::Sheepdog(int hunt)
{
    (*this).SheepCount += hunt;
    return *this;
}

int main(void)
{
   Sheepwalk Chiayi;

   Chiayi.printCount();
   Chiayi.Sheepdog(10).Sheepdog(15); //�y��Q���ϫᰨ�W�S�y��Q������
   Chiayi.printCount();
   
}

//---------------------------------------------------------------------------
 