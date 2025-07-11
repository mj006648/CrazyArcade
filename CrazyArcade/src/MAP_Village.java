import java.awt.BorderLayout;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.swing.JFrame;

import javazoom.jl.player.Player;

public class MAP_Village extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static Thread music;
	
	public MAP_Village(){ //Patriots 생성자
		this.setTitle("Village"); //창 제목
		this.setSize(796, 817);//창 크기 -> Screen이 780,780으로 그려짐
		this.setResizable(false);//창 크기 고정
		this.setLayout(new BorderLayout());///BorderLayout 설정
		
		this.add(new Screen(2));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //윈도우 창 종료시 프로세스까지 모두 종료
		this.setLocationRelativeTo(null); //창 가운데 위치
		this.setVisible(true); //창 보이게 하기
		
		bgplay();
		music.start();
	}
	private void bgplay() { // 배경 음악 (프기프 교수님 참조 파일 참고)
		Player jlPlayer = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("Resources/villagemap.mp3");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            jlPlayer = new Player(bufferedInputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        final Player player = jlPlayer;
        music = new Thread() {
            public void run() {
                try {
                	while(true) {
                		player.play();
                	}
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        };
	}
}