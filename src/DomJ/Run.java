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
                
                char[] alph ="abcdefghijklmnopqrstuvwxyz".toCharArray(); 
                StringBuilder rand = new StringBuilder();
                for (int i = 0; i < 8; i++)
                {
                        rand.append(alph[(int)(Math.random() * alph.length)]);
                }
                
                String fileNameRun =  "Tmp/" + rand.toString() + ".java";
                
                Scanner text = new Scanner(editor.getText());
                text.nextLine();text.nextLine();
                StringBuilder code = new StringBuilder();
                code.append("package Tmp;\npublic class " + rand.toString() + ";\n");
                while (text.hasNextLine())
                        code.append(text.nextLine() + "\n");
                
                CompileJava cJ = new CompileJava(fileNameRun, code.toString());
                cJ.actionPerformed(e);
                


                if (cJ.checkSuccess())
                        try
                        {
                                String fN = fileNameRun.replace(".java", "").replace("/", ".");
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
