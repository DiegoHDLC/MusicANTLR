package vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class TextHighLight
{
    private JTextArea tarea;
    private JComboBox cbox;
    private JTextField lineField;
    private String[] colourNames = {"RED", "ORANGE", "CYAN"};

    private Highlighter.HighlightPainter painter;

    private void createAndDisplayGUI()
    {
        final JFrame frame = new JFrame("Text HIGHLIGHT");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5), "Highlighter JTextArea"));

        tarea = new JTextArea(10, 10);
        JScrollPane scrollPane = new JScrollPane(tarea);
        contentPane.add(scrollPane);

        JButton button = new JButton("HIGHLIGHT TEXT");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                int selection = JOptionPane.showConfirmDialog(
                        frame, getOptionPanel(), "Highlighting Options : ", JOptionPane.OK_CANCEL_OPTION
                                                , JOptionPane.PLAIN_MESSAGE);
                if (selection == JOptionPane.OK_OPTION)                             
                {
                	
                	new Thread() {
                		public void run() {
                			
                		
                	for(int i = 0; i <5; i++) {
    
	                    System.out.println("OK Selected");
	                    try {
	                    	
							Thread.sleep(1000);
							tarea.getHighlighter().removeAllHighlights();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                    int lineNumber = i/*Integer.parseInt(lineField.getText().trim())*/;
	                    try
	                    {
	                        int startIndex = tarea.getLineStartOffset(lineNumber);
	                        int endIndex = tarea.getLineEndOffset(lineNumber);
	                        System.out.println("start Index: "+startIndex);
	                        System.out.println("end Index: "+endIndex);
	                        String colour = (String) cbox.getSelectedItem();
	
	                        if (colour == colourNames[0])
	                        {
	                        	
	                        		//System.out.println("RED Colour");
		                            painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
		                            
		                            tarea.getHighlighter().addHighlight(startIndex, endIndex, painter);
		                            //painter = new DefaultHighlighter.DefaultHighlightPainter(Color.white);
		                            //tarea.getHighlighter().addHighlight(startIndex, endIndex, painter);
		                            
		                            //tarea.repaint();
	                        	
	                            
	                        	
	                        }
	                        else if (colour == colourNames[1])
	                        {
	                            System.out.println("ORANGE Colour");
	                            painter = new DefaultHighlighter.DefaultHighlightPainter(Color.ORANGE);
	                            tarea.getHighlighter().addHighlight(startIndex, endIndex, painter);
	                            //tarea.repaint();
	                        }
	                        else if (colour == colourNames[2])
	                        {
	                            System.out.println("CYAN Colour");
	                            painter = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
	                            tarea.getHighlighter().addHighlight(startIndex, endIndex, painter);
	                            //tarea.repaint();
	                        }
	                    }
	                    
	                    catch(BadLocationException ble)
	                    {
	                    	//tarea.repaint();
	                        ble.printStackTrace();
	                    }
	                    
	                    //tarea.repaint();
	                    //tarea.revalidate();
	                    
                	}
                		}
                    }.start();
                	
                }
                else if (selection == JOptionPane.CANCEL_OPTION)
                {
                    System.out.println("CANCEL Selected");
                }
                else if (selection == JOptionPane.CLOSED_OPTION)
                {
                    System.out.println("JOptionPane closed deliberately.");
                }
                
            }
        });

        frame.add(contentPane, BorderLayout.CENTER);
        frame.add(button, BorderLayout.PAGE_END);

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private JPanel getOptionPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 5, 5));

        JLabel lineNumberLabel = new JLabel("Enter Line Number : ");
        lineField = new JTextField(10);

        JLabel colourLabel = new JLabel("Select One Colour : ");
        cbox = new JComboBox(colourNames);

        panel.add(lineNumberLabel);
        panel.add(lineField);
        panel.add(colourLabel);
        panel.add(cbox);

        return panel;
    }

    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new TextHighLight().createAndDisplayGUI();
            }
        });
    }
}