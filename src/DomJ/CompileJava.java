package DomJ;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.tools.*;
import java.util.*;
public class CompileJava implements Compile, ActionListener
{
        private String fileName;
        private JTextArea editor;
        
        public CompileJava(String fn, JTextArea e)
        {
                fileName = fn;
                editor= e;
        }
        public CompileJava(String fn, String e)
        {
                fileName = fn;
                editor = new JTextArea(e);
        }
        public void actionPerformed(ActionEvent e)
        {
                new Save(new File(fileName), editor).actionPerformed(e);
                try
                {
                        Scanner in = new Scanner(new File(fileName));
                        while (in.hasNextLine())
                        {
                                System.out.println(in.nextLine());
                        }
                                              
                        JavaCompiler comp = ToolProvider.getSystemJavaCompiler();
                        DiagnosticCollector<JavaFileObject> diag = new DiagnosticCollector<JavaFileObject>();
                        StandardJavaFileManager fm = comp.getStandardFileManager(diag, null, null);
                        Iterable<? extends JavaFileObject> compU = fm.getJavaFileObjects(fileName);
                        JavaCompiler.CompilationTask task = comp.getTask(null, fm, diag, null, null, compU);
                        task.call();
                        fm.close();
                        if (checkSuccess())
                                System.out.println("^ happily compiles");
                        else
                                System.out.println("^ refuses to compile");
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
        
        public boolean checkSuccess()
        {
                return new File(fileName.replace("java", "class")).exists();
        }
}
