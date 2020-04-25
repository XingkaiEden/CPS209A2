import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CarFrame extends JFrame implements MouseListener,MouseMotionListener{
	private static final int FRAME_WIDTH = 480;
	private static final int FRAME_HEIGHT = 640;
	private static final int SIZE = 5;
	private Image m_bufferImage = null;
	int m_cnt = 0;
	int m_times = 0;
	int m_selected = -1;
	Vehicle[] m_vehicle;
	QueueBox m_queue = null;
	int[] m_toQue = null;
	
	public CarFrame() {
		// TODO Auto-generated constructor stub
		addMouseListener(this);
		addMouseMotionListener(this);
		JMenuBar menuBar = new JMenuBar();     
	    setJMenuBar(menuBar);
	    menuBar.add( createFileMenu() );
	    menuBar.add( createQueueMenu() );
	    menuBar.add( createListMenu() );
	    setSize(FRAME_WIDTH, FRAME_HEIGHT);
	    initVehicle();
	    initToQue();
	}
	void initToQue()
	{
		m_toQue = new int[SIZE];
		for(int i=0; i<SIZE; i++)
			m_toQue[i] = -1;
	}
	void initVehicle()
	{
		m_vehicle = new Vehicle[SIZE+1];
		for(int i=0; i<m_vehicle.length; i++)
			m_vehicle[i] = null;
	}
	
	class ExitItemListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			System.exit(0);
		}
		
	}
	
	 /**
	  * Creates the File menu.
	  * @return the menu
	 */
	 public JMenu createFileMenu()
	 {
		ActionListener newxListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < m_vehicle.length; i++)
					m_vehicle[i] = null;
				m_queue = null;
				m_cnt = 0;
				m_times++;
				repaint();
			}
		};
		 
		 ActionListener ExitItemListener = new ActionListener()
		 {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		 };
		 
	    JMenu menu = new JMenu("File");
	    JMenuItem newItem = new JMenuItem("New");
	    menu.add(newItem);
		newItem.addActionListener(newxListener);
		
	    JMenuItem exitItem = new JMenuItem("Exit");      
	    exitItem.addActionListener(ExitItemListener);
	    menu.add(exitItem);
	    return menu;
	 }
	 
	 /**
	  * Creates the Queue menu
	  * @return the menu
	  */
	 public JMenu createQueueMenu()
	 {
		 ActionListener addListener = new ActionListener()
		 {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				add();
				repaint();
			}
		 };
		 ActionListener removeListener = new ActionListener()
		 {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				remove();
				repaint();
			}
		 };
		 JMenu menu = new JMenu("Queue");
		 JMenuItem remove = new JMenuItem("Remove");
		 JMenuItem add = new JMenuItem("Add");
		 remove.addActionListener(removeListener);
		 add.addActionListener(addListener);
		 menu.add(remove);
		 menu.add(add);
		 return menu;
	 }
	 
	 /**
	  * Creates the List menu
	  * @return the menu
	  */
	 public JMenu createListMenu()
	 {
		 ActionListener addFirstListener = new ActionListener()
		 {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				addFirst();
				repaint();
			}
		 };
		ActionListener addLastListener = new ActionListener()
		 {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				addLast();
				repaint();
			}
		 };
		ActionListener removeFirstListener = new ActionListener()
		 {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				removeFirst();
				repaint();
			}
		 };
		 ActionListener removeLastListener = new ActionListener()
		 {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				removeLast();
				repaint();
			}
		 };
		 
		 JMenu menu = new JMenu("List");
		 JMenuItem addFirst = new JMenuItem("Add First");
		 JMenuItem addLast = new JMenuItem("Add Last");
		 JMenuItem removeFirst  = new JMenuItem("Remove First");
		 JMenuItem removeLast = new JMenuItem("Remove Last");
		 menu.add(addFirst);
		 menu.add(addLast);
		 menu.add(removeFirst);
		 menu.add(removeLast);
		 addFirst.addActionListener(addFirstListener);
		 addLast.addActionListener(addLastListener);
		 removeFirst.addActionListener(removeFirstListener);
		 removeLast.addActionListener(removeLastListener);
		 
		
		 return menu;
	 }

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if( m_selected >=0 )
		{
			if(m_vehicle[m_selected].m_pre != null)
				m_vehicle[m_selected].m_pre.m_next = null;
			int x = m_vehicle[m_selected].getX();
			int y = m_vehicle[m_selected].getY();
			int dx = arg0.getX() - x;
			int dy = arg0.getY() - y;
			Vehicle tmp = m_vehicle[m_selected];
			while( tmp != null)
			{
				//get the index of tmp in m_vehicle
				int index = -1;
				for(int i= 1; i<= SIZE; i++)
				{
					if(m_vehicle[i] == tmp)
					{
						index = m_toQue[i-1];
						break;
					}
				}
				
				//fix the coordinate of queue box
				if(index >=0)
				{
					m_queue.m_Boxs[index].m_posx += dx;
					m_queue.m_Boxs[index].m_posy += dy;
				}
				
				//fix the coordinate of car's linkers
				tmp.setX(tmp.getX() + dx);
				tmp.setY(tmp.getY() + dy);
				tmp = tmp.m_next;
				
			}
			
			repaint();
		}		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int x = arg0.getX();
		int y = arg0.getY();
		int base = m_times * (SIZE+1);
		if( m_cnt == 0)
		{
			m_vehicle[m_cnt] = new TrainEngine();
			m_vehicle[m_cnt].setX(x);
			m_vehicle[m_cnt].setY(y);
			//m_selected = m_cnt;
			m_cnt++;
		}
		else if( m_cnt < SIZE+1 )
		{
			m_vehicle[m_cnt] = new RailCar();
			m_vehicle[m_cnt].setX(x);
			m_vehicle[m_cnt].setY(y);
			m_vehicle[m_cnt].setNumber(base+m_cnt);
			//m_selected = m_cnt;
			m_cnt++;
		}
		else if( m_cnt == SIZE+1)
		{
			m_queue = new QueueBox(5,x,y);
			m_cnt++;
		}
		else
		{
			m_cnt = SIZE+2;
			m_selected = -1;
			for(int i = 0; i< m_vehicle.length; i++)
			{
				m_vehicle[i].m_selected = false;
				if( m_vehicle[i].selected(x,y) )
				{
					m_selected = i;
					m_vehicle[i].m_selected = true;
					break;
				}
			}
		}
		repaint(); 
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if( m_selected >0 )
		{
			//for the Engine
			if( m_vehicle[0].intersected(m_vehicle[m_selected].getRectangle() ) )
			{
				//make the selected RailCar to be a tailer of the engine
				TrainEngine head = (TrainEngine)m_vehicle[0];
				m_vehicle[0].m_next = m_vehicle[m_selected];
				m_vehicle[m_selected].m_pre = m_vehicle[0];
				
				//get the offset which the selected railcars should move
				double dx = head.getX() + head.U14 - m_vehicle[m_selected].getX();
				double dy = head.getY()  - m_vehicle[m_selected].getY();
				
				//move the selected railcar and its linkers 
				Vehicle tmp = m_vehicle[m_selected];
				while(tmp != null )
				{
					tmp.setX( tmp.getX() + (int)dx );
					tmp.setY( tmp.getY() + (int)dy );
					tmp = tmp.m_next;
				}
					
				repaint();
				return;
			}
			//for the RailCar
			for(int i=1; i< m_vehicle.length; i++)
			{
				//when null or itself ship the following steps
				if( i == m_selected || m_vehicle[i] == null)
					continue;
				
				if( m_vehicle[i].intersected(m_vehicle[m_selected].getRectangle() ) )
				{
					if(m_vehicle[i].m_next == null)
					{
						//make the selected railCar to be  a tailer of the engine
						RailCar pre = (RailCar)m_vehicle[i];
						m_vehicle[i].m_next = m_vehicle[m_selected];
						m_vehicle[m_selected].m_pre = m_vehicle[i];
						
						//get the offset which the selected railcars should move
						int dx = pre.getX() + pre.U6 + pre.U05 -m_vehicle[m_selected].getX();
						int dy = pre.getY() - m_vehicle[m_selected].getY();
						
						//move the selected railCar and its linkers
						Vehicle iter = m_vehicle[m_selected];
						while( iter != null )
						{
							//get the index of tmp in m_vehicle
							int index = -1;
							for(int k= 1; k<= SIZE; k++)
							{
								if(m_vehicle[k] == iter)
								{
									index = m_toQue[k-1];
									break;
								}
							}
							
							//fix the coordinate of queue box
							if(index >=0)
							{
								m_queue.m_Boxs[index].m_posx += dx;
								m_queue.m_Boxs[index].m_posy += dy;
							}
							
							iter.setX(iter.getX() + dx);
							iter.setY(iter.getY() + dy);
							iter = iter.m_next;
						}
						repaint();
					}
					break;
				}
			}
		}
		
	}
	
	@Override
	public void paint(Graphics g)
	{

		if (m_bufferImage == null) 
		{
			m_bufferImage = this.createImage(FRAME_WIDTH, FRAME_HEIGHT);
		}
		Graphics gBuffer = m_bufferImage.getGraphics();
		super.paint(gBuffer);
		Graphics2D g2 = (Graphics2D)gBuffer;
		
		for(int i=0; i<m_vehicle.length; i++)
		{
			if(m_vehicle[i] != null )
			{
				m_vehicle[i].draw(g2);
			}
		}
		if( m_queue != null)
			m_queue.draw(g2);
		gBuffer.dispose();
		g.drawImage(m_bufferImage, 0, 0, null);
	}
	
	public  void addFirst()
	{
		//make the selected RailCar to be a tailer of the engine
		if( m_selected > 0)
		{
			Vehicle first = m_vehicle[0].m_next;
			TrainEngine head = (TrainEngine)m_vehicle[0];
			m_vehicle[0].m_next = m_vehicle[m_selected];
			if(m_vehicle[m_selected].m_pre != null )
				m_vehicle[m_selected].m_pre.m_next = null;
			m_vehicle[m_selected].m_pre = m_vehicle[0];
			
			//get the offset which the selected railcars should move
			double dx = head.getX() + head.U14 - m_vehicle[m_selected].getX();
			double dy = head.getY()  - m_vehicle[m_selected].getY();
			
			//move the selected railcar and its linkers 
			Vehicle tmp = m_vehicle[m_selected];
			RailCar last = null;
			while(tmp != null )
			{
				tmp.setX( tmp.getX() + (int)dx );
				tmp.setY( tmp.getY() + (int)dy );
				last = (RailCar)tmp;
				tmp = tmp.m_next;
			}
			if( last != null)
				last.m_next = first;
			if( first != null )
				first.m_pre = last;
			
			
			//move the previous ahead cars to the end 
			dx = last.getX() + last.U6 + last.U05 -first.getX();
			dy = last.getY() - first.getY();
			while(first != null)
			{
				first.setX(first.getX() + (int)dx);
				first.setY(first.getY() + (int)dy);
				first = first.m_next;
			}
			repaint();
		}
		
	}
	public void addLast()
	{
		if(m_vehicle[0].m_next == null)
			addFirst();
		else
		{
			RailCar last = null;
			Vehicle tmp = m_vehicle[0].m_next;
			while( tmp != null )
			{
				last = (RailCar)tmp;
				tmp = tmp.m_next;
			}
			
			//dx,dy are the distance the selected car and its linkers should move
			int dx = last.getX() + last.U6 + last.U05 - m_vehicle[m_selected].getX();
			int dy = last.getY() - m_vehicle[m_selected].getY();
			
			//change the previous and next relation of selected 
			if( m_vehicle[m_selected].m_pre != null)
				m_vehicle[m_selected].m_pre.m_next = null;
			last.m_next = m_vehicle[m_selected];
			m_vehicle[m_selected].m_pre = last;
			
			//move the selected car and its linkers
			tmp = m_vehicle[m_selected];
			while( tmp != null )
			{
				tmp.setX( tmp.getX() + dx );
				tmp.setY( tmp.getY() + dy );
				tmp = tmp.m_next;
			}
			repaint();
		}
		
	}
	public void removeFirst()
	{
		Vehicle head = m_vehicle[0];
		if( head.m_next == null)
			return;
		head = head.m_next;
		if(head.m_next == null )
		{
			m_vehicle[0].m_next = null;
			head.m_pre = null;
			int x = (int)(50 + Math.random() * (this.getWidth() - 50 ));
			int y = (int)(50 + Math.random() * (this.getHeight() - 100 ));
			head.setX(x);
			head.setY(y);
		}
		else
		{
			Vehicle iter = head.m_next;
			int dx = head.getX() - iter.getX();
			int dy = head.getY() - iter.getY();
			m_vehicle[0].m_next = iter;
			iter.m_pre = m_vehicle[0];
			head.m_pre = null;
			head.m_next = null;
			
			int x = (int)(50 + Math.random() * (this.getWidth() - 50 ));
			int y = (int)(50 + Math.random() * (this.getHeight() - 100 ));
			head.setX(x);
			head.setY(y);
			
			while( iter != null )
			{
				iter.setX(iter.getX() + dx);
				iter.setY(iter.getY() + dy);
				iter = iter.m_next;
			}
			
			
		}
	}
	public void removeLast()
	{
		Vehicle head = m_vehicle[0];
		if(head.m_next == null)
			return;
		
		RailCar last = null;
		head = head.m_next;
		while( head != null )
		{
			last = (RailCar)head;
			head = head.m_next;
		}
		
		last.m_pre.m_next = null;
		last.m_pre = null;
		int x = (int)(50 + Math.random() * (this.getWidth() - 50 ));
		int y = (int)(50 + Math.random() * (this.getHeight() - 100 ));
		last.setX(x);
		last.setY(y);
	}
	public void add()
	{
		for(int i=0; i<SIZE; i++)
		{
			if( m_toQue[i] >= 0 )
			{
				int index = m_toQue[i];
				QueueBox.Box box = m_queue.m_Boxs[index];
				m_queue.m_tail.m_next = box;
				
				box.m_posx = m_queue.m_tail.m_posx;
				box.m_posy = m_queue.m_tail.m_posy - (box.m_len + 2);
				m_queue.m_tail = box;
				
				m_toQue[i] = -1;
				break;
			}
		}
	}
	public void remove()
	{
		if( m_queue.m_left == 0)
			return;
		int index = m_queue.m_numBox + 1 - m_queue.m_left;
		QueueBox.Box preHead =m_queue.m_head;
		m_queue.m_head = m_queue.m_head.m_next;
		preHead.m_next = null;
		m_queue.m_left--;
		
		//fix the coordinate of base queue
		QueueBox.Box iterBox = m_queue.m_head;
		while(iterBox != null)
		{
			iterBox.m_posy += (iterBox.m_len + 2);
			iterBox = iterBox.m_next;
		}
		//fix the coordinate of playing on the car
		preHead.m_posx = m_vehicle[index].getX();
		preHead.m_posy = m_vehicle[index].getY() - preHead.m_len + RailCar.UNIT;
		for(int i=0; i<m_queue.m_Boxs.length; i++)
		{
			if(preHead == m_queue.m_Boxs[i])
			{
				m_toQue[index-1] = i;
				break;
			}
		}
	}
}
