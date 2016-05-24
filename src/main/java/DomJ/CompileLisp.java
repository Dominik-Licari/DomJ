package DomJ;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.tools.*;
import java.util.*;
public class CompileLisp implements Compile, ActionListener
{
        private String fileName;
        private JTextArea editor;
        
        public CompileLisp(String fn, JTextArea e)
        {
                fileName = fn;
                editor= e;
        }
        public CompileLisp(String fn, String e)
        {
                fileName = fn;
                editor = new JTextArea(e);
        }
        public void actionPerformed(ActionEvent e)
        {
                new Save(new File(fileName), editor).actionPerformed(e);
        }
        
}
