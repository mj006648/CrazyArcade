import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class CharacterChoice extends JFrame implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image ChrChoiceBackground = new ImageIcon("Resources/Charcterch.png").getImage();//ó�� ��� �ֱ�
	private int num =0;
	
	public CharacterChoice(){ 
		this.setTitle("CharacterChoice"); //â ����
		this.setSize(800, 610); //â ũ��
		this.setResizable(false);//â ũ�� ����
		this.setLayout(new BorderLayout());///BorderLayout ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ����� ���μ������� ��� ����
		this.setLocationRelativeTo(null); //â ��� ��ġ
		this.setVisible(true); //â ���̰� �ϱ�
		this.addMouseListener(this);
	}
	

	public void paint(Graphics g) {
		if (num ==0) {
		   g.drawImage(ChrChoiceBackground,0,0,null); //ó�� ��� �ֱ�
		   num=1;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Rectangle dao = new Rectangle(0, 20, 790, 65); //�ٿ�����
		boolean check = dao.contains(e.getPoint());
		if(check) {
			if (ReadyFrame.plyaernumber==1) {
				ReadyFrame.p1chnumber=1;
				ReadyFrame.p1chrcheck=1;
			}
			else if(ReadyFrame.plyaernumber==2) {
				ReadyFrame.p2chnumger=1;
				ReadyFrame.p2chrcheck=1;
			}
			CharacterChoice.this.setVisible(false); //���� â �����
		}
		Rectangle dizini = new Rectangle(0, 86, 790, 65); //�����ϼ���
		boolean check1 = dizini.contains(e.getPoint());
		if(check1) {
			if (ReadyFrame.plyaernumber==1) {
				ReadyFrame.p1chnumber=2;
				ReadyFrame.p1chrcheck=1;
			}
			else if(ReadyFrame.plyaernumber==2) {
				ReadyFrame.p2chnumger=2;
				ReadyFrame.p2chrcheck=1;
			}
			CharacterChoice.this.setVisible(false); //���� â �����
		}
		Rectangle eddi = new Rectangle(0, 150, 790, 65); //���켱��
		boolean check2 = eddi.contains(e.getPoint());
		if(check2) {
			if (ReadyFrame.plyaernumber==1) {
				ReadyFrame.p1chnumber=3;
				ReadyFrame.p1chrcheck=1;
			}
			else if(ReadyFrame.plyaernumber==2) {
				ReadyFrame.p2chnumger=3;
				ReadyFrame.p2chrcheck=1;
			}
			CharacterChoice.this.setVisible(false); //���� â �����
		}
		Rectangle mos = new Rectangle(0, 220, 790, 65); //�𽺼���
		boolean check3 = mos.contains(e.getPoint());
		if(check3) {
			if (ReadyFrame.plyaernumber==1) {
				ReadyFrame.p1chnumber=4;
				ReadyFrame.p1chrcheck=1;
			}
			else if(ReadyFrame.plyaernumber==2) {
				ReadyFrame.p2chnumger=4;
				ReadyFrame.p2chrcheck=1;
			}
			CharacterChoice.this.setVisible(false); //���� â �����
		}
		Rectangle marid = new Rectangle(0, 285, 790, 65); //�����弱��
		boolean check4 = marid.contains(e.getPoint());
		if(check4) {
			if (ReadyFrame.plyaernumber==1) {
				ReadyFrame.p1chnumber=5;
				ReadyFrame.p1chrcheck=1;
			}
			else if(ReadyFrame.plyaernumber==2) {
				ReadyFrame.p2chnumger=5;
				ReadyFrame.p2chrcheck=1;
			}
			CharacterChoice.this.setVisible(false); //���� â �����
		}
		Rectangle bazzi = new Rectangle(0, 345, 790, 65); //�����
		boolean check5 = bazzi.contains(e.getPoint());
		if(check5) {
			if (ReadyFrame.plyaernumber==1) {
				ReadyFrame.p1chnumber=6;
				ReadyFrame.p1chrcheck=1;
			}
			else if(ReadyFrame.plyaernumber==2) {
				ReadyFrame.p2chnumger=6;
				ReadyFrame.p2chrcheck=1;
			}
			CharacterChoice.this.setVisible(false); //���� â �����
		}
		Rectangle uni = new Rectangle(0, 410, 790, 65); //��ϼ���
		boolean check6 = uni.contains(e.getPoint());
		if(check6) {
			if (ReadyFrame.plyaernumber==1) {
				ReadyFrame.p1chnumber=7;
				ReadyFrame.p1chrcheck=1;
			}
			else if(ReadyFrame.plyaernumber==2) {
				ReadyFrame.p2chnumger=7;
				ReadyFrame.p2chrcheck=1;
			}
			CharacterChoice.this.setVisible(false); //���� â �����
		}
		Rectangle keppi = new Rectangle(0, 475, 790, 65); //���Ǽ���
		boolean check7 = keppi.contains(e.getPoint());
		if(check7) {
			if (ReadyFrame.plyaernumber==1) {
				ReadyFrame.p1chnumber=8;
				ReadyFrame.p1chrcheck=1;
			}
			else if(ReadyFrame.plyaernumber==2) {
				ReadyFrame.p2chnumger=8;
				ReadyFrame.p2chrcheck=1;
			}
			CharacterChoice.this.setVisible(false); //���� â �����
		}
		Rectangle su = new Rectangle(0, 540, 790, 65); //������
		boolean check8 = su.contains(e.getPoint());
		if(check8) {
			if (ReadyFrame.plyaernumber==1) {
				ReadyFrame.p1chnumber=9;
				ReadyFrame.p1chrcheck=1;
			}
			else if(ReadyFrame.plyaernumber==2) {
				ReadyFrame.p2chnumger=9;
				ReadyFrame.p2chrcheck=1;
			}
			CharacterChoice.this.setVisible(false); //���� â �����
		}
	}

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