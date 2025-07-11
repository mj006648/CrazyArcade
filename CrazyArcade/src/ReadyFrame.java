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
	private Image starteBackground = new ImageIcon("Resources/ready2.png").getImage();//ó�� ��� �ֱ�
	private Image daoBackground = new ImageIcon("Resources/dao.png").getImage();//�ٿ� ��� �ֱ�
	private Image eddiBackground = new ImageIcon("Resources/eddi.png").getImage();//���� ��� �ֱ�
	private Image mosBackground = new ImageIcon("Resources/mos.png").getImage();//�� ��� �ֱ�
	private Image maridBackground = new ImageIcon("Resources/marid.png").getImage();//������ ��� �ֱ�
	private Image keppiBackground = new ImageIcon("Resources/keppi.png").getImage();//���� ��� �ֱ�
	private Image suBackground = new ImageIcon("Resources/su.png").getImage();//�� ��� �ֱ�
	private Image bazziBackground = new ImageIcon("Resources/bazzi.png").getImage();//���� ��� �ֱ�
	private Image uniBackground = new ImageIcon("Resources/uni.png").getImage();//��� ��� �ֱ�
	private Image dizniackground = new ImageIcon("Resources/dizni.png").getImage();//����� ��� �ֱ�
	private Image FactoryBackground = new ImageIcon("Resources/Fatriotmap.png").getImage();//���丮 ��� �ֱ�
	private Image CookieBackground = new ImageIcon("Resources/Cookiemap.png").getImage();//��Ű ���
	private Image VilageBackground = new ImageIcon("Resources/Vilagemap.png").getImage();//������ ���
	private Image FactorylevelBackground = new ImageIcon("Resources/factorylevel.png").getImage();//���丮 ���̵� �ֱ�
	private Image CookielevelBackground = new ImageIcon("Resources/Cookielevel.png").getImage();//��Ű ���̵�
	private Image ReadyBackground = new ImageIcon("Resources/Readyy.png").getImage();//�غ� �׸�
	private Image NoreadyBackground = new ImageIcon("Resources/Noready.png").getImage();//�غ� ��� �׸�
	private Image VilagelevelBackground = new ImageIcon("Resources/Vilagelevel.png").getImage();//������ ���̵�
	
	static int plyaernumber=0; //��� �÷��̾����� �����ϱ����� ����
	static int chrnumber=0;  //ĳ���ͱ׸��� �ʿ��� ���� ����� =1, ���� = 2, ��� =3
	static int p1chnumber=0; //p1�� ĳ����
	static int p2chnumger=0; //p2�� ĳ����
	int count=0;
	private int p1ready=0;
	private int p2ready=0;
	static int p1chrcheck=0; //�÷��̾�1�� ĳ���͸� ������� üũ�ϴ� ����
	static int p2chrcheck=0; //�÷��̾�2�� ĳ���͸� ������� üũ�ϴ� ����
	
	private void readyplay() { //���𴩸��� (������ ������ ���� ���� ����)
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
	private void startplay() { //���ӽ��۴����� (������ ������ ���� ���� ����)
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
	
	public ReadyFrame(){ //MainFrame ������
		this.setTitle("GameReady"); //â ����
		this.setSize(805, 610); //â ũ��
		this.setResizable(false);//â ũ�� ����
		this.setLayout(new BorderLayout());///BorderLayout ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ����� ���μ������� ��� ����
		this.setLocationRelativeTo(null); //â ��� ��ġ
		this.setVisible(true); //â ���̰� �ϱ�
		this.addMouseListener(this);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {//0.001�� �ֱ�� repaint
			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
			}
		},0, 1);
	}

	public void paint(Graphics g) {
		if (count ==0) {
		    g.drawImage(starteBackground,0,0,null); //ó�� ��� �ֱ� �ѹ��� �׷����ߵ�
		    count=1;
		}
		if (plyaernumber==1 && p1chnumber==2) { //ù��° ĳ���� â�� ����� ���� ����
			g.drawImage(dizniackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==1) { //ù��° ĳ���� â�� �ٿ����� ����
			g.drawImage(daoBackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==3) { //ù��° ĳ���� â�� ������� ����
			g.drawImage(eddiBackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==4) { //ù��° ĳ���� â�� �𽺻��� ����
			g.drawImage(mosBackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==5) { //ù��° ĳ���� â�� ��������� ����
			g.drawImage(maridBackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==8) { //ù��° ĳ���� â�� ���ǻ��� ����
			g.drawImage(keppiBackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==6) { //ù��° ĳ���� â�� ������� ����
			g.drawImage(bazziBackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==9) { //ù��° ĳ���� â�� ������ ����
			g.drawImage(suBackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==7) { //ù��° ĳ���� â�� ��ϻ��� ����
			g.drawImage(uniBackground,35,100,null);
		}
		if (plyaernumber==2 && p2chnumger==2) { //�ι�° ĳ���� â�� ����� ���� ����
			g.drawImage(dizniackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==1) { //�ι�° ĳ���� â�� �ٿ����� ����
			g.drawImage(daoBackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==3) { //�ι�° ĳ���� â�� ������� ����
			g.drawImage(eddiBackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==4) { //�ι�° ĳ���� â�� �𽺻��� ����
			g.drawImage(mosBackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==5) { //�ι�° ĳ���� â�� ��������� ����
			g.drawImage(maridBackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==8) { //�ι�° ĳ���� â�� ���ǻ��� ����
			g.drawImage(keppiBackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==6) { //�ι�° ĳ���� â�� ������� ����
			g.drawImage(bazziBackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==7) { //�ι�° ĳ���� â�� ��ϻ��� ����
			g.drawImage(uniBackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==9) { //�ι�° ĳ���� â�� ������ ����
			g.drawImage(suBackground,250,100,null);
		}			
		if (MapChoice.MapNumber==2){  //������ ���� �������̶� �������̵� �׷���
			g.drawImage(FactoryBackground,440 ,200, null);
			g.drawImage(FactorylevelBackground, 625,190, null);
		}
		else if (MapChoice.MapNumber==1){ //��Ű�� ���� ��Ű���̶� ��Ű���̵� �׷���
			g.drawImage(CookieBackground,440 ,200, null);
			g.drawImage(CookielevelBackground,625 ,190, null);
		}
		else if (MapChoice.MapNumber==3){ //�������� ���� ���������̶� ���������̵� �׷���
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
		Rectangle map = new Rectangle(620, 320, 170, 100); //�ʼ��� ������ �����̽� ȭ���
		boolean mapcheck = map.contains(e.getPoint());
		if(mapcheck) {
			new MapChoice();
		}
		
		Rectangle start = new Rectangle(460, 450, 300, 240); // ���� ������ ����
		boolean startcheck = start.contains(e.getPoint());
		if(startcheck) {
			if (p1chnumber !=0 && p2chnumger !=0 && MapChoice.MapNumber==1) {
				if (p1ready ==1 && p2ready==1) {
					new MAP_Cookie(); //��Ű�� ����
					startplay();
					MainFrame.music.stop();
				    ReadyFrame.this.setVisible(false); //���� â �����
				}
			}
			else if (p1chnumber !=0 && p2chnumger !=0 && MapChoice.MapNumber==2) {
				if (p1ready ==1 && p2ready==1) {
					startplay();
					MainFrame.music.stop();
					new MAP_Patriots(); //������ ����
					ReadyFrame.this.setVisible(false); //���� â �����
				}
			}
			else if (p1chnumber !=0 && p2chnumger !=0 && MapChoice.MapNumber==3) {
				if (p1ready ==1 && p2ready==1) {
					startplay();
					MainFrame.music.stop();
					new MAP_Village(); //�������� ����
					ReadyFrame.this.setVisible(false); //���� â �����
				}
			}
			else if(MapChoice.MapNumber==0) {
				JOptionPane no = new JOptionPane();
				no.showMessageDialog(null, "���� ����ּ���.");
			}
			else if(p1ready ==0 || p2ready ==0) {
				JOptionPane nono = new JOptionPane();
				nono.showMessageDialog(null, "�غ� ���ּ���.");
			}
		}
		
		Rectangle player1 = new Rectangle(20, 100, 180, 330); //ù��° ĳ���� ����
		boolean p1check = player1.contains(e.getPoint());
		if(p1check) {
			plyaernumber =1; //1���÷��̾�
			new CharacterChoice(); //ĳ���� ����â ����
		}
		Rectangle player2 = new Rectangle(230, 100, 190, 330); //�ι�° ĳ���� ����
		boolean p2check = player2.contains(e.getPoint());
		if(p2check) {
			plyaernumber =2; //2���÷��̾�
			new CharacterChoice(); //ĳ���� ����â ����
		}
		
		// ready�� 1���̸� �غ�ȰŰ� 0���̸� �غ� �ȵȰ���
		Rectangle ready1 = new Rectangle(5, 520, 200, 70); //1 �÷��̾� �غ�
		boolean ready1check = ready1.contains(e.getPoint());
		if(ready1check) {
			if (p1chrcheck == 1 && p1ready ==0) {
				p1ready =1; //�غ���°� �ƴҶ� �غ� ������ 1�� �÷��̾ ���𰡵�
				readyplay();
			}
			else if(p1ready == 1) {
				p1ready =0; //�غ������ �� �غ� ������ 1�� �÷��̾� ���� �����
			}
		}
		Rectangle ready2 = new Rectangle(230, 520, 200, 70); //2 �÷��̾� �غ�
		boolean ready2check = ready2.contains(e.getPoint());
		if(ready2check) {
			if (p2chrcheck == 1 && p2ready ==0) {
				p2ready =1; //�غ���°� �ƴҶ� �غ� ������ 2�� �÷��̾ ���𰡵�
				readyplay();
			}
			else if(p2ready == 1) {
				p2ready =0; //�غ������ �� �غ� ������ 2�� �÷��̾� ���� �����
			}
		}
		
	}
	// �ٿ� :1, ������ :2, ���� :3, ��:4, ������:5, ����:6, ���:7, ����:8, ��:9
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