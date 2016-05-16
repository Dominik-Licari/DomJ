package DomJ;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.tools.*;
import java.util.*;
import java.net.*;
import java.lang.reflect.Method;

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
                new CompileJava(fileName, editor);
                try
                {
                        //URL[] urls = { new File(fileName).toURI().toURL() };
                        //URLClassLoader cl = URLClassLoader.newInstance(urls);
                        String fN = fileName.replace(".java", "").replace("/", ".");
                        Class classy = Class.forName(fN);
                        Method methodical = classy.getMethod("main", String[].class);
                        String[] args = null;
                        methodical.invoke(null, (Object) args);
                        
                        
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
                
                
        }
}
