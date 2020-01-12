import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;


public class Painter extends JFrame
{
   private final JLabel statusBar;                                           			//下面訊息行
   private JLabel drawtool;															//工具列第一行 "繪圖工具"
   private JComboBox<String> pentool;													//工具列第二行 筆刷
   private String[] names = {"筆刷","直線","橢圓形","矩形","圓角矩形"};					//工具列第二行 其他選項
   private JLabel pensize;																//工具列第三行 "筆刷大小"
   private JRadioButton smallsize;														//工具列第四行   小筆刷
   private JRadioButton middlesize;													//工具列第五行   中筆刷
   private JRadioButton bigsize;														//工具列第六行   大筆刷
   private ButtonGroup buttongroup;													//工具列第四五六行   筆刷群組
   private JCheckBox full;																//工具列第七行 填滿
   private JButton frontbutton;														//工具列第八行 前景色														//工具列第九行 背景色
   private JButton backbutton;															//工具列第九行 背景色
   private JButton clearbutton;														//工具列第十行 清除
   private ArrayList<Point> points = new ArrayList<>(); 								//畫布
   private Color color1 ;																//前景色
   private Color color2 ;																//背景色
   private int kind ,fullnumber,sizenumber;
   private int x1,x2,y1,y2;
   private int size=5;
   private List panellist;                                                              //紀錄圖形
 
   public Painter()                                                          			// Painter 建構程式
   {
      super("小畫家");																	//程式名字
      
      statusBar = new JLabel(); 						 
      add(statusBar, BorderLayout.SOUTH);                                   			// add label to JFrame
      
      
      MouseHandler handler = new MouseHandler(); 										//建立畫布和抓取滑鼠位置
      PaintPanel paintpanel = new PaintPanel();
      add(paintpanel,BorderLayout.CENTER);
      paintpanel.setBackground(Color.white);
      paintpanel.addMouseListener(handler);
      paintpanel.addMouseMotionListener(handler);
      
     
      ToolPanel toolpanel = new ToolPanel();											//工具列
      add(toolpanel,BorderLayout.WEST);
      
      pentool.addActionListener(new ActionListener()									//工具選單功能
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
      
      full.addItemListener(new ItemListener()											//填滿功能
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
      
      
      frontbutton.addActionListener(new ActionListener()								//前景色功能
    		  {
    	  		@Override
    	  		public void actionPerformed(ActionEvent event)
    	  		{
    	  			color1 = JColorChooser.showDialog(toolpanel,"Choose a color",color1);
		  			frontbutton.setBackground(color1);
    	  		}
    		  });
      
      backbutton.addActionListener(new ActionListener()									//背景色功能
    		  {
    	  		@Override
    	  		public void actionPerformed(ActionEvent event)
    	  		{
    	  			color2 = JColorChooser.showDialog(toolpanel,"Choose a color",color2);
		  			paintpanel.setBackground(color2);
		  			backbutton.setBackground(color2);
    	  		}
    		  });
      
      clearbutton.addActionListener(new ActionListener()								//清除畫面功能
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

   private class MouseHandler implements MouseListener,              					//滑鼠位置
      MouseMotionListener 
   {
    
      @Override
      public void mouseClicked(MouseEvent event)
      {
         statusBar.setText(String.format("游標位置: (%d, %d)", 
            event.getX(), event.getY()));
      } 

      
      @Override
      public void mousePressed(MouseEvent event)
      {
         statusBar.setText(String.format("游標位置: (%d, %d)", 
            event.getX(), event.getY()));
         	x1= event.getX();
         	y1= event.getY();
      }

      
      @Override
      public void mouseReleased(MouseEvent event)
      {
         statusBar.setText(String.format("游標位置: (%d, %d)", 
            event.getX(), event.getY()));
      }

      
      @Override
      public void mouseEntered(MouseEvent event)
      {
         statusBar.setText(String.format("游標位置: (%d, %d)", 
            event.getX(), event.getY()));
      }

     
      @Override
      public void mouseExited(MouseEvent event)
      {
         statusBar.setText("滑鼠在畫布外面");
         
      }

      
      @Override
      public void mouseDragged(MouseEvent event)
      {
         statusBar.setText(String.format("游標位置: (%d, %d)", 
            event.getX(), event.getY()));
         	x2 = event.getX(); 
         	y2 = event.getY();
      } 

      
      @Override
      public void mouseMoved(MouseEvent event)
      {
         statusBar.setText(String.format("游標位置: (%d, %d)", 
            event.getX(), event.getY()));
      } 
   } 

   public class PaintPanel extends JPanel                            					//畫板
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
      public void paintComponent(Graphics g)								//畫圖 
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

   public class ToolPanel extends JPanel												//工具列
   {
	  
	   
	   MessageListener listener = new MessageListener();
	   
	   
	   public  ToolPanel()																		
	   {
		   setLayout(new GridLayout(10,1));														//工具列第一行 "繪圖工具"
		   drawtool = new JLabel("[繪圖工具]");						
		   add(drawtool);
	   
		   pentool = new JComboBox<String>(names);												//工具列第二行 筆刷
		   add(pentool);
		   pentool.addItemListener(new ComboBoxButtomListener());
		   
		   pensize = new JLabel("[筆刷大小]");													//工具列第三行 "筆刷大小"
		   add(pensize);
		   
		   smallsize = new JRadioButton("小",true);												//工具列四五六行"小中大"
		   middlesize = new JRadioButton("中",false);
		   bigsize = new JRadioButton("大",false);
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
		   
		   full = new JCheckBox("填滿");                                                        //工具列第七行 填滿
		   add(full);
		   full.addActionListener(listener);
	       
		   
		   frontbutton = new JButton("前景色");															//工具列八九十行
		   backbutton = new JButton("背景色");
		   clearbutton = new JButton("清除畫面");
		   add(frontbutton);
		   add(backbutton);
		   add(clearbutton);
		   frontbutton.addActionListener(listener);
		   backbutton.addActionListener(listener);
	       clearbutton.addActionListener(listener);
	       
	       
	   }
   }

  
   
   class MessageListener implements ActionListener                                        //除了ComboBoxbutton的跳出訊息
   {
	   PaintPanel p = new PaintPanel();
	   
       public void actionPerformed(ActionEvent event) 
       {            
    	   
    	   JOptionPane.showMessageDialog(null, "你點選了  :  " + event.getActionCommand(),"訊息", JOptionPane.INFORMATION_MESSAGE);
       }
   }
   
   class ComboBoxButtomListener implements ItemListener 										//ComboBoxbutton的訊息
   {
       public void itemStateChanged(ItemEvent event) 
       {
           String string = (String) event.getItem();
           if (event.getStateChange() == ItemEvent.SELECTED) 
           {
               JOptionPane.showMessageDialog(null, "你點選了  :  " + string, "訊息",JOptionPane.INFORMATION_MESSAGE);
           }
       }
   }
} 
