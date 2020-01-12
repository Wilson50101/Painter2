import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;


public class Painter extends JFrame
{
   private final JLabel statusBar;                                           			//�U���T����
   private JLabel drawtool;															//�u��C�Ĥ@�� "ø�Ϥu��"
   private JComboBox<String> pentool;													//�u��C�ĤG�� ����
   private String[] names = {"����","���u","����","�x��","�ꨤ�x��"};					//�u��C�ĤG�� ��L�ﶵ
   private JLabel pensize;																//�u��C�ĤT�� "����j�p"
   private JRadioButton smallsize;														//�u��C�ĥ|��   �p����
   private JRadioButton middlesize;													//�u��C�Ĥ���   ������
   private JRadioButton bigsize;														//�u��C�Ĥ���   �j����
   private ButtonGroup buttongroup;													//�u��C�ĥ|������   ����s��
   private JCheckBox full;																//�u��C�ĤC�� ��
   private JButton frontbutton;														//�u��C�ĤK�� �e����														//�u��C�ĤE�� �I����
   private JButton backbutton;															//�u��C�ĤE�� �I����
   private JButton clearbutton;														//�u��C�ĤQ�� �M��
   private ArrayList<Point> points = new ArrayList<>(); 								//�e��
   private Color color1 ;																//�e����
   private Color color2 ;																//�I����
   private int kind ,fullnumber,sizenumber;
   private int x1,x2,y1,y2;
   private int size=5;
   private List panellist;                                                              //�����ϧ�
 
   public Painter()                                                          			// Painter �غc�{��
   {
      super("�p�e�a");																	//�{���W�r
      
      statusBar = new JLabel(); 						 
      add(statusBar, BorderLayout.SOUTH);                                   			// add label to JFrame
      
      
      MouseHandler handler = new MouseHandler(); 										//�إߵe���M����ƹ���m
      PaintPanel paintpanel = new PaintPanel();
      add(paintpanel,BorderLayout.CENTER);
      paintpanel.setBackground(Color.white);
      paintpanel.addMouseListener(handler);
      paintpanel.addMouseMotionListener(handler);
      
     
      ToolPanel toolpanel = new ToolPanel();											//�u��C
      add(toolpanel,BorderLayout.WEST);
      
      pentool.addActionListener(new ActionListener()									//�u����\��
    		  {
    	  		@Override
    	  		public void actionPerformed(ActionEvent event)
    	  		{
    	  			kind = pentool.getSelectedIndex();
    	  		}	
    		  });
      smallsize.addActionListener(new ActionListener()
    	{
    	  	@Override
    	  	public void actionPerformed(ActionEvent event)
    	  	{
    	  		sizenumber=1;
    	  	}
    	});
      middlesize.addActionListener(new ActionListener()
	  {
  		@Override
  		public void actionPerformed(ActionEvent event)
  		{
  			sizenumber=2;
  		}
	  });
      bigsize.addActionListener(new ActionListener()
	  {
  		@Override
  		public void actionPerformed(ActionEvent event)
  		{
  			sizenumber=3;
  		}
	  });
      
      full.addItemListener(new ItemListener()											//�񺡥\��
    		  {
    	  		public void itemStateChanged(ItemEvent event)
    	  		{
    	  			if (event.getStateChange()== ItemEvent.SELECTED)
    	  			{
    	  				fullnumber = 1;
    	  			}
    	  			else
    	  			{
    	  				fullnumber = 0;
    	  			}
    	  		}
    		  });
      
      
      frontbutton.addActionListener(new ActionListener()								//�e����\��
    		  {
    	  		@Override
    	  		public void actionPerformed(ActionEvent event)
    	  		{
    	  			color1 = JColorChooser.showDialog(toolpanel,"Choose a color",color1);
		  			frontbutton.setBackground(color1);
    	  		}
    		  });
      
      backbutton.addActionListener(new ActionListener()									//�I����\��
    		  {
    	  		@Override
    	  		public void actionPerformed(ActionEvent event)
    	  		{
    	  			color2 = JColorChooser.showDialog(toolpanel,"Choose a color",color2);
		  			paintpanel.setBackground(color2);
		  			backbutton.setBackground(color2);
    	  		}
    		  });
      
      clearbutton.addActionListener(new ActionListener()								//�M���e���\��
    		  {
    	  		@Override
    	  		public void actionPerformed(ActionEvent event)
    	  		{
    	  			points.clear();
    	  			paintpanel.setBackground(Color.white);
    	  			backbutton.setBackground(null);
    	  			repaint();
    	  			x1=-5;
    	  			x2=-5;
    	  			y1=-5;
    	  			y2=-5;
    	  		}
    		  });
      	
   } 

   private class MouseHandler implements MouseListener,              					//�ƹ���m
      MouseMotionListener 
   {
    
      @Override
      public void mouseClicked(MouseEvent event)
      {
         statusBar.setText(String.format("��Ц�m: (%d, %d)", 
            event.getX(), event.getY()));
      } 

      
      @Override
      public void mousePressed(MouseEvent event)
      {
         statusBar.setText(String.format("��Ц�m: (%d, %d)", 
            event.getX(), event.getY()));
         	x1= event.getX();
         	y1= event.getY();
      }

      
      @Override
      public void mouseReleased(MouseEvent event)
      {
         statusBar.setText(String.format("��Ц�m: (%d, %d)", 
            event.getX(), event.getY()));
      }

      
      @Override
      public void mouseEntered(MouseEvent event)
      {
         statusBar.setText(String.format("��Ц�m: (%d, %d)", 
            event.getX(), event.getY()));
      }

     
      @Override
      public void mouseExited(MouseEvent event)
      {
         statusBar.setText("�ƹ��b�e���~��");
         
      }

      
      @Override
      public void mouseDragged(MouseEvent event)
      {
         statusBar.setText(String.format("��Ц�m: (%d, %d)", 
            event.getX(), event.getY()));
         	x2 = event.getX(); 
         	y2 = event.getY();
      } 

      
      @Override
      public void mouseMoved(MouseEvent event)
      {
         statusBar.setText(String.format("��Ц�m: (%d, %d)", 
            event.getX(), event.getY()));
      } 
   } 

   public class PaintPanel extends JPanel                            					//�e�O
   {
	  
      public PaintPanel()
      {
         addMouseMotionListener(
            new MouseMotionAdapter() 
            {  
               
               @Override
               public void mouseDragged(MouseEvent event)
               {
                  points.add(event.getPoint());
                  repaint(); 
               } 
              
            } ); 
      }

      
      @Override                                                     
      public void paintComponent(Graphics g)								//�e�� 
      {
         super.paintComponent(g);
         Graphics2D g2d = (Graphics2D) g;
         g2d.setColor(color1);
         
         if(sizenumber == 2)
         {
	         g2d.setStroke(new BasicStroke(2*size));
	         for (Point point : points)
	         
	         if(kind == 0)
	         {
	        	 g2d.fillOval(point.x, point.y, 2*size, 2*size);
	        	 panellist.add(null, point.x);
	        	 panellist.add(null,point.y);
	         }
	         if(kind == 1)
	         {
	        	 if(fullnumber == 0)
	        	 {
	        		g2d.drawLine(x1, y1, x2, y2); 
	        	 }
	        	 else 
	        	 {
	        		 g2d.drawLine(x1, y1, x2, y2); 
	        	 }
	         }
	         if(kind == 2)
	         {
	        	 if(fullnumber == 0)
	        	 {
	        		g2d.drawOval(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2)); 
	        	 }
	        	 else 
	        	 {
	        		 g2d.fillOval(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2)); 
	        	 }
	         }
	         if(kind == 3)
	         {
	        	 if(fullnumber == 0)
	        	 {
	        		g2d.drawRect(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2)); 
	        	 }
	        	 else if(fullnumber == 1)
	        	 {
	        		 g2d.fillRect(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2)); 
	        	 }
	         }
	         if(kind == 4)
	         {
	        	 if(fullnumber == 0)
	        	 {
	        		g2d.drawRoundRect(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2), 30,30); 
	        	 }
	        	 else if(fullnumber == 1)
	        	 {
	        		g2d.fillRoundRect(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2), 30, 30); 
	        	 }
	         }
         
         }
         if(sizenumber == 3)
         {
        	 g2d.setStroke(new BasicStroke(3*size));
	         for (Point point : points)
	         
	         if(kind == 0)
	         {
	        	 g2d.fillOval(point.x, point.y, 3*size, 3*size);
	         }
	         if(kind == 1)
	         {
	        	 if(fullnumber == 0)
	        	 {
	        		g2d.drawLine(x1, y1, x2, y2); 
	        	 }
	        	 else 
	        	 {
	        		 g2d.drawLine(x1, y1, x2, y2); 
	        	 }
	         }
	         if(kind == 2)
	         {
	        	 if(fullnumber == 0)
	        	 {
	        		g2d.drawOval(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2)); 
	        	 }
	        	 else 
	        	 {
	        		 g2d.fillOval(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2)); 
	        	 }
	         }
	         if(kind == 3)
	         {
	        	 if(fullnumber == 0)
	        	 {
	        		g2d.drawRect(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2)); 
	        	 }
	        	 else if(fullnumber == 1)
	        	 {
	        		 g2d.fillRect(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2)); 
	        	 }
	         }
	         if(kind == 4)
	         {
	        	 if(fullnumber == 0)
	        	 {
	        		g2d.drawRoundRect(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2), 30,30); 
	        	 }
	        	 else if(fullnumber == 1)
	        	 {
	        		g2d.fillRoundRect(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2), 30, 30); 
	        	 }
	         }
         }
         else
         {
        	 g2d.setStroke(new BasicStroke(size));
	         for (Point point : points)
	         
	         if(kind == 0)
	         {
	        	 g2d.fillOval(point.x, point.y, size, size);
	         }
	         if(kind == 1)
	         {
	        	 if(fullnumber == 0)
	        	 {
	        		g2d.drawLine(x1, y1, x2, y2); 
	        	 }
	        	 else 
	        	 {
	        		 g2d.drawLine(x1, y1, x2, y2); 
	        	 }
	         }
	         if(kind == 2)
	         {
	        	 if(fullnumber == 0)
	        	 {
	        		g2d.drawOval(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2)); 
	        	 }
	        	 else 
	        	 {
	        		 g2d.fillOval(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2)); 
	        	 }
	         }
	         if(kind == 3)
	         {
	        	 if(fullnumber == 0)
	        	 {
	        		g2d.drawRect(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2)); 
	        	 }
	        	 else if(fullnumber == 1)
	        	 {
	        		 g2d.fillRect(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2)); 
	        	 }
	         }
	         if(kind == 4)
	         {
	        	 if(fullnumber == 0)
	        	 {
	        		g2d.drawRoundRect(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2), 30,30); 
	        	 }
	        	 else if(fullnumber == 1)
	        	 {
	        		g2d.fillRoundRect(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1-x2), Math.abs(y1-y2), 30, 30); 
	        	 }
	         }
         }
      } 

   } 

   public class ToolPanel extends JPanel												//�u��C
   {
	  
	   
	   MessageListener listener = new MessageListener();
	   
	   
	   public  ToolPanel()																		
	   {
		   setLayout(new GridLayout(10,1));														//�u��C�Ĥ@�� "ø�Ϥu��"
		   drawtool = new JLabel("[ø�Ϥu��]");						
		   add(drawtool);
	   
		   pentool = new JComboBox<String>(names);												//�u��C�ĤG�� ����
		   add(pentool);
		   pentool.addItemListener(new ComboBoxButtomListener());
		   
		   pensize = new JLabel("[����j�p]");													//�u��C�ĤT�� "����j�p"
		   add(pensize);
		   
		   smallsize = new JRadioButton("�p",true);												//�u��C�|������"�p���j"
		   middlesize = new JRadioButton("��",false);
		   bigsize = new JRadioButton("�j",false);
		   buttongroup = new ButtonGroup();
		   add(smallsize);
		   add(middlesize);
		   add(bigsize);
		   buttongroup.add(smallsize);
		   buttongroup.add(middlesize);
		   buttongroup.add(bigsize);
		   smallsize.addActionListener(listener);
	       middlesize.addActionListener(listener);
	       bigsize.addActionListener(listener);
		   
		   full = new JCheckBox("��");                                                        //�u��C�ĤC�� ��
		   add(full);
		   full.addActionListener(listener);
	       
		   
		   frontbutton = new JButton("�e����");															//�u��C�K�E�Q��
		   backbutton = new JButton("�I����");
		   clearbutton = new JButton("�M���e��");
		   add(frontbutton);
		   add(backbutton);
		   add(clearbutton);
		   frontbutton.addActionListener(listener);
		   backbutton.addActionListener(listener);
	       clearbutton.addActionListener(listener);
	       
	       
	   }
   }

  
   
   class MessageListener implements ActionListener                                        //���FComboBoxbutton�����X�T��
   {
	   PaintPanel p = new PaintPanel();
	   
       public void actionPerformed(ActionEvent event) 
       {            
    	   
    	   JOptionPane.showMessageDialog(null, "�A�I��F  :  " + event.getActionCommand(),"�T��", JOptionPane.INFORMATION_MESSAGE);
       }
   }
   
   class ComboBoxButtomListener implements ItemListener 										//ComboBoxbutton���T��
   {
       public void itemStateChanged(ItemEvent event) 
       {
           String string = (String) event.getItem();
           if (event.getStateChange() == ItemEvent.SELECTED) 
           {
               JOptionPane.showMessageDialog(null, "�A�I��F  :  " + string, "�T��",JOptionPane.INFORMATION_MESSAGE);
           }
       }
   }
} 
