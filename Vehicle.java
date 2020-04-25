import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class Vehicle {
	int m_x;
	int m_y;
	int m_number = 0;
	boolean m_selected = false;
	Rectangle2D.Double m_rect = null;
	public Vehicle	m_pre = null;
	public Vehicle	m_next = null;
	
	public int getX()
	{
		return m_x;
	}
	
	public int getY()
	{
		return m_y;
	}
	
	public void setX(int x)
	{
		m_x = x;
	}
	
	public void setY(int y)
	{
		m_y = y;
	}
	
	 public int getNumber()
    {
    	return m_number;
    }
    
    public void setNumber(int num)
    {
    	m_number = num;
    }
	
	public boolean intersected(Rectangle2D.Double rect)
	{
		m_rect.setRect(m_x, m_y, m_rect.width, m_rect.height);
		return m_rect.intersects(rect.x, rect.y, rect.width, rect.height);
	}
	public Rectangle2D.Double getRectangle()
	{
		m_rect.setRect(m_x, m_y, m_rect.width, m_rect.height);
		return m_rect;
	}
	
	abstract public void draw(Graphics2D g2);
	abstract public boolean selected(int x, int y);
}
