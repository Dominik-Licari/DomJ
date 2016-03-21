import javax.swing.*;
import java.awt.*;
import java.io.*;
public class DomJ extends JFrame
{
        private TextArea editor;
        private Button run;
        private Button compile;
        private Button save;
        private File workingDirectory;
        private File currentFile;
        private File configFile;
        private Scanner conf;
        public DomJ()
        {
               super("DomJ, the best IDE");
               editor = new TextArea();
               try
               {
                       workingDirectory = new Paths(System.getProperty("user.dir"));
                       configFile = new File(workindDirectory.getAbsolutePath() + "conf");
                       conf = new Scanner(configFile);
                       currentFile = new File(conf.nextLine());
                       save = new Button(new DomJButtons(0, editor));
               }
               catch(FileNotFoundExeption e)
               {
                       System.err.println("ERROR: " e);
               }
               
        }
        
        public static void main(String[] args)
        {
                new DomJ();
        }
        
}


