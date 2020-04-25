import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class RailCar extends Vehicle{
	
	public static final int UNIT = 10 ;
    public static final int U6 = 6 * UNIT ;
    public static final int U5 = 5 * UNIT ;
    public static final int U4 = 4 * UNIT ;
    public static final int U3 = 3 * UNIT ;
    public static final int U2 = 2 * UNIT ;
    public static final int U15 = UNIT + UNIT / 2 ;
    public static final int U05 =  UNIT / 2 ;
    public static final int BODY_WIDTH = U3 ;
    public static final int BODY_HEIGHT = U2 ;
	
	RailCar()
	{
		super();
		m_rect = new Rectangle2D.Double(getX(), getY(), U6, U3);
	}
     
    /**
       Draw the rail car
       @param g2 the graphics context
     */
    public void draw(Graphics2D g2)
    {
		// think about whether getX() and getY() should be inherited
	    // or defined in this class
		int xLeft = getX() ;
		int yTop = getY() ;
		
		Rectangle2D.Double body 
		    = new Rectangle2D.Double(getX(), yTop + UNIT, U6, UNIT);      
		Ellipse2D.Double frontTire 
		    = new Ellipse2D.Double(getX() + UNIT, yTop + U2, UNIT, UNIT);
		Ellipse2D.Double rearTire
		    = new Ellipse2D.Double(getX() + U4, yTop + U2, UNIT, UNIT);
		
		// the bottom of the front windshield
		Point2D.Double r1 
		    = new Point2D.Double(getX() + UNIT, yTop + UNIT);
		// the front of the roof
		Point2D.Double r2 
		    = new Point2D.Double(getX() + U2, yTop);
		// the rear of the roof
		Point2D.Double r3 
		    = new Point2D.Double(getX() + U4, yTop);
		// the bottom of the rear windshield
		Point2D.Double r4 
		    = new Point2D.Double(getX() + U5, yTop + UNIT);
	
		// the right end of the hitch
		Point2D.Double r5 
		    = new Point2D.Double(getX() + U6, yTop + U15);
		// the left end of the hitch
		Point2D.Double r6 
		    = new Point2D.Double(getX() + U6 + U05, yTop + U15);
		
		Line2D.Double frontWindshield 
		    = new Line2D.Double(r1, r2);
		Line2D.Double roofTop 
		    = new Line2D.Double(r2, r3);
		Line2D.Double rearWindshield
		    = new Line2D.Double(r3, r4);
		Line2D.Double hitch
		    = new Line2D.Double(r5, r6);
	
		if( m_selected )
			g2.setColor(Color.red);
		g2.draw(hitch);
		g2.draw(frontTire);
		g2.draw(rearTire);
		g2.draw(body) ;
		//g2.draw(frontWindshield);
		//g2.draw(roofTop);
		//g2.draw(rearWindshield);
		
		// think about whether getNumber() should be inherited or
		// defined in this class
		g2.drawString("" + getNumber(), getX() + U2, yTop + U2) ;
    }
	
	public boolean selected(int x, int y)
	{
		Rectangle2D.Double box = new Rectangle2D.Double(getX(), getY(), U6, U3);
		return box.contains(x, y);
	}
}
