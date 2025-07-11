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
	private int state;//상태 번호
	private int state_move;//이동 상태 번호
	private int move;

	public Uni(Screen screen, int playertype) { /*플레이어 타입을 전달받아, 해당 타입에 따라 키에 대한 동작이 다르도록 함*/
		super(screen);
		// TODO Auto-generated constructor stub
		if(playertype == 1) {
			if (Screen.map_selection == 0) {//쿠키맵일때
				this.X = 0;//초기 X값
				this.Y = 0;//초기 Y값
			}else if (Screen.map_selection == 1) {//해적맵일때
				this.X = 60;//초기 X값
				this.Y = 60;//초기 Y값
			}else if (Screen.map_selection == 2) {//빌리지맵일때
				this.X = 0;//초기 X값
				this.Y = 0;//초기 Y값
			}
		}else if(playertype == 2) {
			if (Screen.map_selection == 0) {
				this.X = 720;//초기 X값
				this.Y = 720;//초기 Y값
			}else if (Screen.map_selection == 1) {
				this.X = 660;//초기 X값
				this.Y = 660;//초기 Y값
			}else if (Screen.map_selection == 2) {
				this.X = 720;//초기 X값
				this.Y = 720;//초기 Y값
			}
		}
		this.step = 5;//초기 이동 거리
		this.step_plus = 0;//이동 속도 증가율
		this.bombSize = 1;//물줄기 크기 1
		this.playertype = playertype;
		playerWaterBalloon = new WaterBalloon(playertype); /* 물풍선 생성*/
		this.Uni_state = new Image[4][3];
		Image Uni_front = new ImageIcon("Resources/uni_front.png").getImage();//다오 정면 이미지
		Image Uni_front1 = new ImageIcon("Resources/uni_front1.png").getImage();
		Image Uni_front2 = new ImageIcon("Resources/uni_front2.png").getImage();
		Image Uni_back = new ImageIcon("Resources/uni_back.png").getImage();//다오 후면 이미지
		Image Uni_back1 = new ImageIcon("Resources/uni_back1.png").getImage();
		Image Uni_back2 = new ImageIcon("Resources/uni_back2.png").getImage();
		Image Uni_left = new ImageIcon("Resources/uni_left.png").getImage();//다오 좌측면 이미지
		Image Uni_left1 = new ImageIcon("Resources/uni_left1.png").getImage();
		Image Uni_left2 = new ImageIcon("Resources/uni_left2.png").getImage();
		Image Uni_right = new ImageIcon("Resources/uni_right.png").getImage();//다오 우측면 이미지
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
		this.state = 0;//초기 정면으로 보고있음
		this.state_move = 0;
		this.move = 0;
	}
	
	public Image getImg() {//이미지를 스크린에 주기위한 함수
		return this.Uni_state[state][state_move];
	}
	
	public Image getballoonImg() {//이미지를 스크린에 주기위한 함수
		return playerWaterBalloon.getImg();
	}
	
	public Image getcenterImg() {// 터진 물풍선 가운데 이미지를 스크린에 주기위한 함수
		return playerWaterBalloon.getcenterImg();
	}
	
	public Image getleftImg() {// 터진 물풍선 왼쪽 이미지를 스크린에 주기위한 함수
		return playerWaterBalloon.getleftImg();
	}
	
	public Image getrightImg() {// 터진 물풍선 오른쪽 이미지를 스크린에 주기위한 함수
		return playerWaterBalloon.getrightImg();
	}
	
	public Image getupImg() {// 터진 물풍선 위 이미지를 스크린에 주기위한 함수
		return playerWaterBalloon.getupImg();
	}
	public Image getdownImg() {// 터진 물풍선 아래 이미지를 스크린에 주기위한 함수
		return playerWaterBalloon.getdownImg();
	}
	
	public int getballoonX(int i) {//물풍선 X값을 스크린에 주기위한 함수
		return playerWaterBalloon.getX(i);
	}
	
	public int getballoonY(int i) {//물풍선 Y값을 스크린에 주기위한 함수
		return playerWaterBalloon.getY(i);
	}
	public int getboomballoonX(int i) {//터진 물풍선 X값을 스크린에 주기위한 함수
		return playerWaterBalloon.getboomX(i);
	}
	
	public int getboomballoonY(int i) {//터진 물풍선 Y값을 스크린에 주기위한 함수
		return playerWaterBalloon.getboomY(i);
	}
	
	public int getX() {//X값을 스크린에 주기위한 함수
		return this.X;
	}
	
	public int getY() {//Y값을 스크린에 주기위한 함수
		return this.Y;
	}
	
	public void getPlayerIndex_x(int x) {//스크린에서 x인덱스값 가져오기
		this.playerIndex_x = x;
	}
	
	public void getPlayerIndex_y(int y) {//스크린에서 y인덱스값 가져오기
		this.playerIndex_y = y;
	}
	
	public int getballonListsize() {
		return playerWaterBalloon.balloonXqueue.size();
		/*물풍선 객체의 링크드 리스트의 크기를 스크린에 전달하여, 반복문의 반복 휫수를 지정하기 위한 함수*/
	}
	public int getboomballonListsize() {
		return playerWaterBalloon.boomballoonXqueue.size();
		/*터진 물풍선 객체의 링크드 리스트의 크기를 스크린에 전달하여, 반복문의 반복 휫수를 지정하기 위한 함수*/
	}
	
	public LinkedList<Integer> getbombSize(int i) {//물풍선 크기값을 스크린에 주기위한 함수
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
	
	public void up(int step) {//위로 가기
		this.state = 1;
		if (move == 7) {
			move = 0;
		}
		if((move%6 == 0)||(move%6 == 1)||(move%6 == 2)) {
			this.state_move = 1;
		}else {
			this.state_move = 2;
		}
		if (playerIndex_y == 0) {//인덱스 0일경우 예외처리
			Y-=step;
			move++;
		}
		else if((BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 0) || (BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 1) ||
				(BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 2) || (BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 9) || 
				(BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 12) || (BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 15) ||
				(BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 18) || (BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 21) ||
				(BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 24) || (BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 27)){
			//다음 이동위치 인덱스 0,1,2,9,12일 경우에만 이동가능
			Y-=step;
			move++;
		}
		else if((playerIndex_y)*60.45<this.getY()) {//그래도 캐릭터가 벽옆의 빈칸으로 안넘어가져서 벽을 넘지 않을 정도까지만 이동
			Y-=step;
			move++;
		}
	}
	public void down(int step) {//아래로 가기
		this.state  = 0;
		if (move == 7) {
			move = 0;
		}
		if((move%6 == 0)||(move%6 == 1)||(move%6 == 2)) {
			this.state_move = 1;
		}else {
			this.state_move = 2;
		}
		if (playerIndex_y == 12) {//인덱스 12일경우 예외처리
			Y+=step;
			move++;
		}
		else if((BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 0) || (BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 1) || 
				(BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 2) || (BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 9) || 
				(BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 12) || (BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 15) ||
				(BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 18) || (BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 21) ||
				(BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 24) || (BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 27)) {
			//다음 이동위치의 인덱스 0,1,2,9,12일 경우에만 이동가능
			Y+=step;
			move++;
		}
		else if((playerIndex_y)*60.45>this.getY()) {//그래도 캐릭터가 벽옆의 빈칸으로 안넘어가져서 벽을 넘지 않을 정도까지만 이동
			Y+=step;
			move++;
		}
	}
	public void left(int step) {//왼쪽으로 가기
		this.state  = 2;
		if (move == 7) {
			move = 0;
		}
		if((move%6 == 0)||(move%6 == 1)||(move%6 == 2)) {
			this.state_move = 1;
		}else {
			this.state_move = 2;
		}
		if (playerIndex_x == 0) {//인덱스 0일경우 예외처리
			X-=step;
			move++;
		}
		else if((BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 0) || (BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 1) || 
				(BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 2) || (BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 9) || 
				(BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 12) || (BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 15) ||
				(BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 18) || (BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 21) ||
				(BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 24) || (BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 27)) {
			//다음 이동위치의 인덱스 0,1,2,9,12일 경우에만 이동가능
			X-=step;
			move++;
		}
		else if((playerIndex_x)*60.45<this.getX()) {//그래도 캐릭터가 벽옆의 빈칸으로 안넘어가져서 벽을 넘지 않을 정도까지만 이동
			X-=step;
			move++;
		}
	}
	public void right(int step) {//오른쪽으로 가기
		this.state  = 3;
		if (move == 7) {
			move = 0;
		}
		if((move%6 == 0)||(move%6 == 1)||(move%6 == 2)) {
			this.state_move = 1;
		}else {
			this.state_move = 2;
		}
		if (playerIndex_x == 12) {//인덱스 12일경우 예외처리
			X+=step;
			move++;
		}
		else if((BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 0) || (BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 1) || 
				(BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 2) || (BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 9) || 
				(BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 12) || (BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 15) ||
				(BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 18) || (BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 21) ||
				(BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 24) || (BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 27)) {
			//다음 이동위치의 인덱스 0,1,2,9,12일 경우에만 이동가능
			X+=step;
			move++;
		}
		else if((playerIndex_x)*60.45>this.getX()) {//그래도 캐릭터가 벽옆의 빈칸으로 안넘어가져서 벽을 넘지 않을 정도까지만 이동
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
			   playerWaterBalloon.makeWaterBalloon(this.getX(), this.getY(), this.bombSize, BoomJudge.character1_bombsizeup);//물풍선 놓기
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
			playerWaterBalloon.makeWaterBalloon(this.getX(), this.getY(), this.bombSize, BoomJudge.character2_bombsizeup);//물풍선 놓기
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