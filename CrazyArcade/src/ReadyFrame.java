import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javazoom.jl.player.Player;

public class ReadyFrame extends JFrame implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image starteBackground = new ImageIcon("Resources/ready2.png").getImage();//처음 배경 넣기
	private Image daoBackground = new ImageIcon("Resources/dao.png").getImage();//다오 배경 넣기
	private Image eddiBackground = new ImageIcon("Resources/eddi.png").getImage();//에띠 배경 넣기
	private Image mosBackground = new ImageIcon("Resources/mos.png").getImage();//모스 배경 넣기
	private Image maridBackground = new ImageIcon("Resources/marid.png").getImage();//마리드 배경 넣기
	private Image keppiBackground = new ImageIcon("Resources/keppi.png").getImage();//케피 배경 넣기
	private Image suBackground = new ImageIcon("Resources/su.png").getImage();//수 배경 넣기
	private Image bazziBackground = new ImageIcon("Resources/bazzi.png").getImage();//배찌 배경 넣기
	private Image uniBackground = new ImageIcon("Resources/uni.png").getImage();//우니 배경 넣기
	private Image dizniackground = new ImageIcon("Resources/dizni.png").getImage();//디즈니 배경 넣기
	private Image FactoryBackground = new ImageIcon("Resources/Fatriotmap.png").getImage();//팩토리 배경 넣기
	private Image CookieBackground = new ImageIcon("Resources/Cookiemap.png").getImage();//쿠키 배경
	private Image VilageBackground = new ImageIcon("Resources/Vilagemap.png").getImage();//빌리지 배경
	private Image FactorylevelBackground = new ImageIcon("Resources/factorylevel.png").getImage();//팩토리 난이도 넣기
	private Image CookielevelBackground = new ImageIcon("Resources/Cookielevel.png").getImage();//쿠키 난이도
	private Image ReadyBackground = new ImageIcon("Resources/Readyy.png").getImage();//준비 그림
	private Image NoreadyBackground = new ImageIcon("Resources/Noready.png").getImage();//준비 취소 그림
	private Image VilagelevelBackground = new ImageIcon("Resources/Vilagelevel.png").getImage();//빌리지 난이도
	
	static int plyaernumber=0; //몇번 플레이어인지 구분하기위한 변수
	static int chrnumber=0;  //캐릭터그릴때 필요한 변수 디즈니 =1, 배찌 = 2, 우니 =3
	static int p1chnumber=0; //p1의 캐릭터
	static int p2chnumger=0; //p2의 캐릭터
	int count=0;
	private int p1ready=0;
	private int p2ready=0;
	static int p1chrcheck=0; //플레이어1의 캐릭터를 골랐는지 체크하는 변수
	static int p2chrcheck=0; //플레이어2의 캐릭터를 골랐는지 체크하는 변수
	
	private void readyplay() { //레디누를때 (프기프 교수님 참조 파일 참고)
		Player jlPlayer = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("Resources/ready.mp3");
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
	private void startplay() { //게임시작누를때 (프기프 교수님 참조 파일 참고)
		Player jlPlayer = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("Resources/start.mp3");
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
	
	public ReadyFrame(){ //MainFrame 생성자
		this.setTitle("GameReady"); //창 제목
		this.setSize(805, 610); //창 크기
		this.setResizable(false);//창 크기 고정
		this.setLayout(new BorderLayout());///BorderLayout 설정
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //윈도우 창 종료시 프로세스까지 모두 종료
		this.setLocationRelativeTo(null); //창 가운데 위치
		this.setVisible(true); //창 보이게 하기
		this.addMouseListener(this);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {//0.001초 주기로 repaint
			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
			}
		},0, 1);
	}

	public void paint(Graphics g) {
		if (count ==0) {
		    g.drawImage(starteBackground,0,0,null); //처음 배경 넣기 한번만 그려져야됨
		    count=1;
		}
		if (plyaernumber==1 && p1chnumber==2) { //첫번째 캐릭터 창에 디즈니 사진 띄우기
			g.drawImage(dizniackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==1) { //첫번째 캐릭터 창에 다오사진 띄우기
			g.drawImage(daoBackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==3) { //첫번째 캐릭터 창에 에띠사진 띄우기
			g.drawImage(eddiBackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==4) { //첫번째 캐릭터 창에 모스사진 띄우기
			g.drawImage(mosBackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==5) { //첫번째 캐릭터 창에 마리드사진 띄우기
			g.drawImage(maridBackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==8) { //첫번째 캐릭터 창에 케피사진 띄우기
			g.drawImage(keppiBackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==6) { //첫번째 캐릭터 창에 배찌사진 띄우기
			g.drawImage(bazziBackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==9) { //첫번째 캐릭터 창에 수사진 띄우기
			g.drawImage(suBackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==7) { //첫번째 캐릭터 창에 우니사진 띄우기
			g.drawImage(uniBackground,35,100,null);
		}
		if (plyaernumber==2 && p2chnumger==2) { //두번째 캐릭터 창에 디즈니 사진 띄우기
			g.drawImage(dizniackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==1) { //두번째 캐릭터 창에 다오사진 띄우기
			g.drawImage(daoBackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==3) { //두번째 캐릭터 창에 에띠사진 띄우기
			g.drawImage(eddiBackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==4) { //두번째 캐릭터 창에 모스사진 띄우기
			g.drawImage(mosBackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==5) { //두번째 캐릭터 창에 마리드사진 띄우기
			g.drawImage(maridBackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==8) { //두번째 캐릭터 창에 케피사진 띄우기
			g.drawImage(keppiBackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==6) { //두번째 캐릭터 창에 배찌사진 띄우기
			g.drawImage(bazziBackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==7) { //두번째 캐릭터 창에 우니사진 띄우기
			g.drawImage(uniBackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==9) { //두번째 캐릭터 창에 수사진 띄우기
			g.drawImage(suBackground,250,100,null);
		}			
		if (MapChoice.MapNumber==2){  //해적맵 고르면 해적맵이랑 해적난이도 그려줌
			g.drawImage(FactoryBackground,440 ,200, null);
			g.drawImage(FactorylevelBackground, 625,190, null);
		}
		else if (MapChoice.MapNumber==1){ //쿠키맵 고르면 쿠키맵이랑 쿠키난이도 그려줌
			g.drawImage(CookieBackground,440 ,200, null);
			g.drawImage(CookielevelBackground,625 ,190, null);
		}
		else if (MapChoice.MapNumber==3){ //빌리지맵 고르면 빌리지맵이랑 빌리지난이도 그려줌
			g.drawImage(VilageBackground,440 ,200, null);
			g.drawImage(VilagelevelBackground,625 ,190, null);
		}
		if (p1chrcheck==1 && p1ready == 1) {
			g.drawImage(ReadyBackground, 12 , 520, null);
		}
		else if (p1chrcheck==1 && p1ready == 0) {
			g.drawImage(NoreadyBackground, 12 , 520, null);
		}
		if (p2chrcheck==1 && p2ready == 1) {
			g.drawImage(ReadyBackground, 230 , 520, null);
		}
		else if (p2chrcheck==1 && p2ready == 0) {
			g.drawImage(NoreadyBackground, 228 , 520, null);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Rectangle map = new Rectangle(620, 320, 170, 100); //맵선택 누르면 맵초이스 화면뜸
		boolean mapcheck = map.contains(e.getPoint());
		if(mapcheck) {
			new MapChoice();
		}
		
		Rectangle start = new Rectangle(460, 450, 300, 240); // 시작 누르면 시작
		boolean startcheck = start.contains(e.getPoint());
		if(startcheck) {
			if (p1chnumber !=0 && p2chnumger !=0 && MapChoice.MapNumber==1) {
				if (p1ready ==1 && p2ready==1) {
					new MAP_Cookie(); //쿠키맵 시작
					startplay();
					MainFrame.music.stop();
				    ReadyFrame.this.setVisible(false); //현재 창 숨기기
				}
			}
			else if (p1chnumber !=0 && p2chnumger !=0 && MapChoice.MapNumber==2) {
				if (p1ready ==1 && p2ready==1) {
					startplay();
					MainFrame.music.stop();
					new MAP_Patriots(); //해적맵 시작
					ReadyFrame.this.setVisible(false); //현재 창 숨기기
				}
			}
			else if (p1chnumber !=0 && p2chnumger !=0 && MapChoice.MapNumber==3) {
				if (p1ready ==1 && p2ready==1) {
					startplay();
					MainFrame.music.stop();
					new MAP_Village(); //빌리지맵 시작
					ReadyFrame.this.setVisible(false); //현재 창 숨기기
				}
			}
			else if(MapChoice.MapNumber==0) {
				JOptionPane no = new JOptionPane();
				no.showMessageDialog(null, "맵을 골라주세요.");
			}
			else if(p1ready ==0 || p2ready ==0) {
				JOptionPane nono = new JOptionPane();
				nono.showMessageDialog(null, "준비를 해주세요.");
			}
		}
		
		Rectangle player1 = new Rectangle(20, 100, 180, 330); //첫번째 캐릭터 선택
		boolean p1check = player1.contains(e.getPoint());
		if(p1check) {
			plyaernumber =1; //1번플레이어
			new CharacterChoice(); //캐릭터 선택창 띄우기
		}
		Rectangle player2 = new Rectangle(230, 100, 190, 330); //두번째 캐릭터 선택
		boolean p2check = player2.contains(e.getPoint());
		if(p2check) {
			plyaernumber =2; //2번플레이어
			new CharacterChoice(); //캐릭터 선택창 띄우기
		}
		
		// ready가 1번이면 준비된거고 0번이면 준비가 안된거임
		Rectangle ready1 = new Rectangle(5, 520, 200, 70); //1 플레이어 준비
		boolean ready1check = ready1.contains(e.getPoint());
		if(ready1check) {
			if (p1chrcheck == 1 && p1ready ==0) {
				p1ready =1; //준비상태가 아닐때 준비를 누르면 1번 플레이어가 레디가됨
				readyplay();
			}
			else if(p1ready == 1) {
				p1ready =0; //준비상태일 때 준비를 누르면 1번 플레이어 레디가 사라짐
			}
		}
		Rectangle ready2 = new Rectangle(230, 520, 200, 70); //2 플레이어 준비
		boolean ready2check = ready2.contains(e.getPoint());
		if(ready2check) {
			if (p2chrcheck == 1 && p2ready ==0) {
				p2ready =1; //준비상태가 아닐때 준비를 누르면 2번 플레이어가 레디가됨
				readyplay();
			}
			else if(p2ready == 1) {
				p2ready =0; //준비상태일 때 준비를 누르면 2번 플레이어 레디가 사라짐
			}
		}
		
	}
	// 다오 :1, 디지니 :2, 에띠 :3, 모스:4, 마리드:5, 배찌:6, 우니:7, 케피:8, 수:9
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}