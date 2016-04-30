import javax.swing.*;
import java.awt.event.*;
import java.io.*;
public class Save implements ActionListener
{
        private File file;
        private JTextArea editor;
        
        public Save(File f, JTextArea e)
        {
                file = f;
                editor= e;
        }
        public void actionPerformed(ActionEvent e)
        {
                try
                {
                        PrintStream printin = new PrintStream(file);
                        printin.print(editor.getText());
                }
                catch (FileNotFoundException ex)
                {
                        ex.printStackTrace();
                }
                
        }
}
