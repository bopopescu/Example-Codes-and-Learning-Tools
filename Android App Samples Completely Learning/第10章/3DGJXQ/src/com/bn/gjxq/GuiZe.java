package com.bn.gjxq;
/**
 * �����O��ڶH�Ѫ��W�h���A��L���q�L�ե�canMove��k���X�_�l��m�P������m
 */
enum Finish {NO_FINISH,BLACK_WIN,WHITE_WIN}
public class GuiZe
{	
	public static boolean canMove(ChessForControl chessforcontrol,ChessForControl[][] currBoard,int toZ,int toX)//��,�C
	{
		int row=chessforcontrol.row;//��e��H����
		int col=chessforcontrol.col;//��e��H���C
		if(//�p�G�Ӧ�m�O�ۤv�誺�A���򪽱����i�U
			    currBoard[toZ][toX]!=null
				&&currBoard[toZ][toX].chessType>=(chessforcontrol.chessType/6)*6
				&&currBoard[toZ][toX].chessType<((chessforcontrol.chessType/6)+1)*6
		   )
			{
				return false;
			}
		switch(chessforcontrol.chessType)//�ھڹ�H�������i�����P�_
		{
		case 0://�¨�
		case 6://�ը�
			if((chessforcontrol.row==toZ&&!containHor(chessforcontrol,currBoard,toZ,toX))||
					(chessforcontrol.col==toX&&!containVer(chessforcontrol,currBoard,toZ,toX))
			  )
			{
				return true;
			}
			break;
		case 1:
		case 7://��e����
		
			if(Math.abs(chessforcontrol.row-toZ)+Math.abs(chessforcontrol.col-toX)==3)
			{
				if(chessforcontrol.row==toZ||chessforcontrol.col==toX)
				{
					return false;
				}
				return true;
			}
			break;
		
		case 2:
		case 8://��e���H
			if(!containSlant(chessforcontrol,currBoard,toZ,toX))
			{
				return true;
			}
			break;
		case 3:
		case 9://��e����
			if((chessforcontrol.row==toZ&&!containHor(chessforcontrol,currBoard,toZ,toX))||
					(chessforcontrol.col==toX&&!containVer(chessforcontrol,currBoard,toZ,toX))||
					(!containSlant(chessforcontrol,currBoard,toZ,toX))
			  )
			{
				return true;
			}
			break;
		case 4:
		case 10://��e����
			if(Math.abs(toZ-row)<2&&Math.abs(toX-col)<2)
			{
				return true;
			}
           break;	
		case 5://�§L		
			if(row+1<8&&currBoard[row+1][col]==null)//���ʤ@��A
	  		{
	  			if(toZ==row+1&&toX==col)//�ؼЮ�l�N�O�o�Ӯ�l
	  			{
	  				chessforcontrol.isMoved=true;
	  				return true;
	  			}
	  			else if(row+2<8&&!chessforcontrol.isMoved//�L�S�����ʹL�A�~�ਫ���
	  					&&currBoard[row+2][col]==null//���ʨ�檺���Ӯ�l��������
	  					&&toZ==row+2&&toX==col)//�ؼЦ�m������m
	  		  			{
	  				        chessforcontrol.isMoved=true;
	  		  				return true;
	  		  			}
	  		}
			if(row+1<8&&col+1<8&&currBoard[row+1][col+1]!=null//�o�ӬO���F�ר��Y��誺�A�ҥH����֩w���ର��
					&&currBoard[row+1][col+1].chessType>=((chessforcontrol.chessType/6+1)%2)*6
					&&currBoard[row+1][col+1].chessType<(((chessforcontrol.chessType/6)+1)%2+1)*6)//����n�O���Ѥl�A
			{
				if(toZ==row+1&&toX==col+1)//�ؼЮ�l�N�O�o�Ӯ�l
	  			{
					chessforcontrol.isMoved=true;
	  				return true;
	  			}
			}
			if(row+1<8&&col-1>=0&&currBoard[row+1][col-1]!=null//�o�ӬO���F�ר��Y��誺�A�ҥH����֩w���ର��
					&&currBoard[row+1][col-1].chessType>=((chessforcontrol.chessType/6)+1)%2*6
					&&currBoard[row+1][col-1].chessType<(((chessforcontrol.chessType/6)+1)%2+1)*6)//����n�O���Ѥl�A
			{
				if(toZ==row+1&&toX==col-1)//�ؼЮ�l�N�O�o�Ӯ�l
	  			{
					chessforcontrol.isMoved=true;
	  				return true;
	  			}
			}
			break;
		case 11://��e���էL		
			if(row-1>0&&currBoard[row-1][col]==null)//���ʤ@��A
	  		{
	  			if(toZ==row-1&&toX==col)//�ؼЮ�l�N�O�o�Ӯ�l
	  			{
	  				chessforcontrol.isMoved=true;
	  				return true;
	  			}
	  			else if(row-2>0&&!chessforcontrol.isMoved//�L�S�����ʹL�A�~�ਫ���
	  					&&currBoard[row-2][col]==null//���ʨ�檺���Ӯ�l��������
	  					&&toZ==row-2&&toX==col)//�ؼЦ�m������m
	  		  			{
	  						chessforcontrol.isMoved=true;
	  		  				return true;
	  		  			}
	  		}
			if(row-1>0&&col+1<8&&currBoard[row-1][col+1]!=null//�o�ӬO���F�k�ר��Y��誺�A�ҥH����֩w���ର��
					&&currBoard[row-1][col+1].chessType>=((chessforcontrol.chessType/6)+1)%2*6
					&&currBoard[row-1][col+1].chessType<(((chessforcontrol.chessType/6)+1)%2+1)*6)//����n�O���Ѥl�A
			{
				if(toZ==row-1&&toX==col+1)//�ؼЮ�l�N�O�o�Ӯ�l
	  			{
					chessforcontrol.isMoved=true;
	  				return true;
	  			}
			}
			if(row-1>0&&col-1>=0&&currBoard[row+1][col-1]!=null//�o�ӬO���F���ר��Y��誺�A�ҥH����֩w���ର��
					&&currBoard[row-1][col-1].chessType>=(((chessforcontrol.chessType/6)+1)%2)*6
					&&currBoard[row-1][col-1].chessType<(((chessforcontrol.chessType/6)+1)%2+1)*6)//����n�O���Ѥl�A
			{
				if(toZ==row-1&&toX==col-1)//�ؼЮ�l�N�O�o�Ӯ�l
	  			{
					chessforcontrol.isMoved=true;
	  				return true;
	  			}
			}			
			break;
		}
		return false;
    }
	public static Finish isFinish(ChessForControl[][] currBoard)//�P�_�Y�a�O�_Ĺ�F
	{
		boolean black=false;
		boolean white=false;
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if(currBoard[i][j]!=null)
				{
					if(currBoard[i][j].chessType==4)//�p�G��e�O�¤�
					{
						black=true;
						
					}
					else if(currBoard[i][j].chessType==10)//�p�G��e�O�դ�
					{
						white=true;
					}
				}
			}
		}		
		if(!black)//�p�G�O�¤��F
		{
			return Finish.WHITE_WIN;//��^�դ�Ĺ
		}
		else if(!white)//�p�G�O�դ��F
		{
			return Finish.BLACK_WIN;//��^�¤�Ĺ
		}
		return Finish.NO_FINISH;//�_�h��^�S����Ĺ
	}

//�������I�����t���Ѥl
public static boolean containHor(ChessForControl chessforcontrol,ChessForControl[][] currBoard,int row,int col)
{
	if(col>chessforcontrol.col)//�p�G�ؼ��I�b��e�ﹳ�k��
	{
		for(int i=chessforcontrol.col+1;i<col;i++)
		{
			if(currBoard[row][i]!=null)//��e�I������
			{
				return true;//�t���l
			}
		}
	}
	else//�p�G�ؼ��I�b��e�ﹳ����
	{
		for(int i=col+1;i<chessforcontrol.col;i++)
		{
			if(currBoard[row][i]!=null)//��e�I������
			{
				return true;//�t���l
			}
		}
	}
	return false;
 }
//������V�W�٦��Ѥl
public static boolean containVer(ChessForControl chessforcontrol,ChessForControl[][] currBoard,int row,int col)
{
	if(chessforcontrol.row<row)//�ؼ��I�b��e�l�U��
	{
		for(int i=chessforcontrol.row+1;i<row;i++)
		{
			if(currBoard[i][col]!=null)
			{
				return true;
			}
		}
	}
	else//�ؼ��I�b��e�l�W��
	{
		for(int i=row+1;i<chessforcontrol.row;i++)
		{
			if(currBoard[i][col]!=null)
			{
				return true;
			}
		}
	}
	
	return false;
}
//�P�_�פ�V�W�O�_�t���Ѥl
public static boolean containSlant(ChessForControl chessforcontrol,ChessForControl[][] currBoard,int row,int col)
{
	if(chessforcontrol.row-row+chessforcontrol.col-col==0)//�J
	{
		if(chessforcontrol.col>col)//�p�G�ؼ��I��m�b��e��m����
		{
			for(int i=col+1;i<chessforcontrol.col;i++)
			{
				if(currBoard[row+col-i][i]!=null)
				{
					return true;
				}
			}
		}
		if(chessforcontrol.col<col)//�p�G�ؼ��I�b��e��m�k��
		{
			for(int i=chessforcontrol.col+1;i<col;i++)
			{
				if(currBoard[col+row-i][i]!=null)
				{
					return true;
				}
			}
		}
	}
	else if(chessforcontrol.col-chessforcontrol.row==col-row)//�p�G�b�׽u�W-----��
		{
			if(col<chessforcontrol.col)//�p�G�ؼ��I�b��e��m����
			{
				for(int i=col+1;i<chessforcontrol.col;i++)
				{
					if(currBoard[row-col+i][i]!=null)
					{
						return true;
					}
				}
			}
			if(col>chessforcontrol.col)//�p�G�ؼ��I�b��e�I�k��
			{
				for(int i=chessforcontrol.col+1;i<col;i++)
				{
					if(currBoard[row-col+i][i]!=null)//
					{
						return true;
					}
				}
			}
			
		}
	return false;
}
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
