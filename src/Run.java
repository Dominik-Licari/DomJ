import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.tools.*;
import java.util.*;
public class Run implements ActionListener
{
        private String fileName;
        private JTextArea editor;
        
        public Run(String fn, JTextArea e)
        {
                fileName = fn;
                editor= e;
        }
        public void actionPerformed(ActionEvent e)
        {
                new Compile(fileName, editor);
                try
                {
                        Process p = new ProcessBuilder("gnome-terminal", "-x", "java", fileName.replace(".java", "")).start();
                }
                catch (FileNotFoundException ex)
                {
                        ex.printStackTrace();
                }
                catch (IOException ex)
                {
                        ex.printStackTrace();
                }
                
                
        }
}
