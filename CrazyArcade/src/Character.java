import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public abstract class Character {
	private Screen screen;
	private int X;
	private int Y;
	public Character(Screen screen) {//��ũ�� �ҷ�����
		this.screen = screen;
	}
	public Screen getScreen() {//��ũ�� �������� �Լ�
		return this.screen;
	}
	public abstract Image getImg();//�̹��� �������� �Լ�
	public abstract Image getballoonImg(); //��ǳ�� �̹��� ��������
	public abstract Image getcenterImg(); //��ǳ�� �̹��� ��������
	public abstract Image getleftImg(); //��ǳ�� �̹��� ��������
	public abstract Image getrightImg(); //��ǳ�� �̹��� ��������
	public abstract Image getupImg(); //��ǳ�� �̹��� ��������
	public abstract Image getdownImg(); //��ǳ�� �̹��� ��������
	
	public int getX() {// X�� �������� �Լ�
		return this.X;
	}
	
	public int getY() {// Y�� �������� �Լ�
		return this.Y;
	}
	public abstract void up(int step);
	public abstract void down(int step);
	public abstract void left(int step);
	public abstract void right(int step);
	public abstract void keyPressed(KeyEvent e);
	protected abstract int getballoonY(int i);
	protected abstract int getballoonX(int i);
	protected abstract int getboomballoonY(int i);
	protected abstract int getboomballoonX(int i);
	protected abstract int getballonListsize();
	protected abstract int getboomballonListsize();
	protected abstract LinkedList<Integer> getbombSize(int i);
	protected abstract void getPlayerIndex_x(int x);
	protected abstract void getPlayerIndex_y(int y);
	public abstract void getmovestop();
}