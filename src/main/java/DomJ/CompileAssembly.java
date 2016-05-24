package DomJ;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.tools.*;
import java.util.*;
public class CompileAssembly implements Compile, ActionListener
{
        private String fileName;
        private JTextArea editor;
        
        public CompileAssembly(String fn, JTextArea e)
        {
                fileName = fn;
                editor= e;
        }
        public CompileAssembly(String fn, String e)
        {
                fileName = fn;
                editor = new JTextArea(e);
        }
        public void actionPerformed(ActionEvent e)
        {
                new Save(new File(fileName), editor).actionPerformed(e);
                try
                {
                        String[] command = new String[]{"gcc", "-m32", 
                                                        "-nostdlib", "-static", "-o",
                                                        fileName.replace(".S", ""),  fileName};
                        //System.out.println(Arrays.toString(command));
                        Process p = Runtime.getRuntime().exec(command);
                        p.waitFor();
                        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        BufferedReader err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                        String line = "";
                        while ((line = in.readLine()) != null)
                        {
                                System.out.println(line);
                        }
                        while ((line = err.readLine()) != null)
                        {
                                System.out.println(line);
                        }
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }
        
}
