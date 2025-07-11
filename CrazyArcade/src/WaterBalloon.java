import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import javazoom.jl.player.Player;

public class WaterBalloon {
	int X;
	int Y;
	int bombSize;
	int playertype;
	int waterballoonmax;
	int waterballoonmax_plus;
	
	int up_check;
	LinkedList<Integer> up_checkList = new LinkedList<>();
	int down_check;
	LinkedList<Integer> down_checkList = new LinkedList<>();
	int left_check;
	LinkedList<Integer> left_checkList = new LinkedList<>();
	int right_check;
	LinkedList<Integer> right_checkList = new LinkedList<>();
	
	public int[] mapXlocationlist;
	public int[] mapYlocationlist;
	
	public int balloonXindex;
	public int balloonYindex;
	
	public int balloonplus; /* 물풍선 확장 아이템에서 사용*/
	
	private Image balloonImg;
	private Image centerImg;
	private Image leftImg;
	private Image rightImg;
	private Image upImg;
	private Image downImg;
	
	Queue<Integer> balloonXqueue = new LinkedList<>();
	Queue<Integer> balloonYqueue = new LinkedList<>();
	LinkedList<Integer> balloonXList = new LinkedList<>();
	LinkedList<Integer> balloonYList = new LinkedList<>();
	/* 여러 개의 물풍선을 놓기 위해 링크드 리스트 사용*/
	
	Queue<Integer> boomballoonXqueue = new LinkedList<>();
	Queue<Integer> boomballoonYqueue = new LinkedList<>();
	LinkedList<Integer> boomballoonXList = new LinkedList<>();
	LinkedList<Integer> boomballoonYList = new LinkedList<>();
	
	private Make_Item_Index item;
	
	public WaterBalloon(int playertype){
		this.playertype = playertype;
		this.waterballoonmax = 1; //물풍선 기본 개수 1개
		this.waterballoonmax_plus = 0; //추가 물풍선 개수 0개
		this.mapXlocationlist = new int[13];
		this.mapYlocationlist = new int[13];
		this.item = new Make_Item_Index();
		int locationnum = 0;
		for(int i=0; i<13;i++) {
				this.mapXlocationlist[i] = locationnum;
				this.mapYlocationlist[i] = locationnum;
				locationnum += 60.45;
		}
		
		this.balloonImg = new ImageIcon("Resources/waterbomb.png").getImage();
		this.centerImg = new ImageIcon("Resources/waterbomb_center.png").getImage();
		this.leftImg = new ImageIcon("Resources/waterbomb_width.png").getImage();
		this.rightImg = new ImageIcon("Resources/waterbomb_width2.png").getImage();
		this.upImg = new ImageIcon("Resources/waterbomb_height2.png").getImage();
		this.downImg = new ImageIcon("Resources/waterbomb_height.png").getImage();
	}
	
	public Image getImg() {// 물풍선 이미지를 스크린에 주기위한 함수
		return this.balloonImg;
	}
	
	public Image getcenterImg() {// 터진 물풍선 가운데 이미지를 스크린에 주기위한 함수
		return this.centerImg;
	}
	
	public Image getleftImg() {// 터진 물풍선 왼쪽 이미지를 스크린에 주기위한 함수
		return this.leftImg;
	}
	
	public Image getrightImg() {// 터진 물풍선 오른쪽 이미지를 스크린에 주기위한 함수
		return this.rightImg;
	}
	
	public Image getupImg() {// 터진 물풍선 위 이미지를 스크린에 주기위한 함수
		return this.upImg;
	}
	public Image getdownImg() {// 터진 물풍선 아래 이미지를 스크린에 주기위한 함수
		return this.downImg;
	}
	
	public int getX(int i) { /*물풍선의 x 값을 스크린에 전달하기 위한 함수, 
	i는 인덱스로 스크린에서 링크드 리스트의 어떤 값을 전달할지 지정받음.*/
		return balloonXList.get(i);
	}
	
	public int getY(int i) {
		return balloonYList.get(i);
	}
	
	public int getboomX(int i) { /*터진 물풍선의 x 값을 스크린에 전달하기 위한 함수, 
	i는 인덱스로 스크린에서 링크드 리스트의 어떤 값을 전달할지 지정받음.*/
		return boomballoonXList.get(i);
	}
	
	public int getboomY(int i) {
		return boomballoonYList.get(i);
	}	
	private void bgplay() { // 배경 음악 (프기프 교수님 참조 파일 참고)
		Player jlPlayer = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("Resources/exclusive.mp3");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            jlPlayer = new Player(bufferedInputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        final Player player = jlPlayer;
        new Thread() {
            public void run() {
                try {
                	player.play();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();
	}		
	
	public void makeWaterBalloon(int x, int y, int bombSize, int waterballoonmax_plus) {
		this.bombSize = bombSize;
		if (this.waterballoonmax_plus != waterballoonmax_plus) {
			this.waterballoonmax = 1 + waterballoonmax_plus;
		}
		this.waterballoonmax_plus = waterballoonmax_plus;
		if(playertype == 1 && waterballoonmax==0) {
			System.out.println("player1 물풍선 횟수 초과");
		}else if(playertype == 2 && waterballoonmax==0) {
			System.out.println("player2 물풍선 횟수 초과");
		}else {
			if (playertype == 1) {
				waterballoonmax -= 1;//물풍선이 설치되면 물풍선 최대개수를 1 줄여줌
				this.balloonplus = BoomJudge.character1_stream;
			}else if (playertype == 2) {
				waterballoonmax -= 1;//물풍선이 설치되면 물풍선 최대개수를 1 줄여줌
				this.balloonplus = BoomJudge.character2_stream;
			}
			this.X = x;
			this.Y = y;
			for(int i=0; i<13;i++) {
				if((-(X-mapXlocationlist[i])<40) || ((mapXlocationlist[i]-X)<40)) {
					balloonXindex = i;
				}
			}
			for(int i=0; i<13;i++) {
				if((-(Y-mapYlocationlist[i])<40) || ((mapYlocationlist[i]-Y)<40)) {
					balloonYindex = i;
				}
			}
			balloonXqueue.add(balloonXindex); /*물풍선 x 좌표 인덱스를 저장하는 큐*/
			balloonYqueue.add(balloonYindex); /*물풍선 y 좌표 인덱스를 저장하는 큐*/
			balloonXList.add(balloonXindex); /*물풍선 x 좌표 인덱스를 저장하는 큐*/
			balloonYList.add(balloonYindex); /*물풍선 y 좌표 인덱스를 저장하는 링크드 리스트*/
			BoomJudge.map_size[balloonYindex][balloonXindex] = 3; /*3로 바꾸어 물풍선 놓기*/
			BoomJudge.die();
			/*내부적으로 이용하기 위해 3로 바꾸어줌*/
			TimerTask task = new TimerTask() {
				@Override
			    public void run() {
					int remember_x = balloonXqueue.peek();
					int remember_y = balloonYqueue.peek();
					
			    	balloonXList.remove(0);
			    	balloonYList.remove(0);
					boomballoonXqueue.add(balloonXqueue.poll()); /*물풍선 x 좌표 인덱스를 저장하는 큐*/
					boomballoonYqueue.add(balloonYqueue.poll()); /*물풍선 y 좌표 인덱스를 저장하는 큐*/
					boomballoonXList.add(remember_x); /*물풍선 x 좌표 인덱스를 저장하는 링크드 리스트*/
					boomballoonYList.add(remember_y); /*물풍선 y 좌표 인덱스를 저장하는 링크드 리스트*/
					
					down_check=-1;
					up_check=-1;
					left_check=-1;
					right_check=-1;
					
					for(int plusbombsize = 0; plusbombsize<balloonplus+1; plusbombsize++) {
						if(boomballoonYqueue.peek()+bombSize+plusbombsize<=12) {
							if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 5){
								down_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 6){
								down_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 7) {
								down_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 8) {
								down_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 10) {
								down_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 11) {
								down_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 13) {
								down_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 14) {
								down_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 16) {
								down_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 17) {
								down_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 19) {
								down_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 20) {
								down_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 22) {
								down_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 23) {
								down_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 25) {
								down_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 26) {
								down_check = plusbombsize;
								break;
							}else{
								down_check = plusbombsize;
							}
						}
					}
					down_checkList.add(down_check+1);
					for(int plusbombsize = 0; plusbombsize<balloonplus+1; plusbombsize++) {
						if(boomballoonYqueue.peek()-bombSize-plusbombsize>=0) {
							if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 5){
								up_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 6){
								up_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 7) {
								up_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 8) {
								up_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 10) {
								up_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 11) {
								up_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 13) {
								up_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 14) {
								up_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 16) {
								up_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 17) {
								up_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 19) {
								up_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 20) {
								up_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 22) {
								up_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 23) {
								up_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 25) {
								up_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 26) {
								up_check = plusbombsize;
								break;
							}else{
								up_check = plusbombsize;
							}
						}
					}
					up_checkList.add(up_check+1);
					for(int plusbombsize = 0; plusbombsize<balloonplus+1; plusbombsize++) {
						if(boomballoonXqueue.peek()+bombSize+plusbombsize<=12) {
							if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 5){
								right_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 6){
								right_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 7) {
								right_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 8) {
								right_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 10) {
								right_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 11) {
								right_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 13) {
								right_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 14) {
								right_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 16) {
								right_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 17) {
								right_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 19) {
								right_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 20) {
								right_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 22) {
								right_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 23) {
								right_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 25) {
								right_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 26) {
								right_check = plusbombsize;
								break;
							}else{
								right_check = plusbombsize;
							}
						}
					}
					right_checkList.add(right_check+1);
					for(int plusbombsize = 0; plusbombsize<balloonplus+1; plusbombsize++) {
						if(boomballoonXqueue.peek()-bombSize-plusbombsize>=0) {
							if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 5){
								left_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 6){
								left_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 7) {
								left_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 8) {
								left_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 10) {
								left_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 11) {
								left_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 13) {
								left_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 14) {
								left_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 16) {
								left_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 17) {
								left_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 19) {
								left_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 20) {
								left_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 22) {
								left_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 23) {
								left_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 25) {
								left_check = plusbombsize;
								break;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 26) {
								left_check = plusbombsize;
								break;
							}else{
								left_check = plusbombsize;
							}
						}
					}
					left_checkList.add(left_check+1);
					BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 4; 
					for(int plusbombsize = 0; plusbombsize<right_checkList.get(0); plusbombsize++) {
						if(boomballoonXqueue.peek()+bombSize+plusbombsize<=12) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 4;
						}
					}
					for(int plusbombsize = 0; plusbombsize<left_checkList.get(0); plusbombsize++) {
						if(boomballoonXqueue.peek()-bombSize-plusbombsize>=0) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 4;
						}
					}
					for(int plusbombsize = 0; plusbombsize<down_checkList.get(0); plusbombsize++) {
						if(boomballoonYqueue.peek()+bombSize+plusbombsize<=12) {
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 4;
						}
					}
					for(int plusbombsize = 0; plusbombsize<up_checkList.get(0); plusbombsize++) {
						if(boomballoonYqueue.peek()-bombSize-plusbombsize>=0) {
							BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 4;
						}
					}
					bgplay();
					waterballoonmax +=1;
			    }
			};
			
            TimerTask boomover = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//7번인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, up_checkList, down_checkList, left_checkList, right_checkList, 7, 9);
					//8번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, up_checkList, down_checkList, left_checkList, right_checkList, 8, 9);
					//10번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, up_checkList, down_checkList, left_checkList, right_checkList, 10, 12);
					//11번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, up_checkList, down_checkList, left_checkList, right_checkList, 11, 12);
					//13번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, up_checkList, down_checkList, left_checkList, right_checkList, 13, 15);
					//14번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, up_checkList, down_checkList, left_checkList, right_checkList, 14, 15);
					//16번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, up_checkList, down_checkList, left_checkList, right_checkList, 16, 18);
					//17번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, up_checkList, down_checkList, left_checkList, right_checkList, 17, 18);
					//19번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, up_checkList, down_checkList, left_checkList, right_checkList, 19, 21);
					//20번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, up_checkList, down_checkList, left_checkList, right_checkList, 20, 21);
					//22번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, up_checkList, down_checkList, left_checkList, right_checkList, 22, 24);
					//23번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, up_checkList, down_checkList, left_checkList, right_checkList, 23, 24);
					//25번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, up_checkList, down_checkList, left_checkList, right_checkList, 25, 27);
					//26번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, up_checkList, down_checkList, left_checkList, right_checkList, 26, 27);
					
					//기존 인덱스
					BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 0;
					BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 0;
					for(int plusbombsize = 0; plusbombsize<down_checkList.get(0); plusbombsize++) {
						if(boomballoonYqueue.peek()+bombSize+plusbombsize<=12) {
							if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 9){
								BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 9;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 9;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 12) {
								BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 12;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 12;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 15) {
								BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 15;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 15;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 18) {
								BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 18;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 18;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 21) {
								BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 21;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 21;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 24) {
								BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 24;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 24;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 27) {
								BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 27;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 27;
							}else{
								BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 0;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 0;
							}
						}
					}
					for(int plusbombsize = 0; plusbombsize<up_checkList.get(0); plusbombsize++) {
						if(boomballoonYqueue.peek()-bombSize-plusbombsize>=0) {
							if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 9) {
								BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 9;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 9;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 12) {
								BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 12;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 12;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 15) {
								BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 15;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 15;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 18) {
								BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 18;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 18;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 21) {
								BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 21;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 21;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 24) {
								BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 24;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 24;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 27) {
								BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 27;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 27;
							}else{
								BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 0;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 0;
							}
						}
					}
					for(int plusbombsize = 0; plusbombsize<right_checkList.get(0); plusbombsize++) {
						if(boomballoonXqueue.peek()+bombSize+plusbombsize<=12) {
							if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 9) {
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 9;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 9;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 12) {
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 12;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 12;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 15) {
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 15;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 15;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 18) {
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 18;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 18;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 21) {
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 21;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 21;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 24) {
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 24;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 24;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 27) {
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 27;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 27;
							}else{
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 0;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 0;
							}
						}
					}
					for(int plusbombsize = 0; plusbombsize<left_checkList.get(0); plusbombsize++) {
						if(boomballoonXqueue.peek()-bombSize-plusbombsize>=0) {
							if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 9) {
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 9;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 9;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 12) {
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 12;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 12;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 15) {
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 15;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 15;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 18) {
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 18;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 18;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 21) {
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 21;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 21;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 24) {
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 24;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 24;
							}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 27) {
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 27;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 27;
							}else{
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 0;
								BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 0;
							}
						}
					}
				
					boomballoonXqueue.remove();
					boomballoonYqueue.remove();
					boomballoonXList.remove(0);
					boomballoonYList.remove(0);
					up_checkList.remove(0);
					down_checkList.remove(0);
					left_checkList.remove(0);
					right_checkList.remove(0);
				}
			};

			Timer boom = new Timer();
			boom.schedule(task, 5000);
			boom.schedule(boomover, 6000);
		}
	}
	
	public LinkedList<Integer> get_up_checkList() {
		return up_checkList;
	}
	public LinkedList<Integer> get_down_checkList() {
		return down_checkList;
	}
	public LinkedList<Integer> get_left_checkList() {
		return left_checkList;
	}
	public LinkedList<Integer> get_right_checkList() {
		return right_checkList;
	}
}