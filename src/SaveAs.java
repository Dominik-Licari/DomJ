import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class SaveAs implements ActionListener
{
        private JTextArea editor;
        
        public SaveAs(JTextArea e)
        {
                editor= e;
        }
        public void actionPerformed(ActionEvent e)
        {
                JFrame p = new JFrame();
                JTextField prompt = new JTextField();
                p.setPreferredSize(new Dimension(100,70));
                p.add(prompt);
                p.pack();
                p.setVisible(true);
                prompt.addKeyListener(new KeyAdapter()
                {
                        public void keyPressed(KeyEvent e)
                        {
                                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                                {
                                        new Save(new File(prompt.getText()), editor);
                                        System.out.println(prompt.getText());
                                        p.dispose();
                                }
                        }
                });                
        }
}
