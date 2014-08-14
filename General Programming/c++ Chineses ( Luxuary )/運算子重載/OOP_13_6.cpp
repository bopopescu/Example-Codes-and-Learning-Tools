//---------------------------------------------------------------------------
// �{���W��: oop_13_6.cpp
// �{���\��: �w�q�@��Vector���O�A�Ψ��x�s��ƦV�q
 
#include <iostream.h>

typedef int TempType;

class Vector{
  //friend ostream & operator<<(ostream &, Vector &);
  public:
        Vector(int initialSize=5);
        ~ Vector(){delete []elements;};
        TempType & operator[](int offset);
        int size(){return NumOfElements;};
  private:
        int NumOfElements;  //�����Ӽ�
        TempType *elements; //�Ĥ@�Ӥ����s�񪺦�}
};

//�غc��
Vector:: Vector (int initialSize)
{
   elements = new TempType[initialSize];;
   NumOfElements = initialSize; 
}

TempType & Vector::operator[](int offset)
{
   if(offset >= NumOfElements)
	{
      TempType *Extended = new TempType[offset + 1];
      for(int i=0; i< NumOfElements; i++) 
Extended[i] = elements[i];
    delete []elements;
    elements = Extended;
    NumOfElements = offset + 1;
   }
   return elements[offset];
}


int main(void){
  Vector V;

  for(int i=0; i<V.size(); i++)
    V[i] = i+1;

  for(int i=0; i<V.size(); i++)
    cout << V[i] << "\t";
  cout << endl;

  V[8]=100;
  for(int i=0; i<V.size(); i++)
    cout << V[i] << "\t";

}



//---------------------------------------------------------------------------
