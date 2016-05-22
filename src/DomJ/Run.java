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
                new CompileJava(fileName, editor).actionPerformed(e);
                try
                {
                        String fN = fileName.replace(".java", "").replace("/", ".");
                        Class classy = new Reloader().loadClass(fN).newInstance().getClass();
                        Method methodical = classy.getMethod("main", String[].class);
                        String[] args = null;
                        methodical.invoke(null, (Object) args);
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
                
                
        }
        
        private class Reloader extends ClassLoader
        {
                @Override 
                public Class<?> loadClass(String s)
                {
                        return findClass(s);
                }

                @Override
                public Class<?> findClass(String s)
                {
                        try
                        {
                                byte[] bytes = loadClassData(s);
                                return defineClass(s, bytes, 0, bytes.length);
                        }
                        catch (IOException ioe)
                        {
                                try 
                                {
                                        return super.loadClass(s);
                                }
                                catch (ClassNotFoundException cnfe)
                                {}
                                ioe.printStackTrace();
                                return null;
                        }
                }
                
                private byte[] loadClassData(String className) throws IOException
                {
                        File f = new File("" + className.replaceAll("\\.", "/") + ".class");
                        int size = (int) f.length();
                        byte[] buff = new byte[size];
                        FileInputStream fis = new FileInputStream(f);
                        DataInputStream dis = new DataInputStream(fis);
                        dis.readFully(buff);
                        dis.close();
                        return buff;
                }
                
        }
        
}
