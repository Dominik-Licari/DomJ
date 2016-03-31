import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
public class DomJ extends JFrame
{
        private JTextArea editor;
        private Button run;
        private Button compile;
        private Button save;
        private File workingDirectory;
        private File currentFile;
        private File configFile;
        private Scanner conf;
        public DomJ(String fileName)
        {
               super("DomJ, the best IDE");
               try
               {
                       workingDirectory = new File(System.getProperty("user.dir"));
                       configFile = new File(workingDirectory.getAbsolutePath() + "conf");
                       conf = new Scanner(configFile);
                       currentFile = new File(fileName);
                       save = new Button("Save");
                       save.addActionListener(new DomJButtons(0, editor));
                       editor = new JTextArea();
                       add(save);
                       add(editor);
                       
               }
               catch(FileNotFoundException e)
               {
                       e.printStackTrace();
                       System.err.println("The file was not found");
               }
               
        }
        
        public static void main(String[] args)
        {
                if (args.length > 0)
                {
                        if (args[0].charAt(0) == '-')
                        {
                                for (char cur : args[0].toCharArray())
                                        switch (cur)
                                        {
                                        case '-':
                                                break;
                                        case 'h':
                                                System.out.println("Usage:\nDomJ [-h] fileName");
                                                break;
                                        }
                                if (args.length > 1)
                                        new DomJ(args[0]);
                        }
                        else
                                new DomJ(args[0]);
                }
                else
                        System.out.println("-h or for usage");
        }
}

