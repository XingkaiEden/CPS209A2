import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class QueueBox {
	class Box{
		int m_len;
		int m_posx;
		int m_posy;
		char m_xx;
		Box	m_next;
	}
	
	Box[] m_Boxs;
	Box	m_head;
	Box m_tail;
	int m_numBox;
	int m_basex;
	int m_basey;
	int m_left;
	int m_baseWidth = 5*UNIT;
	int m_baseHeight = 2*UNIT;
	static int UNIT = 10;
	QueueBox(int num, int x, int y)
	{
		super();
		m_numBox = num;
		m_left = num;
		m_basex = x;
		m_basey = y;
		initBoxs();
		initQueue();
		
	}
	void initBoxs()
	{
		m_Boxs = new Box[m_numBox];
		for(int i=0; i<m_numBox; i++)
		{
			m_Boxs[i] = new Box();
			m_Boxs[i].m_len = 3*UNIT;
			m_Boxs[i].m_posx = m_basex + UNIT;
			m_Boxs[i].m_posy = m_basey - (i+1) * ( m_Boxs[i].m_len + 2 );
			m_Boxs[i].m_xx = (char)('A' + i);
		}
	}
	void initQueue()
	{
		m_head = m_Boxs[0];
		m_tail = m_Boxs[m_numBox-1];
		for(int i=0; i<m_Boxs.length-1; i++)
			m_Boxs[i].m_next = m_Boxs[i+1];
		m_Boxs[m_Boxs.length-1].m_next = null;
	}
	
	public void draw(Graphics2D g2)
	{
		Rectangle2D.Double baseBox
	    	= new Rectangle2D.Double(m_basex, m_basey, m_baseWidth, m_baseHeight);
		g2.setColor(Color.black);
		g2.fill(baseBox);
		
		for(int i=0; i<m_Boxs.length; i++)
		{
			Rectangle2D.Double box = new Rectangle2D.Double(
					m_Boxs[i].m_posx, m_Boxs[i].m_posy, m_Boxs[i].m_len, m_Boxs[i].m_len);
			g2.draw(box);
			g2.drawString(""+m_Boxs[i].m_xx, m_Boxs[i].m_posx + m_Boxs[i].m_len / 2, 
					m_Boxs[i].m_posy + m_Boxs[i].m_len / 2);
		}
	}
}
