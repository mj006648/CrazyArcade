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
	private JButton[] loginButtons; //����, ���� ������ ����ʵ�
	private Image starteBackground = new ImageIcon("Resources/start.png").getImage();//ó�� ��� �ֱ�
	ImageIcon loginBackground = new ImageIcon("Resources/login.png");//�α��� ��� �ֱ�
	ImageIcon exitBackground = new ImageIcon("Resources/exit.png");//���� ��� �ֱ�
	
	public static Thread music;
	
	public MainFrame(){ //MainFrame ������
		this.setTitle("CrazyArcade"); //â ����
		this.setSize(800, 600); //â ũ��
		this.setResizable(false);//â ũ�� ����
		this.setLayout(new BorderLayout());///BorderLayout ����
		
		this.add(loginPanel(), BorderLayout.SOUTH); //â �Ʒ��� �� �г� �߰�
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ����� ���μ������� ��� ����
		this.setLocationRelativeTo(null); //â ��� ��ġ
		this.setVisible(true); //â ���̰� �ϱ�
		
		bgplay();
		music.start();
	}

	private void bgplay() { // ��� ���� (������ ������ ���� ���� ����)
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
		g.drawImage(starteBackground,0,0,null); //ó�� ��� �ֱ�
	}
	
	private JPanel loginPanel() { //â �Ʒ� �� �г�(����,���� ����)
		JPanel loginPanel = new JPanel(); //return�� �г� ����
		loginButtons = new JButton[2]; //�α��ΰ� ���Ḹ����
		JButton login = new JButton(loginBackground); 
		JButton exit = new JButton(exitBackground); 
		
		login.setPreferredSize(new Dimension(160,50)); //login ��ư ũ�� 160,50 ����
		exit.setPreferredSize(new Dimension(160,50)); //exit ��ư ũ�� 160,50 ����
	
		loginButtons[0] = login;
		loginButtons[1] = exit;
		for (int i = 0; i<loginButtons.length; i++) {
			loginPanel.add(loginButtons[i]); 
		}
		login.addActionListener(new ActionListener() {  //�α��� �������� �ʼ��� ȭ���̳���
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ReadyFrame();
				setVisible(false);
			}
		});
		exit.addActionListener(new ActionListener() {  //���ᴭ������ ȭ���� ������
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