//---------------------------------------------------------------------------
// �{���W��: oop_13_1.cpp
// �{���\��: �w�q�@�����O���٦�禡

#include <iostream.h>

class Sheepwalk{
  friend void Sheepdog(Sheepwalk &, int);
public:
Sheepwalk(){SheepCount = 0};
void printCount(){cout <<"�@��" <<�@SheepCount << "����\n"};
private:
int SheepCount;
};

void Sheepdog(Sheepwalk &s, int hunt)
{
    s.SheepCount += hunt;
}

int main(void)
{
   Sheepwalk Chiayi;

   Chiayi.print();
   Sheepdog(Chiayi,10);
   Chiayi.print();
   Sheepdog(Chiayi,15);
   Chiayi.print();
}

//---------------------------------------------------------------------------
 