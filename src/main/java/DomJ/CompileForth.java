package DomJ;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.tools.*;
import java.util.*;
public class CompileForth implements Compile, ActionListener
{
        private String fileName;
        private JTextArea editor;
        
        public CompileForth(String fn, JTextArea e)
        {
                fileName = fn;
                editor= e;
        }
        public CompileForth(String fn, String e)
        {
                fileName = fn;
                editor = new JTextArea(e);
        }
        public void actionPerformed(ActionEvent e)
        {
                new Save(new File(fileName), editor).actionPerformed(e);
        }
        
}
