import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.swing.JOptionPane;

import javazoom.jl.player.Player;

public class BoomJudge {
	public static int[][] map_size = new int[13][13];//맵 사이즈 13*13
	/*물풍선에서 조작하기 위해 static으로 변경*/
	public static int character1_bombsizeup;
	public static int character2_bombsizeup;
	public static int character1_speedup;
	public static int character2_speedup;
	public static int character1_stream;
	public static int character2_stream;
	
	public static int[][] previous_map_size = new int[13][13];//맵 사이즈 13*13
	
	public BoomJudge(int map) {
		if (map == 0) {
			this.Map_Cookie_batch();
		}else if(map == 1) {
			this.Map_Patriots_batch();
		}else if(map == 2) {
			this.Map_Village_batch();
		}
		character1_bombsizeup = 0;
		character2_bombsizeup = 0;
		character1_speedup = 0;
		character2_speedup = 0;
		character1_stream = 0;
		character2_stream = 0;
	}
	/*숫자별 인덱스 의미
	    * 0 : 아무것도 없는 그냥 길
	    * 1 : player1
	    * 2 : player2
	    * 3 : 터지지 않은 물풍선
	    * 4 : 터진 물풍선
	    * 5, 6 : 벽(인덱스별로 벽 색이 다름)
	    * 7, 8 : 물풍선 최대개수 추가 아이템이 있는 벽(인덱스별로 벽 색이 다름)
	    * 9 : 물풍선 최대개수 추가 아이템
	    * 10, 11 : 속도 증가 아이템이 있는 벽(인덱스별로 벽 색이 다름)
	    * 12 : 속도 증가 아이템
	    * 13, 14 : 물풍선 범위 증가 아이템이 있는 벽(인덱스별로 벽 색이 다름)
	    * 15 : 물풍선 범위 증가 아이템
	    * 16, 17 : 레드데빌(속도 증가 +3) 아이템이 있는 벽(인덱스별로 벽 색이 다름)
	    * 18 : 레드데빌 아이템
	    * 19, 20 : 퍼플데빌(속도 감소 -3) 아이템이 있는 벽(인덱스별로 벽 색이 다름)
	    * 21 : 퍼플데빌 아이템
	    * 22, 23 : 핵풍선(물줄기 +5) 아이템이 있는 벽(인덱스별로 벽 색이 다름)
	    * 24 : 핵풍선 아이템
	    * 25, 26 : 슈퍼맨(물줄기 +3, 속도 +3) 아이템이 있는 벽(인덱스별로 벽 색이 다름)
	    * 27 : 슈퍼맨 아이템
	    * 
	    * 5, 7, 10, 13, 16, 19, 22, 25 같은 벽으로 보임
	    * 6, 8, 11, 14, 17, 20, 23, 26 같은 벽으로 보임
	    * 이후 인덱스 추가시 추가바람*/

	public void Map_Cookie_batch() {//쿠키맵 기본 맵 구성
		this.map_size = new int[][] {{ 1, 0,11, 6, 8, 6,14, 6, 6, 8,23, 0, 0 },
									 { 0, 8, 6,11, 6, 0, 0, 0, 6, 6, 6, 6, 0 },
									 {14, 6, 6, 0, 5, 5, 0, 5,10, 0, 6, 6, 6 },
									 { 6, 6, 0, 0, 0, 5, 5, 7, 0, 0, 0, 6, 8 },
									 { 8, 6,14, 0, 5,22, 5, 5,13, 0, 6, 6, 6 },
									 { 6, 0, 5, 7, 5, 5, 0, 5, 5,25, 5, 0,11 },
									 { 6, 0, 0, 5,16, 0, 0, 0,10, 5, 0, 0, 6 },
									 { 6, 0, 5,10, 5, 7, 0, 5, 5, 5, 5, 0, 8 },
									 { 6, 6, 6, 0, 5, 5,13, 5, 5, 0, 6, 6, 6 },
									 { 8, 6, 0, 0, 0, 5, 5, 7, 0, 0, 0, 8, 6 },
									 {11, 6, 6, 0, 5, 7, 0, 5, 5, 0, 6, 6,14 },
									 { 0, 8, 6, 6,23, 0, 0, 0, 6, 8, 6, 8, 0 },
									 { 0, 0, 6, 6,14, 6, 8, 6, 6, 6,11, 0, 2 }};
		this.previous_map_size = new int[][] {{ 1, 0,11, 6, 8, 6,14, 6, 6, 8,23, 0, 0 },
											  { 0, 8, 6,11, 6, 0, 0, 0, 6, 6, 6, 6, 0 },
											  {14, 6, 6, 0, 5, 5, 0, 5,10, 0, 6, 6, 6 },
											  { 6, 6, 0, 0, 0, 5, 5, 7, 0, 0, 0, 6, 8 },
											  { 8, 6,14, 0, 5,22, 5, 5,13, 0, 6, 6, 6 },
											  { 6, 0, 5, 7, 5, 5, 0, 5, 5,25, 5, 0,11 },
											  { 6, 0, 0, 5,16, 0, 0, 0,10, 5, 0, 0, 6 },
											  { 6, 0, 5,10, 5, 7, 0, 5, 5, 5, 5, 0, 8 },
											  { 6, 6, 6, 0, 5, 5,13, 5, 5, 0, 6, 6, 6 },
											  { 8, 6, 0, 0, 0, 5, 5, 7, 0, 0, 0, 8, 6 },
											  {11, 6, 6, 0, 5, 7, 0, 5, 5, 0, 6, 6,14 },
											  { 0, 8, 6, 6,23, 0, 0, 0, 6, 8, 6, 8, 0 },
											  { 0, 0, 6, 6,14, 6, 8, 6, 6, 6,11, 0, 2 }};
	}
	public void Map_Patriots_batch() {//해적맵 기본 맵 구성
		this.map_size = new int[][] {{ 6, 5,10, 5, 5,22, 5, 5,13, 5, 5, 7,11 },
									 { 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5 },
									 { 5, 0, 8, 0, 0, 5, 0,13, 0, 0,23, 0, 5 },
									 {25, 0, 0, 6, 0, 0, 0, 0, 0, 8, 0, 0,10 },
									 { 5, 0, 0, 0, 6, 0, 0, 0,14, 0, 0, 0, 5 },
									 { 7, 0, 0, 0, 0,11, 0,17, 0, 0, 0, 0,25 },
									 { 5, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 5 },
									 {13, 0, 0, 0, 0, 6, 0, 6, 0, 0, 0, 0, 7 },
									 { 5, 0, 0, 0, 6, 0, 0, 0,14, 0, 0, 0, 5 },
									 {10, 0, 0,14, 0, 0, 0, 0, 0, 6, 0, 0, 5 },
									 { 5, 0, 8, 0, 0, 7, 0, 5, 0, 0, 8, 0, 5 },
									 { 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2,22 },
									 { 8, 5, 7, 5,13, 5,10, 5, 5, 5,10, 5, 6 }};
		this.previous_map_size = new int[][] {{ 6, 5,10, 5, 5,22, 5, 5,13, 5, 5, 7,11 },
			 								  { 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5 },
			 								  { 5, 0, 8, 0, 0, 5, 0,13, 0, 0,23, 0, 5 },
			 								  {25, 0, 0, 6, 0, 0, 0, 0, 0, 8, 0, 0,10 },
			 								  { 5, 0, 0, 0, 6, 0, 0, 0,14, 0, 0, 0, 5 },
			 								  { 7, 0, 0, 0, 0,11, 0,17, 0, 0, 0, 0,25 },
			 								  { 5, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 5 },
			 								  {13, 0, 0, 0, 0, 6, 0, 6, 0, 0, 0, 0, 7 },
			 								  { 5, 0, 0, 0, 6, 0, 0, 0,14, 0, 0, 0, 5 },
			 								  {10, 0, 0,14, 0, 0, 0, 0, 0, 6, 0, 0, 5 },
			 								  { 5, 0, 8, 0, 0, 7, 0, 5, 0, 0, 8, 0, 5 },
			 								  { 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2,22 },
			 								  { 8, 5, 7, 5,13, 5,10, 5, 5, 5,10, 5, 6 }};
	}
	public void Map_Village_batch() {//마을맵 기본 맵 구성
		this.map_size = new int[][] {{ 1, 0, 0, 0, 0, 5, 8, 7, 0, 0, 0, 0, 0 },
									 { 0, 5,10, 5,13, 0,14, 0,25, 5, 5, 5, 0 },
									 { 0, 5,13, 7, 5, 0,20, 0, 5, 5, 5, 5, 0 },
									 { 5, 0, 0, 0, 0, 5, 6, 5, 0, 0, 0, 0,10 },
									 { 6, 0, 8, 6, 0, 8, 6,11, 0,11,17, 0,14 },
									 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									 { 0, 5, 0, 5, 0, 5, 0,25, 0, 5, 0,10, 0 },
									 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									 {11, 0, 6,14, 0,11,17, 8, 0, 6, 8, 0, 6 },
									 { 5, 0, 0, 0, 0, 7, 6, 5, 0, 0, 0, 0,10 },
									 { 0, 5,13,10, 5, 0,20, 0, 5, 5, 7, 5, 0 },
									 { 0,10, 5, 7, 5, 0,14, 0, 5, 7, 5,10, 0 },
									 { 0, 0, 0, 0, 0, 5,11, 5, 0, 0, 0, 0, 2 }};
		this.previous_map_size = new int[][] {{ 1, 0, 0, 0, 0, 5, 8, 7, 0, 0, 0, 0, 0 },
			 								  { 0, 5,10, 5,13, 0,14, 0,25, 5, 5, 5, 0 },
			 								  { 0, 5,13, 7, 5, 0,20, 0, 5, 5, 5, 5, 0 },
			 								  { 5, 0, 0, 0, 0, 5, 6, 5, 0, 0, 0, 0,10 },
			 								  { 6, 0, 8, 6, 0, 8, 6,11, 0,11,17, 0,14 },
			 								  { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			 								  { 0, 5, 0, 5, 0, 5, 0,25, 0, 5, 0,10, 0 },
			 								  { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			 								  {11, 0, 6,14, 0,11,17, 8, 0, 6, 8, 0, 6 },
			 								  { 5, 0, 0, 0, 0, 7, 6, 5, 0, 0, 0, 0,10 },
			 								  { 0, 5,13,10, 5, 0,20, 0, 5, 5, 7, 5, 0 },
			 								  { 0,10, 5, 7, 5, 0,14, 0, 5, 7, 5,10, 0 },
			 								  { 0, 0, 0, 0, 0, 5,11, 5, 0, 0, 0, 0, 2 }};
	}
	public static void die() {
		for(int map_y=0;map_y<13;map_y++) {
			for(int map_x=0;map_x<13;map_x++) {
				if((previous_map_size[map_y][map_x] == 1) && (map_size[map_y][map_x] == 4)) {
					if(Screen.map_selection == 0) {// 게임이 종료시 배경음 종료
						MAP_Cookie.music.stop();
					}else if(Screen.map_selection == 1) {
						MAP_Patriots.music.stop();
					}else if(Screen.map_selection == 2) {
						MAP_Village.music.stop();
					}
					bgplay();//게임 종료시 게임 끝 노래시작
					JOptionPane.showMessageDialog(null, "플레이어 1 사망");
					System.exit(0);
				} /*물풍선이 터지기 직전의 플레이어의 좌표를 가지는 맵 previous_map_size와 
				물풍선이 터질때의 인덱스 정보를 가지는 map_size를 비교하여 동일한 위치에 1과 4 혹은
				2와 4가 존재하면 사망처리*/
				if((previous_map_size[map_y][map_x] == 2) && (map_size[map_y][map_x] == 4)) {
					if(Screen.map_selection == 0) {
						MAP_Cookie.music.stop();
					}else if(Screen.map_selection == 1) {
						MAP_Patriots.music.stop();
					}else if(Screen.map_selection == 2) {
						MAP_Village.music.stop();
					}
					bgplay();
					JOptionPane.showMessageDialog(null, "플레이어 2 사망");
					System.exit(0);
				}
			}
		}
	}
	
	private static void bgplay() { // 게임 종료시 음악 (프기프 교수님 참조 파일 참고)
		Player jlPlayer = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("Resources/gamewin.mp3");
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
	
	public static void item_check() { //추가 및 체크 함수
		for(int map_y=0;map_y<13;map_y++) {
			for(int map_x=0;map_x<13;map_x++) {
				for (int playertype=1; playertype<3; playertype++) {
					if((previous_map_size[map_y][map_x] == 9) && (map_size[map_y][map_x] == playertype)) {//player 물풍선 추가 아이템 흭득 경우
						previous_map_size[map_y][map_x] = playertype;
						map_size[map_y][map_x] = playertype;
						if(playertype==1) {
							character1_bombsizeup+=1;
						}else {
							character2_bombsizeup+=1;
						}
					}
					if((previous_map_size[map_y][map_x] == 12) && (map_size[map_y][map_x] == playertype)) {//player 속도 증가 아이템 흭득 경우
						previous_map_size[map_y][map_x] = playertype;
						map_size[map_y][map_x] = playertype;
						if(playertype==1) {
							character1_speedup+=1;
						}else {
							character2_speedup+=1;
						}
					}
					if((previous_map_size[map_y][map_x] == 15) && (map_size[map_y][map_x] == playertype)) {//player 물풍선 범위 증가 아이템 흭득 경우
						previous_map_size[map_y][map_x] = playertype;
						map_size[map_y][map_x] = playertype;
						if(playertype==1) {
							character1_stream+=1;
						}else {
							character2_stream+=1;
						}
					}
					if((previous_map_size[map_y][map_x] == 18) && (map_size[map_y][map_x] == playertype)) {//player 속도 증가(레드데빌) 아이템 흭득 경우
						previous_map_size[map_y][map_x] = playertype;
						map_size[map_y][map_x] = playertype;
						if(playertype==1) {
							character1_speedup+=3;
						}else {
							character2_speedup+=3;
						}
					}
					if((previous_map_size[map_y][map_x] == 21) && (map_size[map_y][map_x] == playertype)) {//player 속도 감소(퍼플데빌) 아이템 흭득 경우
						previous_map_size[map_y][map_x] = playertype;
						map_size[map_y][map_x] = playertype;
						if(playertype==1) {
							character1_speedup-=3;
							if(character1_speedup<0) {
								character1_speedup = 0;
							}
						}else {
							character2_speedup-=3;
							if(character2_speedup<0) {
								character2_speedup = 0;
							}
						}
					}
					if((previous_map_size[map_y][map_x] == 24) && (map_size[map_y][map_x] == playertype)) {//player 핵풍선 아이템 흭득 경우
						previous_map_size[map_y][map_x] = playertype;
						map_size[map_y][map_x] = playertype;
						if(playertype==1) {
							character1_stream+=5;
						}else {
							character2_stream+=5;
						}
					}
					if((previous_map_size[map_y][map_x] == 27) && (map_size[map_y][map_x] == playertype)) {//player 슈퍼맨 아이템 흭득 경우
						previous_map_size[map_y][map_x] = playertype;
						map_size[map_y][map_x] = playertype;
						if(playertype==1) {
							character1_stream+=3;
							character1_speedup+=3;
						}else {
							character2_stream+=3;
							character2_speedup+=3;
						}
					}
				}
			}
		}
	}
}