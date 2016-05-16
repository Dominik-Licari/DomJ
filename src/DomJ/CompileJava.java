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
        public void actionPerformed(ActionEvent e)
        {
                new Save(new File(fileName), editor);
                try
                {
                        Scanner in = new Scanner(fileName);
                        JavaCompiler comp = ToolProvider.getSystemJavaCompiler();
                        DiagnosticCollector<JavaFileObject> diag = new DiagnosticCollector<JavaFileObject>();
                        StandardJavaFileManager fm = comp.getStandardFileManager(diag, null, null);
                        Iterable<? extends JavaFileObject> compU = fm.getJavaFileObjects(fileName);
                        JavaCompiler.CompilationTask task = comp.getTask(null, fm, diag, null, null, compU);
                        task.call();
                        fm.close();
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
