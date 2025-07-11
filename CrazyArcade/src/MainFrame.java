import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javazoom.jl.player.Player;

public class MainFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton[] loginButtons; //시작, 종료 관리용 멤버필드
	private Image starteBackground = new ImageIcon("Resources/start.png").getImage();//처음 배경 넣기
	ImageIcon loginBackground = new ImageIcon("Resources/login.png");//로그인 배경 넣기
	ImageIcon exitBackground = new ImageIcon("Resources/exit.png");//종료 배경 넣기
	
	public static Thread music;
	
	public MainFrame(){ //MainFrame 생성자
		this.setTitle("CrazyArcade"); //창 제목
		this.setSize(800, 600); //창 크기
		this.setResizable(false);//창 크기 고정
		this.setLayout(new BorderLayout());///BorderLayout 설정
		
		this.add(loginPanel(), BorderLayout.SOUTH); //창 아래로 들어갈 패널 추가
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //윈도우 창 종료시 프로세스까지 모두 종료
		this.setLocationRelativeTo(null); //창 가운데 위치
		this.setVisible(true); //창 보이게 하기
		
		bgplay();
		music.start();
	}

	private void bgplay() { // 배경 음악 (프기프 교수님 참조 파일 참고)
		Player jlPlayer = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("Resources/gameReady.mp3");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            jlPlayer = new Player(bufferedInputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        final Player player = jlPlayer;
        music = new Thread() {
            public void run() {
                try {
                	while(!Thread.currentThread().isInterrupted()) {
                    	while(true) {
                    		player.play();
                    	}
                	}
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        };
    }
	
	public void paint(Graphics g) {
		g.drawImage(starteBackground,0,0,null); //처음 배경 넣기
	}
	
	private JPanel loginPanel() { //창 아래 들어갈 패널(시작,종료 선택)
		JPanel loginPanel = new JPanel(); //return할 패널 생성
		loginButtons = new JButton[2]; //로그인과 종료만있음
		JButton login = new JButton(loginBackground); 
		JButton exit = new JButton(exitBackground); 
		
		login.setPreferredSize(new Dimension(160,50)); //login 버튼 크기 160,50 설정
		exit.setPreferredSize(new Dimension(160,50)); //exit 버튼 크기 160,50 설정
	
		loginButtons[0] = login;
		loginButtons[1] = exit;
		for (int i = 0; i<loginButtons.length; i++) {
			loginPanel.add(loginButtons[i]); 
		}
		login.addActionListener(new ActionListener() {  //로그인 눌렀을때 맵선택 화면이나옴
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ReadyFrame();
				setVisible(false);
			}
		});
		exit.addActionListener(new ActionListener() {  //종료눌렀을때 화면이 꺼진다
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		return loginPanel;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}