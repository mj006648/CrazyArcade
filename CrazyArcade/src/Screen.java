import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Screen extends Canvas implements KeyListener, ComponentListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image bufferedImage;
	private Graphics bufferGraphics;
	private Dimension dim;
	
	public static int map_selection;//어떤 맵이 골라졌는지
	
	static VirtualKeyState c1 = new VirtualKeyState(); /*동시이동적용*/
    static VirtualKeyState c2 = new VirtualKeyState();
	
	public int[] mapXlocationlist;
	public int[] mapYlocationlist;
	public int[][] mapindexlist;
	
	private final int MAX_PLAYER = 2;//MAX 플레이어
	Character[] players; //플레이어 관리를 위한 배열

	
	int[] playerIndex_x; /*characterin에서 사용*/
	int[] playerIndex_y;
	
	int[] previous_Index_x; // 이전 인덱스 저장
	int[] previous_Index_y;
	
	//맵 관련 이미지
	private Image map_CookieBackground = new ImageIcon("Resources/mapCookie.png").getImage();//쿠키(맵0) 이미지
	private Image map_CookieBox1 = new ImageIcon("Resources/boxcookie1.png").getImage();
	private Image map_CookieBox2 = new ImageIcon("Resources/boxcookie2.png").getImage();
	private Image map_PatriotsBackground = new ImageIcon("Resources/mapPatriots.png").getImage();//해적(맵1) 이미지
	private Image map_PatriotsBox2 = new ImageIcon("Resources/box2.png").getImage();
	private Image map_PatriotsBox3 = new ImageIcon("Resources/box3.png").getImage();
	private Image map_VillageBackground = new ImageIcon("Resources/mapvillage.png").getImage();
	private Image map_VillageBox2 = new ImageIcon("Resources/boxtree.png").getImage();
	private Image map_VillageBox3 = new ImageIcon("Resources/boxvillage2.png").getImage();
	//아이템 관련 이미지
	private Image item_waterbomb = new ImageIcon("Resources/item_waterbombplus.png").getImage();
	private Image item_speed = new ImageIcon("Resources/item_Speed.png").getImage();
	private Image item_stream = new ImageIcon("Resources/item_waterstream.png").getImage();
	private Image item_purpledevil = new ImageIcon("Resources/item_Purpledevil.png").getImage();
	private Image item_reddevil = new ImageIcon("Resources/item_Reddevil.png").getImage();
	private Image item_super = new ImageIcon("Resources/item_super.png").getImage();
	private Image item_hackstream = new ImageIcon("Resources/item_hackstream.png").getImage();
	
	//물풍선 링크 이미지
	private Image boombmiddleup = new ImageIcon("Resources/waterbomb_link1.png").getImage();
	private Image boombmiddledown = new ImageIcon("Resources/waterbomb_link2.png").getImage();
	private Image boombmiddleleft = new ImageIcon("Resources/waterbomb_link3.png").getImage();
	private Image boombmiddleright = new ImageIcon("Resources/waterbomb_link4.png").getImage();
	public Screen(int map) {
		this.map_selection = map; //생성자를 통해 어떤 맵 설정되었는지 받아오기 위함
		BoomJudge judge = new BoomJudge(map); //맵 배치 받아오기 위해서 사용
		players = new Character[MAX_PLAYER];
		playerIndex_x = new int[MAX_PLAYER];
		playerIndex_y = new int[MAX_PLAYER];
		previous_Index_x = new int[MAX_PLAYER];
		previous_Index_y = new int[MAX_PLAYER];
		if (ReadyFrame.p1chnumber==2) {
			Character player1 = new Dizini(this,1); //플레이어1에 디지니 생성
			players[0] = player1;
		}
		else if (ReadyFrame.p1chnumber==6) {
			Character player1 = new Bazzi(this,1); //플레이어1에 배찌 생성
			players[0] = player1;
		}
		else if (ReadyFrame.p1chnumber==7) {
			Character player1 = new Uni(this,1); //플레이어1에 우니 생성
			players[0] = player1;
		}
		else if (ReadyFrame.p1chnumber==1) {
			Character player1 = new Dao(this,1); //플레이어1에 다오 생성
			players[0] = player1;
		}
		else if (ReadyFrame.p1chnumber==3) {
			Character player1 = new Eddi(this,1); //플레이어1에 에띠 생성
			players[0] = player1;
		}
		else if (ReadyFrame.p1chnumber==4) {
			Character player1 = new Mos(this,1); //플레이어1에 모스 생성
			players[0] = player1;
		}
		else if (ReadyFrame.p1chnumber==5) {
			Character player1 = new Marid(this,1); //플레이어1에 마리드 생성
			players[0] = player1;
		}
		else if (ReadyFrame.p1chnumber==8) {
			Character player1 = new Keppy(this,1); //플레이어1에 케피 생성
			players[0] = player1;
		}
		else if (ReadyFrame.p1chnumber==9) {
			Character player1 = new Su(this,1); //플레이어1에 수 생성
			players[0] = player1;
		}
		if (ReadyFrame.p2chnumger==2) {
			Character player2 = new Dizini(this,2); //플레이어2에 디지니 생성
			players[1] = player2;
		}
		else if (ReadyFrame.p2chnumger==6) {
			Character player2 = new Bazzi(this,2); //플레이어2에 배찌 생성
			players[1] = player2;
		}
		else if (ReadyFrame.p2chnumger==7) {
			Character player2 = new Uni(this,2); //플레이어2에 우니 생성
			players[1] = player2;
		}
		else if (ReadyFrame.p2chnumger==1) {
			Character player2 = new Dao(this,2); //플레이어2에 다오 생성
			players[1] = player2;
		}
		else if (ReadyFrame.p2chnumger==3) {
			Character player2 = new Eddi(this,2); //플레이어2에 에띠 생성
			players[1] = player2;
		}
		else if (ReadyFrame.p2chnumger==4) {
			Character player2 = new Mos(this,2); //플레이어2에 모스 생성
			players[1] = player2;
		}
		else if (ReadyFrame.p2chnumger==5) {
			Character player2 = new Marid(this,2); //플레이어2에 마리드 생성
			players[1] = player2;
		}
		else if (ReadyFrame.p2chnumger==8) {
			Character player2 = new Keppy(this,2); //플레이어2에 케피 생성
			players[1] = player2;
		}
		else if (ReadyFrame.p2chnumger==9) {
			Character player2 = new Su(this,2); //플레이어2에 수 생성
			players[1] = player2;
		}
		addKeyListener(this);
		addComponentListener(this);
		
		/* mapXlocaionlist와 mapYlocationlist는 맵의 각 타일들의 중심좌표의 x와 y값을 각각 저장*/
		/*for문을 이용하여 첫 타일은 (0,0)에서 시작해 x와 y 각각 60씩 증가하며 중심좌표들이 저장됨*/
		this.mapXlocationlist = new int[13];
		this.mapYlocationlist = new int[13];
		int locationnum = 0;
		for(int i=0; i<13;i++) {
				this.mapXlocationlist[i] = locationnum;
				this.mapYlocationlist[i] = locationnum;
				locationnum += 60.45;
		}
		
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {//0.001초 주기로 repaint
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
				BoomJudge.die(); /*0.001초마다, die 함수를 호출하여
				캐릭터가 물풍선이 터지는 위치에 들어오는지를 실시간으로 판단함*/
				BoomJudge.item_check();
			}
		},0, 1);
	}
	
	public void paint(Graphics g) {//스크린에 그리는 부분
		initBufferd();
		this.characterin();
		Dimension dim = getSize();
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);
		if(map_selection == 0) {//쿠키맵일때 배경
			bufferGraphics.drawImage(map_CookieBackground,0,0,this);
			for(int map_y=0; map_y<BoomJudge.map_size.length; map_y++) {
				for(int map_x=0; map_x<BoomJudge.map_size.length; map_x++) {
					if((BoomJudge.map_size[map_y][map_x] == 5) || (BoomJudge.map_size[map_y][map_x] == 7) || 
							(BoomJudge.map_size[map_y][map_x] == 10) || (BoomJudge.map_size[map_y][map_x] == 13) ||
							(BoomJudge.map_size[map_y][map_x] == 16) || (BoomJudge.map_size[map_y][map_x] == 19) ||
							(BoomJudge.map_size[map_y][map_x] == 22) || (BoomJudge.map_size[map_y][map_x] == 25)) {
						bufferGraphics.drawImage(map_CookieBox1, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//맵 인덱스에 맞게 블록 이미지 생성
					}
					if((BoomJudge.map_size[map_y][map_x] == 6) || (BoomJudge.map_size[map_y][map_x] == 8) || 
							(BoomJudge.map_size[map_y][map_x] == 11) || (BoomJudge.map_size[map_y][map_x] == 14) ||
							(BoomJudge.map_size[map_y][map_x] == 17) || (BoomJudge.map_size[map_y][map_x] == 20) ||
							(BoomJudge.map_size[map_y][map_x] == 23) || (BoomJudge.map_size[map_y][map_x] == 26)) {
						bufferGraphics.drawImage(map_CookieBox2, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//맵 인덱스에 맞게 블록 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 9) {
						bufferGraphics.drawImage(item_waterbomb, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//물풍선 늘려주는 아이템 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 12) {
						bufferGraphics.drawImage(item_speed, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//속도증가 아이템 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 15) {
						bufferGraphics.drawImage(item_stream, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//물풍선 범위증가 아이템 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 18) {
						bufferGraphics.drawImage(item_reddevil, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//레드데빌 아이템
					}
					if(BoomJudge.map_size[map_y][map_x] == 21) {
						bufferGraphics.drawImage(item_purpledevil, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//퍼플데빌 아이템 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 24) {
						bufferGraphics.drawImage(item_hackstream, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//핵풍선 아이템 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 27) {
						bufferGraphics.drawImage(item_super, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//슈퍼맨 아이템 이미지 생성
					}
				}
			}
		}else if(map_selection == 1) {//해적맵일때 배경
			bufferGraphics.drawImage(map_PatriotsBackground,0,0,this);
			for(int map_y=0; map_y<BoomJudge.map_size.length; map_y++) {
				for(int map_x=0; map_x<BoomJudge.map_size.length; map_x++) {
					if((BoomJudge.map_size[map_y][map_x] == 5) || (BoomJudge.map_size[map_y][map_x] == 7) || 
							(BoomJudge.map_size[map_y][map_x] == 10) || (BoomJudge.map_size[map_y][map_x] == 13) ||
							(BoomJudge.map_size[map_y][map_x] == 16) || (BoomJudge.map_size[map_y][map_x] == 19) ||
							(BoomJudge.map_size[map_y][map_x] == 22) || (BoomJudge.map_size[map_y][map_x] == 25)) {
						bufferGraphics.drawImage(map_PatriotsBox2, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//맵 인덱스에 맞게 블록 이미지 생성
					}
					if((BoomJudge.map_size[map_y][map_x] == 6) || (BoomJudge.map_size[map_y][map_x] == 8) || 
							(BoomJudge.map_size[map_y][map_x] == 11) || (BoomJudge.map_size[map_y][map_x] == 14) ||
							(BoomJudge.map_size[map_y][map_x] == 17) || (BoomJudge.map_size[map_y][map_x] == 20) ||
							(BoomJudge.map_size[map_y][map_x] == 23) || (BoomJudge.map_size[map_y][map_x] == 26)) {
						bufferGraphics.drawImage(map_PatriotsBox3, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//맵 인덱스에 맞게 블록 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 9) {
						bufferGraphics.drawImage(item_waterbomb, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//물풍선 늘려주는 아이템 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 12) {
						bufferGraphics.drawImage(item_speed, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//속도증가 아이템 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 15) {
						bufferGraphics.drawImage(item_stream, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//물풍선 범위증가 아이템 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 18) {
						bufferGraphics.drawImage(item_reddevil, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//레드데빌 아이템
					}
					if(BoomJudge.map_size[map_y][map_x] == 21) {
						bufferGraphics.drawImage(item_purpledevil, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//퍼플데빌 아이템 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 24) {
						bufferGraphics.drawImage(item_hackstream, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//핵풍선 아이템 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 27) {
						bufferGraphics.drawImage(item_super, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//슈퍼맨 아이템 이미지 생성
					}
				}
			}
		}else if(map_selection == 2) {//빌리지맵일때 배경
			bufferGraphics.drawImage(map_VillageBackground,0,0,this);
			for(int map_y=0; map_y<BoomJudge.map_size.length; map_y++) {
				for(int map_x=0; map_x<BoomJudge.map_size.length; map_x++) {
					if((BoomJudge.map_size[map_y][map_x] == 5) || (BoomJudge.map_size[map_y][map_x] == 7) || 
							(BoomJudge.map_size[map_y][map_x] == 10) || (BoomJudge.map_size[map_y][map_x] == 13) ||
							(BoomJudge.map_size[map_y][map_x] == 16) || (BoomJudge.map_size[map_y][map_x] == 19) ||
							(BoomJudge.map_size[map_y][map_x] == 22) || (BoomJudge.map_size[map_y][map_x] == 25)) {
						bufferGraphics.drawImage(map_VillageBox3, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//맵 인덱스에 맞게 블록 이미지 생성
					}
					if((BoomJudge.map_size[map_y][map_x] == 6) || (BoomJudge.map_size[map_y][map_x] == 8) || 
							(BoomJudge.map_size[map_y][map_x] == 11) || (BoomJudge.map_size[map_y][map_x] == 14) ||
							(BoomJudge.map_size[map_y][map_x] == 17) || (BoomJudge.map_size[map_y][map_x] == 20) ||
							(BoomJudge.map_size[map_y][map_x] == 23) || (BoomJudge.map_size[map_y][map_x] == 26)) {
						bufferGraphics.drawImage(map_VillageBox2, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//맵 인덱스에 맞게 블록 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 9) {
						bufferGraphics.drawImage(item_waterbomb, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//물풍선 늘려주는 아이템 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 12) {
						bufferGraphics.drawImage(item_speed, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//속도증가 아이템 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 15) {
						bufferGraphics.drawImage(item_stream, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//물풍선 범위증가 아이템 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 18) {
						bufferGraphics.drawImage(item_reddevil, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//레드데빌 아이템
					}
					if(BoomJudge.map_size[map_y][map_x] == 21) {
						bufferGraphics.drawImage(item_purpledevil, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//퍼플데빌 아이템 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 24) {
						bufferGraphics.drawImage(item_hackstream, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//핵풍선 아이템 이미지 생성
					}
					if(BoomJudge.map_size[map_y][map_x] == 27) {
						bufferGraphics.drawImage(item_super, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//슈퍼맨 아이템 이미지 생성
					}
				}
			}
		}
		
		for (int playertype = 0; playertype<MAX_PLAYER; playertype++) {
			for(int i=0;i<players[playertype].getballonListsize();i++) { /*물풍선의 링크드 리스트 사이즈 만큼 반복문 수행*/
				bufferGraphics.drawImage(players[playertype].getballoonImg(), mapXlocationlist[players[playertype].getballoonX(i)], mapYlocationlist[players[playertype].getballoonY(i)], this);
				/*물풍선 이미지를 그리되, 그리는 위치는 각 타일의 중앙이 되도록 함*/
			}
			
			for(int i=0;i<players[playertype].getboomballonListsize();i++) { /*터진 물풍선의 링크드 리스트 사이즈 만큼 반복문 수행*/
			/*일반적인 물풍선 터진 이미지 그리기*/
			bufferGraphics.drawImage(players[playertype].getcenterImg(),mapXlocationlist[players[playertype].getboomballoonX(i)],mapYlocationlist[players[playertype].getboomballoonY(i)], this);
			boolean check_end=true;
			for(int plusbombsize = players[playertype].getbombSize(2).get(i); plusbombsize>0; plusbombsize--) {
				if(players[playertype].getboomballoonX(i)-plusbombsize>=0) {
					if (check_end) {
						bufferGraphics.drawImage(players[playertype].getleftImg(),mapXlocationlist[players[playertype].getboomballoonX(i)-plusbombsize],mapYlocationlist[players[playertype].getboomballoonY(i)], this);
						check_end=false;
					}else {
						bufferGraphics.drawImage(boombmiddleleft,mapXlocationlist[players[playertype].getboomballoonX(i)-plusbombsize],mapYlocationlist[players[playertype].getboomballoonY(i)], this);
					}
				}
			}
			check_end=true;
			for(int plusbombsize = players[playertype].getbombSize(3).get(i); plusbombsize>0; plusbombsize--) {
				if(players[playertype].getboomballoonX(i)+plusbombsize<=12) {
					if (check_end) {
						bufferGraphics.drawImage(players[playertype].getrightImg(),mapXlocationlist[players[playertype].getboomballoonX(i)+plusbombsize],mapYlocationlist[players[playertype].getboomballoonY(i)], this);
						check_end=false;
					}else {
						bufferGraphics.drawImage(boombmiddleright,mapXlocationlist[players[playertype].getboomballoonX(i)+plusbombsize],mapYlocationlist[players[playertype].getboomballoonY(i)], this);
					}
				}
			}
			check_end=true;
			for(int plusbombsize = players[playertype].getbombSize(0).get(i); plusbombsize>0; plusbombsize--) {
				if(players[playertype].getboomballoonY(i)-plusbombsize>=0) {
					if (check_end) {
						bufferGraphics.drawImage(players[playertype].getupImg(),mapXlocationlist[players[playertype].getboomballoonX(i)],mapYlocationlist[players[playertype].getboomballoonY(i)-plusbombsize], this);
						check_end=false;
					}else {
						bufferGraphics.drawImage(boombmiddleup,mapXlocationlist[players[playertype].getboomballoonX(i)],mapYlocationlist[players[playertype].getboomballoonY(i)-plusbombsize], this);
					}
				}
			}
			check_end=true;
			for(int plusbombsize = players[playertype].getbombSize(1).get(i); plusbombsize>0; plusbombsize--) {
				if(players[playertype].getboomballoonY(i)+plusbombsize<=12) {
					if (check_end) {
						bufferGraphics.drawImage(players[playertype].getdownImg(),mapXlocationlist[players[playertype].getboomballoonX(i)],mapYlocationlist[players[playertype].getboomballoonY(i)+plusbombsize], this);
						check_end=false;
					}else {
						bufferGraphics.drawImage(boombmiddledown,mapXlocationlist[players[playertype].getboomballoonX(i)],mapYlocationlist[players[playertype].getboomballoonY(i)+plusbombsize], this);
					}
				}
			}
		}
	}
		
		for(int playertype=0; playertype<MAX_PLAYER; playertype++) {
			bufferGraphics.drawImage(players[playertype].getImg(), players[playertype].getX(), players[playertype].getY(), this);//players 이미지 생성
			players[playertype].getPlayerIndex_x(playerIndex_x[playertype]); //x인덱스값 캐릭터에게 주기
			players[playertype].getPlayerIndex_y(playerIndex_y[playertype]); //y인덱스값 캐릭터에게 주기
		}
		g.drawImage(this.bufferedImage, 0, 0, this);
	}
	
	public void characterin() {//캐릭터가 현재 맵의 어느 배열위치에 있는지 확인
		/* mapXlocaitonlist의 13개의 중심 좌표값을 현재의 플레이어 X좌표와 비교하여 그 차이가 40보다 작으면 인덱스를 해당 중심좌표의 인덱스로 변경함*/
		for (int playertype=0; playertype<MAX_PLAYER; playertype++) {
			if(BoomJudge.map_size[playerIndex_y[playertype]][playerIndex_x[playertype]] != 4) {
			BoomJudge.map_size[playerIndex_y[playertype]][playerIndex_x[playertype]] = 0;
			}
			if((BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 9) &&(BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 15)
					&& (BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 12) && (BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 18)
					&& (BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 21) && (BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 24)
					&& (BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 27)) {
				BoomJudge.previous_map_size[playerIndex_y[playertype]][playerIndex_x[playertype]] = 0;/* 캐릭터에 대한 조작 이벤트가 발생시 map_size의 1 또는 2를 0으로 초기화*/
			}
			for(int i=0; i<13;i++) {
				if((-(players[playertype].getX()-mapXlocationlist[i])<40) || ((mapXlocationlist[i]-players[playertype].getX())<40)) {
					playerIndex_x[playertype] = i;
					previous_Index_x[playertype] = i;
				}
			}
			/* mapYlocaitonlist의 13개의 중심 좌표값을 현재의 플레이어 Y좌표와 비교하여 그 차이가 40보다 작으면 인덱스를 해당 중심좌표의 인덱스로 변경함*/
			for(int i=0; i<13;i++) {
				if((-(players[playertype].getY()-mapYlocationlist[i])<40) || ((mapYlocationlist[i]-players[playertype].getY())<40)) {
					playerIndex_y[playertype] = i;
					previous_Index_y[playertype] = i;
				}
			}
			BoomJudge.map_size[playerIndex_y[playertype]][playerIndex_x[playertype]] = playertype+1; /*캐릭터의 위치를 저장*/ //player1은 1로 player2는 2로 저장
			if((BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 9)&&(BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 15)
					&& (BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 12)&& (BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 18)
					&& (BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 21) && (BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 24)
					&& (BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 27)) {
				BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] = 0;
				BoomJudge.previous_map_size[playerIndex_y[playertype]][playerIndex_x[playertype]] = playertype+1;
			}
			/*물풍선을 놓고 마지막에 이동한 위치를 저장하고 물풍선이 존재하는 맵과 비교하기 위하여
			 * previous_map_size를 사용함
			 * 
			 */
		}
	}
	
	public void update(Graphics g) {//업데이트 함수
		paint(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {/*동시이동적용*/
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			c1.setUp(true);
			break;
		case KeyEvent.VK_S:
			c1.setDown(true);
			break;
		case KeyEvent.VK_A:
			c1.setLeft(true);
			break;
		case KeyEvent.VK_D:
			c1.setRight(true);
			break;
		case KeyEvent.VK_UP:
			 c2.setUp(true);
			break;
		case KeyEvent.VK_DOWN:
			c2.setDown(true);
			break;
		case KeyEvent.VK_LEFT:
			c2.setLeft(true);
			break;
		case KeyEvent.VK_RIGHT:
			c2.setRight(true);
			break;
		}
        
        
		players[0].keyPressed(e);
		players[1].keyPressed(e);
		characterin(); // 버튼 누를때마다 캐릭터 위치 반영
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) { /*동시이동적용*/
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			c1.setUp(false);
			this.players[0].getmovestop();
			break;
		case KeyEvent.VK_S:
			c1.setDown(false);
			this.players[0].getmovestop();
			break;
		case KeyEvent.VK_A:
			c1.setLeft(false);
			this.players[0].getmovestop();
			break;
		case KeyEvent.VK_D:
			c1.setRight(false);
			this.players[0].getmovestop();
			break;
		case KeyEvent.VK_UP:
			 c2.setUp(false);
			 this.players[1].getmovestop();
			break;
		case KeyEvent.VK_DOWN:
			c2.setDown(false);
			this.players[1].getmovestop();
			break;
		case KeyEvent.VK_LEFT:
			c2.setLeft(false);
			this.players[1].getmovestop();
			break;
		case KeyEvent.VK_RIGHT:
			c2.setRight(false);
			this.players[1].getmovestop();
			break;
		}
        repaint();
	}
	
	private void initBufferd() {//버퍼 초기화
		this.dim = getSize();
		this.bufferedImage = createImage(dim.width, dim.height);
		this.bufferGraphics = this.bufferedImage.getGraphics();
	}

	@Override
	public void componentResized(ComponentEvent e) {//창 크기가 바뀔때 버퍼 초기화
		// TODO Auto-generated method stub
		initBufferd();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}