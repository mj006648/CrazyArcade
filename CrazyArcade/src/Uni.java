import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Uni extends Character implements KeyListener{

	private int X;
	private int Y;
	private int playerIndex_x;
	private int playerIndex_y;
	private int step;
	private int step_plus;
	public int bombSize;
	public int streamSize;
	public int playertype;
	WaterBalloon playerWaterBalloon;
	private Image[][] Uni_state;
	private int state;//���� ��ȣ
	private int state_move;//�̵� ���� ��ȣ
	private int move;

	public Uni(Screen screen, int playertype) { /*�÷��̾� Ÿ���� ���޹޾�, �ش� Ÿ�Կ� ���� Ű�� ���� ������ �ٸ����� ��*/
		super(screen);
		// TODO Auto-generated constructor stub
		if(playertype == 1) {
			if (Screen.map_selection == 0) {//��Ű���϶�
				this.X = 0;//�ʱ� X��
				this.Y = 0;//�ʱ� Y��
			}else if (Screen.map_selection == 1) {//�������϶�
				this.X = 60;//�ʱ� X��
				this.Y = 60;//�ʱ� Y��
			}else if (Screen.map_selection == 2) {//���������϶�
				this.X = 0;//�ʱ� X��
				this.Y = 0;//�ʱ� Y��
			}
		}else if(playertype == 2) {
			if (Screen.map_selection == 0) {
				this.X = 720;//�ʱ� X��
				this.Y = 720;//�ʱ� Y��
			}else if (Screen.map_selection == 1) {
				this.X = 660;//�ʱ� X��
				this.Y = 660;//�ʱ� Y��
			}else if (Screen.map_selection == 2) {
				this.X = 720;//�ʱ� X��
				this.Y = 720;//�ʱ� Y��
			}
		}
		this.step = 5;//�ʱ� �̵� �Ÿ�
		this.step_plus = 0;//�̵� �ӵ� ������
		this.bombSize = 1;//���ٱ� ũ�� 1
		this.playertype = playertype;
		playerWaterBalloon = new WaterBalloon(playertype); /* ��ǳ�� ����*/
		this.Uni_state = new Image[4][3];
		Image Uni_front = new ImageIcon("Resources/uni_front.png").getImage();//�ٿ� ���� �̹���
		Image Uni_front1 = new ImageIcon("Resources/uni_front1.png").getImage();
		Image Uni_front2 = new ImageIcon("Resources/uni_front2.png").getImage();
		Image Uni_back = new ImageIcon("Resources/uni_back.png").getImage();//�ٿ� �ĸ� �̹���
		Image Uni_back1 = new ImageIcon("Resources/uni_back1.png").getImage();
		Image Uni_back2 = new ImageIcon("Resources/uni_back2.png").getImage();
		Image Uni_left = new ImageIcon("Resources/uni_left.png").getImage();//�ٿ� ������ �̹���
		Image Uni_left1 = new ImageIcon("Resources/uni_left1.png").getImage();
		Image Uni_left2 = new ImageIcon("Resources/uni_left2.png").getImage();
		Image Uni_right = new ImageIcon("Resources/uni_right.png").getImage();//�ٿ� ������ �̹���
		Image Uni_right1 = new ImageIcon("Resources/uni_right1.png").getImage();
		Image Uni_right2 = new ImageIcon("Resources/uni_right2.png").getImage();
		this.Uni_state[0][0] = Uni_front;
		this.Uni_state[0][1] = Uni_front1;
		this.Uni_state[0][2] = Uni_front2;
		this.Uni_state[1][0] = Uni_back;
		this.Uni_state[1][1] = Uni_back1;
		this.Uni_state[1][2] = Uni_back2;
		this.Uni_state[2][0] = Uni_left;
		this.Uni_state[2][1] = Uni_left1;
		this.Uni_state[2][2] = Uni_left2;
		this.Uni_state[3][0] = Uni_right;
		this.Uni_state[3][1] = Uni_right1;
		this.Uni_state[3][2] = Uni_right2;
		this.state = 0;//�ʱ� �������� ��������
		this.state_move = 0;
		this.move = 0;
	}
	
	public Image getImg() {//�̹����� ��ũ���� �ֱ����� �Լ�
		return this.Uni_state[state][state_move];
	}
	
	public Image getballoonImg() {//�̹����� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getImg();
	}
	
	public Image getcenterImg() {// ���� ��ǳ�� ��� �̹����� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getcenterImg();
	}
	
	public Image getleftImg() {// ���� ��ǳ�� ���� �̹����� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getleftImg();
	}
	
	public Image getrightImg() {// ���� ��ǳ�� ������ �̹����� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getrightImg();
	}
	
	public Image getupImg() {// ���� ��ǳ�� �� �̹����� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getupImg();
	}
	public Image getdownImg() {// ���� ��ǳ�� �Ʒ� �̹����� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getdownImg();
	}
	
	public int getballoonX(int i) {//��ǳ�� X���� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getX(i);
	}
	
	public int getballoonY(int i) {//��ǳ�� Y���� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getY(i);
	}
	public int getboomballoonX(int i) {//���� ��ǳ�� X���� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getboomX(i);
	}
	
	public int getboomballoonY(int i) {//���� ��ǳ�� Y���� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getboomY(i);
	}
	
	public int getX() {//X���� ��ũ���� �ֱ����� �Լ�
		return this.X;
	}
	
	public int getY() {//Y���� ��ũ���� �ֱ����� �Լ�
		return this.Y;
	}
	
	public void getPlayerIndex_x(int x) {//��ũ������ x�ε����� ��������
		this.playerIndex_x = x;
	}
	
	public void getPlayerIndex_y(int y) {//��ũ������ y�ε����� ��������
		this.playerIndex_y = y;
	}
	
	public int getballonListsize() {
		return playerWaterBalloon.balloonXqueue.size();
		/*��ǳ�� ��ü�� ��ũ�� ����Ʈ�� ũ�⸦ ��ũ���� �����Ͽ�, �ݺ����� �ݺ� �ܼ��� �����ϱ� ���� �Լ�*/
	}
	public int getboomballonListsize() {
		return playerWaterBalloon.boomballoonXqueue.size();
		/*���� ��ǳ�� ��ü�� ��ũ�� ����Ʈ�� ũ�⸦ ��ũ���� �����Ͽ�, �ݺ����� �ݺ� �ܼ��� �����ϱ� ���� �Լ�*/
	}
	
	public LinkedList<Integer> getbombSize(int i) {//��ǳ�� ũ�Ⱚ�� ��ũ���� �ֱ����� �Լ�
		if(i==0) {
			return playerWaterBalloon.get_up_checkList();
		}else if(i==1) {
			return playerWaterBalloon.get_down_checkList();
		}else if(i==2) {
			return playerWaterBalloon.get_left_checkList();
		}else{
			return playerWaterBalloon.get_right_checkList();
		}
	}
	
	public void up(int step) {//���� ����
		this.state = 1;
		if (move == 7) {
			move = 0;
		}
		if((move%6 == 0)||(move%6 == 1)||(move%6 == 2)) {
			this.state_move = 1;
		}else {
			this.state_move = 2;
		}
		if (playerIndex_y == 0) {//�ε��� 0�ϰ�� ����ó��
			Y-=step;
			move++;
		}
		else if((BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 0) || (BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 1) ||
				(BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 2) || (BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 9) || 
				(BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 12) || (BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 15) ||
				(BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 18) || (BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 21) ||
				(BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 24) || (BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 27)){
			//���� �̵���ġ �ε��� 0,1,2,9,12�� ��쿡�� �̵�����
			Y-=step;
			move++;
		}
		else if((playerIndex_y)*60.45<this.getY()) {//�׷��� ĳ���Ͱ� ������ ��ĭ���� �ȳѾ���� ���� ���� ���� ���������� �̵�
			Y-=step;
			move++;
		}
	}
	public void down(int step) {//�Ʒ��� ����
		this.state  = 0;
		if (move == 7) {
			move = 0;
		}
		if((move%6 == 0)||(move%6 == 1)||(move%6 == 2)) {
			this.state_move = 1;
		}else {
			this.state_move = 2;
		}
		if (playerIndex_y == 12) {//�ε��� 12�ϰ�� ����ó��
			Y+=step;
			move++;
		}
		else if((BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 0) || (BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 1) || 
				(BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 2) || (BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 9) || 
				(BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 12) || (BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 15) ||
				(BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 18) || (BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 21) ||
				(BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 24) || (BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 27)) {
			//���� �̵���ġ�� �ε��� 0,1,2,9,12�� ��쿡�� �̵�����
			Y+=step;
			move++;
		}
		else if((playerIndex_y)*60.45>this.getY()) {//�׷��� ĳ���Ͱ� ������ ��ĭ���� �ȳѾ���� ���� ���� ���� ���������� �̵�
			Y+=step;
			move++;
		}
	}
	public void left(int step) {//�������� ����
		this.state  = 2;
		if (move == 7) {
			move = 0;
		}
		if((move%6 == 0)||(move%6 == 1)||(move%6 == 2)) {
			this.state_move = 1;
		}else {
			this.state_move = 2;
		}
		if (playerIndex_x == 0) {//�ε��� 0�ϰ�� ����ó��
			X-=step;
			move++;
		}
		else if((BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 0) || (BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 1) || 
				(BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 2) || (BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 9) || 
				(BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 12) || (BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 15) ||
				(BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 18) || (BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 21) ||
				(BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 24) || (BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 27)) {
			//���� �̵���ġ�� �ε��� 0,1,2,9,12�� ��쿡�� �̵�����
			X-=step;
			move++;
		}
		else if((playerIndex_x)*60.45<this.getX()) {//�׷��� ĳ���Ͱ� ������ ��ĭ���� �ȳѾ���� ���� ���� ���� ���������� �̵�
			X-=step;
			move++;
		}
	}
	public void right(int step) {//���������� ����
		this.state  = 3;
		if (move == 7) {
			move = 0;
		}
		if((move%6 == 0)||(move%6 == 1)||(move%6 == 2)) {
			this.state_move = 1;
		}else {
			this.state_move = 2;
		}
		if (playerIndex_x == 12) {//�ε��� 12�ϰ�� ����ó��
			X+=step;
			move++;
		}
		else if((BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 0) || (BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 1) || 
				(BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 2) || (BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 9) || 
				(BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 12) || (BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 15) ||
				(BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 18) || (BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 21) ||
				(BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 24) || (BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 27)) {
			//���� �̵���ġ�� �ε��� 0,1,2,9,12�� ��쿡�� �̵�����
			X+=step;
			move++;
		}
		else if((playerIndex_x)*60.45>this.getX()) {//�׷��� ĳ���Ͱ� ������ ��ĭ���� �ȳѾ���� ���� ���� ���� ���������� �̵�
			X+=step;
			move++;
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(playertype == 1) {
			this.streamSize = BoomJudge.character1_stream;
			if(BoomJudge.character1_speedup != step_plus) {
				this.step = step + BoomJudge.character1_speedup - this.step_plus;
				this.step_plus = BoomJudge.character1_speedup;
			}
			if(Screen.c1.isUp()) {
				if(this.getY()>=0) {
					this.up(this.step);
				}
			}
	        if(Screen.c1.isDown()) {
	        	if(this.getY()<=700) {
					this.down(this.step);
				}
	        }
	        if(Screen.c1.isLeft()) {
	        	if(this.getX()>=0) {
					this.left(this.step);
				}
	        }
	        if(Screen.c1.isRight()) {
	        	if(this.getX()<=720) {
					this.right(this.step);
				}
	        }
		    if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			   playerWaterBalloon.makeWaterBalloon(this.getX(), this.getY(), this.bombSize, BoomJudge.character1_bombsizeup);//��ǳ�� ����
		    }
		}else if(playertype == 2) {
			this.streamSize = BoomJudge.character2_stream;
			if(BoomJudge.character2_speedup != step_plus) {
				this.step = step + BoomJudge.character2_speedup - this.step_plus;
				this.step_plus = BoomJudge.character2_speedup;
			}
			 if(Screen.c2.isUp()) {
				 if(this.getY()>=0) {
						this.up(this.step);
					}
			 }
		     if(Screen.c2.isDown()) {
		        	if(this.getY()<=700) {
						this.down(this.step);
					}
		        }
		     if(Screen.c2.isLeft()) {
		        	if(this.getX()>=0) {
						this.left(this.step);
					}
		        }
		     if(Screen.c2.isRight()) {
						if(this.getX()<=720) {
							this.right(this.step);
						}
		        }
		    if(e.getKeyCode()==KeyEvent.VK_SHIFT) {	
			playerWaterBalloon.makeWaterBalloon(this.getX(), this.getY(), this.bombSize, BoomJudge.character2_bombsizeup);//��ǳ�� ����
		    }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		this.state_move = 0;
	}

	@Override
	public void getmovestop() {
		// TODO Auto-generated method stub
		this.state_move = 0;
	}

}