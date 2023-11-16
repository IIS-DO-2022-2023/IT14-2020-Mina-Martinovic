package geometry1;

import java.awt.Color;
import java.awt.Graphics;

public class ThreeDShape extends Shape{

	//Ona predstavlja podklasu koja dodaje jednu konkretnu osobinu,
	//a to je boja popune (fillColor). Ova klasa ne implementira konkretne metode poput 
	//compareTo, contains, draw, itd.,već ostavlja te metode za konkretne
	//oblike koji nasleđuju ThreeDShape.
	//Svrha klase ThreeDShape je da omogući dodavanje osobine 
	//boje popune svim oblicima koji je nasleđuju. Na taj način, 
	//svi oblici koji nasleđuju SurfaceShape automatski dobijaju sposobnost 
	//da imaju boju popune,dok istovremeno dele zajedničke metode definisane u Shape.
	//linij i tacka mi se ne oopunjavaju zato nasledjuju i dalje samo shape
	private static final long serialVersionUID = 1L;
	private Color innerColor;
	
	public Color getInnerColor() {
		return innerColor;
	}
 
	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
