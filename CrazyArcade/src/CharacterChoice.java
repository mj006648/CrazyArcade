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
	private Image ChrChoiceBackground = new ImageIcon("Resources/Charcterch.png").getImage();//처음 배경 넣기
	private int num =0;
	
	public CharacterChoice(){ 
		this.setTitle("CharacterChoice"); //창 제목
		this.setSize(800, 610); //창 크기
		this.setResizable(false);//창 크기 고정
		this.setLayout(new BorderLayout());///BorderLayout 설정
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //윈도우 창 종료시 프로세스까지 모두 종료
		this.setLocationRelativeTo(null); //창 가운데 위치
		this.setVisible(true); //창 보이게 하기
		this.addMouseListener(this);
	}
	

	public void paint(Graphics g) {
		if (num ==0) {
		   g.drawImage(ChrChoiceBackground,0,0,null); //처음 배경 넣기
		   num=1;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Rectangle dao = new Rectangle(0, 20, 790, 65); //다오선택
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
			CharacterChoice.this.setVisible(false); //현재 창 숨기기
		}
		Rectangle dizini = new Rectangle(0, 86, 790, 65); //디지니선택
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
			CharacterChoice.this.setVisible(false); //현재 창 숨기기
		}
		Rectangle eddi = new Rectangle(0, 150, 790, 65); //에띠선택
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
			CharacterChoice.this.setVisible(false); //현재 창 숨기기
		}
		Rectangle mos = new Rectangle(0, 220, 790, 65); //모스선택
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
			CharacterChoice.this.setVisible(false); //현재 창 숨기기
		}
		Rectangle marid = new Rectangle(0, 285, 790, 65); //마리드선택
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
			CharacterChoice.this.setVisible(false); //현재 창 숨기기
		}
		Rectangle bazzi = new Rectangle(0, 345, 790, 65); //배찌선택
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
			CharacterChoice.this.setVisible(false); //현재 창 숨기기
		}
		Rectangle uni = new Rectangle(0, 410, 790, 65); //우니선택
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
			CharacterChoice.this.setVisible(false); //현재 창 숨기기
		}
		Rectangle keppi = new Rectangle(0, 475, 790, 65); //케피선택
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
			CharacterChoice.this.setVisible(false); //현재 창 숨기기
		}
		Rectangle su = new Rectangle(0, 540, 790, 65); //수선택
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
			CharacterChoice.this.setVisible(false); //현재 창 숨기기
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