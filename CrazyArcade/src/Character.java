import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public abstract class Character {
	private Screen screen;
	private int X;
	private int Y;
	public Character(Screen screen) {//스크린 불러오기
		this.screen = screen;
	}
	public Screen getScreen() {//스크린 가져오는 함수
		return this.screen;
	}
	public abstract Image getImg();//이미지 가져오는 함수
	public abstract Image getballoonImg(); //물풍선 이미지 가져오기
	public abstract Image getcenterImg(); //물풍선 이미지 가져오기
	public abstract Image getleftImg(); //물풍선 이미지 가져오기
	public abstract Image getrightImg(); //물풍선 이미지 가져오기
	public abstract Image getupImg(); //물풍선 이미지 가져오기
	public abstract Image getdownImg(); //물풍선 이미지 가져오기
	
	public int getX() {// X값 가져오는 함수
		return this.X;
	}
	
	public int getY() {// Y값 가져오는 함수
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