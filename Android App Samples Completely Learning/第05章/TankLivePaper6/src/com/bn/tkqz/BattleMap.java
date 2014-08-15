package com.bn.tkqz;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class BattleMap
{
	
	//�Ҧ����d���a��
	private final byte[][][] MAP_SP=
	{
			
			{//crazy tank ���d�N�X�G  ���30�A �C�ơG23
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,5,5,0,0,5,0,0,5,5,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,5,5,0,0,5,0,0,5,5,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,5,5,5,5,5,5,5,5,5,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0},
				{0,0,0,0,0,5,5,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0},
				{0,0,0,0,5,5,0,0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0},
				{0,0,0,5,5,0,0,2,2,0,0,0,0,0,2,2,0,0,5,5,0,0,0},
				{0,0,3,0,0,0,0,2,2,0,0,0,0,0,2,2,0,0,0,0,3,0,0},
				{0,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,0},
				{0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0},
				{0,0,0,5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,0,0,0},
				{0,0,0,0,5,5,0,0,0,2,2,2,2,2,0,0,0,5,5,0,0,0,0},
				{0,0,0,0,0,5,5,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0},
				{0,0,0,0,0,0,5,0,3,0,3,0,3,0,3,0,5,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,3,0,3,0,3,0,3,0,3,0,0,0,0,0,0,0},
				{0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0},
				{0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0},
				{0,0,1,0,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0},
				{0,0,1,0,0,1,0,0,1,0,1,1,0,0,0,1,1,0,0,0,0,0,0},
				{0,0,1,0,1,0,0,0,1,0,1,0,1,0,0,1,1,0,0,0,0,0,0},
				{0,0,1,1,0,0,0,0,1,0,1,0,0,1,0,1,1,0,0,0,0,0,0},
				{0,0,1,0,1,0,0,0,1,0,1,0,0,0,1,1,1,0,1,1,1,0,0},
				{0,0,1,0,0,1,0,0,1,0,1,0,0,0,0,1,1,0,0,1,1,0,0},
				{0,0,1,0,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,0,0},
				{0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0}
			}
	};
	//�Ҧ����d���a��
	private final byte[][][] MAP_HP=
	{
		{//crazy tank ���d�N�X  �]��ơG23�A �C�ơG30�^
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,1,1,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,0,1,1,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,1,1,5,5,1,1,0,0,0,0,1,1,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,2,2,5,5,5,5,5,5,2,2,0,0,1,1,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,1,1,5,5,5,5,1,1,5,5,5,5,1,1,2,2,1,0,0,0,0,0},
			{0,0,0,0,0,0,2,2,5,5,5,5,1,1,1,1,1,1,5,5,5,5,2,2,1,0,0,0,0,0},
			{0,0,0,0,2,2,5,5,5,5,1,1,1,1,1,1,1,1,1,1,5,5,5,5,2,2,0,0,0,0},
			{0,0,1,1,5,5,5,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,5,5,5,1,1,0,0},
			{0,0,5,5,5,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,5,5,5,0,0},
			{2,2,5,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,5,2,2},
			{5,5,5,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,5,5,5},
			{5,5,5,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,5,5,5},
			{0,0,5,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,5,0,0},
			{0,0,5,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,5,0,0},
			{0,0,5,5,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,5,5,0,0},
			{0,0,5,5,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,5,5,0,0},
			{0,0,5,5,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0,1,1,1,1,1,1,5,5,0,0},
			{0,0,5,5,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0,1,1,1,1,1,1,5,5,0,0},
			{0,0,5,5,1,1,1,1,0,0,0,0,1,1,0,0,1,1,0,0,0,0,1,1,1,1,5,5,0,0},
			{0,0,5,5,1,1,1,1,0,0,0,0,1,1,0,0,1,1,0,0,0,0,1,1,1,1,5,5,0,0}
		}
	};
	//��e���d��
	private int missionNum=1;
	//��e���d���a��
	private byte[][] mapData; //�`�N�A�a�ϼƲլ�byte����
	private Bitmap brickBitmap=AliveWallPaperTank.brickBitmap;
	private Bitmap stoneBitmap=AliveWallPaperTank.stoneBitmap;
	private Bitmap seaBitmap=AliveWallPaperTank.seaBitmap;
	private Bitmap iceBitmap=AliveWallPaperTank.iceBitmap;
	private Bitmap grassBitmap=AliveWallPaperTank.grassBitmap;
	private Bitmap homeBitmap=AliveWallPaperTank.homeBitmap;
	private Barrier []barrier=
	{
			new Rode(),//0�N���
			new Brick(brickBitmap),//1�N��j��
			new Stone(stoneBitmap),//2�N�����
			new Sea(seaBitmap),//3�N����v
			new Ice(iceBitmap),//4�N��B
			new Grass(grassBitmap),//5�N���a�A�e�b�W�h
	};
	//�ЫئѺ۹ﹳ
	int xHome;//�Ѻ۪�x,y����
	int yHome;
	Home home;
	//���y��
	Reward reward=null;
	void intiMapData()
	{
		if(AliveWallPaperTank.isShuPing())
		{
			mapData=copyMapData(MAP_SP[missionNum-1]);
		}
		else
		{
			mapData=copyMapData(MAP_HP[missionNum-1]);
		}
		//�ЫئѺ۹ﹳ
		xHome=(mapData[0].length/2-1)*Constant.BARRIER_SIZE+Constant.GAME_VIEW_X;//�Ѻ۪�x,y����
		yHome=(mapData.length-2)*Constant.BARRIER_SIZE+Constant.GAME_VIEW_Y;	
		home=new Home(homeBitmap,xHome,yHome);
		
	}
	//�����G���a�ϼƲժ���k
	byte[][] copyMapData(byte[][] mapData)
	{
		byte[][] result=null;
		result=new byte[mapData.length][mapData[0].length];
		for(int i=0;i<mapData.length;i++)
		{
			for(int j=0;j<mapData[0].length;j++)
			{
				result[i][j]=mapData[i][j];
			}
		}
		return result;
	}
	void drawSelfBelow(Canvas canvas,Paint paint)//�n�D�e�b�U�h��
	{
		for(int i=0;i<mapData.length;i++)//�e��ê��
		{
			for(int j=0;j<mapData[i].length;j++)
			{
				if(mapData[i][j]!=Barrier.GRASS)//�e�b�U�h���N��
				{
					barrier[mapData[i][j]].drawSelf(canvas, paint, j*Constant.BARRIER_SIZE+Constant.GAME_VIEW_X, i*Constant.BARRIER_SIZE+Constant.GAME_VIEW_Y);
				}
			}
		}
		home.drawSelf(canvas, paint);//�e�Ѻ�
	}
	void drawSelfAbove(Canvas canvas,Paint paint)//�n�D�e�b�W�h��
	{
		for(int i=0;i<mapData.length;i++)
		{
			for(int j=0;j<mapData[i].length;j++)
			{
				if(mapData[i][j]==Barrier.GRASS)//�e�b�W�h���N��
				{
					barrier[mapData[i][j]].drawSelf(canvas, paint, j*Constant.BARRIER_SIZE+Constant.GAME_VIEW_X, i*Constant.BARRIER_SIZE+Constant.GAME_VIEW_Y);
				}
			}
		}
		if(reward!=null)
		{
			reward.drawSelf(canvas, paint);
		}
	}
	//�˴��Z�J�]�]�A�^���Z�J�^�O�_�J���ê������k
	boolean isTankMetWithBarrier(int xTemp,int yTemp)
	{
		for(int i=0;i<mapData.length;i++)
		{
			for(int j=0;j<mapData[i].length;j++)
			{
				if(mapData[i][j]==Barrier.BRICK||mapData[i][j]==Barrier.STONE||mapData[i][j]==Barrier.SEA)
				{//�p�G�J���ê���A
					if
					  (
						  Constant.oneIsInAnother//�o�̪��ץ��ȫD�`���n�I�I�I
						  (
								  xTemp+Constant.TANK_SIZE_REVISE-1, yTemp+Constant.TANK_SIZE_REVISE-1, Constant.TANK_SIZE-2*Constant.TANK_SIZE_REVISE-3, Constant.TANK_SIZE-2*Constant.TANK_SIZE_REVISE-3, 
								  j*Constant.BARRIER_SIZE+Constant.GAME_VIEW_X, i*Constant.BARRIER_SIZE+Constant.GAME_VIEW_Y, Constant.BARRIER_SIZE, Constant.BARRIER_SIZE
						  )
					  )
					  {
						  return true;
					  }
				}
			}
		}
		return false;
	}
	//�^���Z�J�O�_�b�B�W����
	boolean isHeroMetWithIce(Hero hero)//�u���^���Z�J�~�b�B�W���ơA�Ĥ�Z�J������
	{
		for(int i=0;i<mapData.length;i++)
		{
			for(int j=0;j<mapData[i].length;j++)
			{
				if(mapData[i][j]==Barrier.ICE)
				{//�p�G�J���ê���A
					if
					  (
						  Constant.oneIsInAnother
						  (
								  hero.x, hero.y, Constant.TANK_SIZE, Constant.TANK_SIZE, 
								  j*Constant.BARRIER_SIZE+Constant.GAME_VIEW_X, i*Constant.BARRIER_SIZE+Constant.GAME_VIEW_Y, Constant.BARRIER_SIZE, Constant.BARRIER_SIZE
						  )
					  )
					  {
						  return true;
					  }
				}
			}
		}
		return false;
	}
	//�l�u�O�_������ê��
	boolean isBulletMetWithBarrier(int xTemp,int yTemp)
	{
		for(int i=0;i<mapData.length;i++)
		{
			for(int j=0;j<mapData[i].length;j++)
			{
				if(mapData[i][j]==Barrier.BRICK||mapData[i][j]==Barrier.STONE)
				{//�p�G�J���ê���A
					if
					  (
						  Constant.oneIsInAnother
						  (
								  xTemp, yTemp, Constant.BULLET_SIZE, Constant.BULLET_SIZE, 
								  j*Constant.BARRIER_SIZE+Constant.GAME_VIEW_X, i*Constant.BARRIER_SIZE+Constant.GAME_VIEW_Y, Constant.BARRIER_SIZE, Constant.BARRIER_SIZE
						  )
					  )
					  {
							if(mapData[i][j]==Barrier.BRICK)//�N�j���H
							{
								mapData[i][j]=Barrier.RODE;
							}
							return true;
					  }
				}
			}
		}
		return false;
	}
	//�[�j�l�u�O�_������ê��
	boolean isStrongBulletMetWithBarrier(int xTemp,int yTemp)
	{
		for(int i=0;i<mapData.length;i++)
		{
			for(int j=0;j<mapData[i].length;j++)
			{
				if(mapData[i][j]==Barrier.BRICK||mapData[i][j]==Barrier.STONE)
				{//�p�G�J���ê���A
					if
					  (
						  Constant.oneIsInAnother
						  (
								  xTemp, yTemp, Constant.BULLET_SIZE, Constant.BULLET_SIZE, 
								  j*Constant.BARRIER_SIZE+Constant.GAME_VIEW_X, i*Constant.BARRIER_SIZE+Constant.GAME_VIEW_Y, Constant.BARRIER_SIZE, Constant.BARRIER_SIZE
						  )
					  )
					  {
							if(mapData[i][j]==Barrier.BRICK)//�N�j���H
							{
								mapData[i][j]=Barrier.RODE;
							}
							if(mapData[i][j]==Barrier.STONE)//�N�j���H
							{
								mapData[i][j]=Barrier.RODE;
							}
							return true;
					  }
				}
			}
		}
		return false;
	}
	//�l�u�O�_�����Ѻ�
	boolean isBulletMetWithHome(int x,int y)
	{
		if
		(
				Constant.oneIsInAnother
				(
						x, y, Constant.BULLET_SIZE, Constant.BULLET_SIZE, 
						home.x,home.y,Constant.HOME_SIZE,Constant.HOME_SIZE
				)	
		)
		{
			return true;
		}
		
		return false;
	}
	//�^���Z�J�O�_�Y����y��
	boolean isHeroMetWithReward(Hero hero)
	{
		if
		(
				reward!=null&&
				Constant.oneIsInAnother
				(
						hero.x, hero.y, Constant.TANK_SIZE, Constant.TANK_SIZE,
						reward.x,reward.y,Constant.REWARD_SIZE,Constant.REWARD_SIZE
				)	
		)
		{
			return true;
		}
		return false;		
	}
	//�Υ���N�Ѻ۫O�@�_�Ӫ���k
	void buildHomeWithStone()
	{
		int row=mapData.length;
		int col=mapData[0].length;
		//��l�ƿj��A�O�@�Ѻ�
		mapData[row-1][col/2-2]=Barrier.STONE;//��
		mapData[row-1][col/2-3]=Barrier.STONE;
		mapData[row-2][col/2-2]=Barrier.STONE;
		mapData[row-2][col/2-3]=Barrier.STONE;
		
		mapData[row-1][col/2+1]=Barrier.STONE;//�k
		mapData[row-1][col/2+2]=Barrier.STONE;
		mapData[row-2][col/2+1]=Barrier.STONE;
		mapData[row-2][col/2+2]=Barrier.STONE;
		
		mapData[row-3][col/2-2]=Barrier.STONE;//���W
		mapData[row-3][col/2-3]=Barrier.STONE;
		mapData[row-4][col/2-2]=Barrier.STONE;
		mapData[row-4][col/2-3]=Barrier.STONE;
		
		mapData[row-3][col/2+1]=Barrier.STONE;//�k�W
		mapData[row-3][col/2+2]=Barrier.STONE;
		mapData[row-4][col/2+1]=Barrier.STONE;
		mapData[row-4][col/2+2]=Barrier.STONE;
		
		mapData[row-3][col/2-1]=Barrier.STONE;//�W
		mapData[row-3][col/2]=Barrier.STONE;
		mapData[row-4][col/2-1]=Barrier.STONE;
		mapData[row-4][col/2]=Barrier.STONE;
	}
	//�Υ���N�Ѻ۫O�@�_�Ӫ���k
	void buildHomeWithBrick()
	{
		int row=mapData.length;
		int col=mapData[0].length;
		//��l�ƿj��A�O�@�Ѻ�
		mapData[row-1][col/2-2]=Barrier.BRICK;//��
		mapData[row-1][col/2-3]=Barrier.BRICK;
		mapData[row-2][col/2-2]=Barrier.BRICK;
		mapData[row-2][col/2-3]=Barrier.BRICK;
		
		mapData[row-1][col/2+1]=Barrier.BRICK;//�k
		mapData[row-1][col/2+2]=Barrier.BRICK;
		mapData[row-2][col/2+1]=Barrier.BRICK;
		mapData[row-2][col/2+2]=Barrier.BRICK;
		
		mapData[row-3][col/2-2]=Barrier.BRICK;//���W
		mapData[row-3][col/2-3]=Barrier.BRICK;
		mapData[row-4][col/2-2]=Barrier.BRICK;
		mapData[row-4][col/2-3]=Barrier.BRICK;
		
		mapData[row-3][col/2+1]=Barrier.BRICK;//�k�W
		mapData[row-3][col/2+2]=Barrier.BRICK;
		mapData[row-4][col/2+1]=Barrier.BRICK;
		mapData[row-4][col/2+2]=Barrier.BRICK;
		
		mapData[row-3][col/2-1]=Barrier.BRICK;//�W
		mapData[row-3][col/2]=Barrier.BRICK;
		mapData[row-4][col/2-1]=Barrier.BRICK;
		mapData[row-4][col/2]=Barrier.BRICK;
	}
	//�i�J�U�@������k
	public void goToNextMission()
	{
		if(missionNum>=this.MAP_SP.length)
		{
			//�q�����N�X
			System.out.println("congartulations! All missions are completed!!!");
			return;
		}
		missionNum++;
		mapData=copyMapData(this.MAP_SP[missionNum-1]);
		//�p�ƾ��M�s
		AliveWallPaperTank.countTankDestoryed=0;
		//�^���Z�J�^���Ӫ���m
		AliveWallPaperTank.hero.backHome();
		//�M�����y��
		AliveWallPaperTank.map.reward=null;
	}
	public int getMissionNum()
	{
		return missionNum;
	}
	public void setMissionNum(int missionNum)
	{
		this.missionNum=missionNum;
	}

}

