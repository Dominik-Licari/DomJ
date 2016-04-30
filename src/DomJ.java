import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
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
                setLayout(new BorderLayout());
                JPanel menu = new JPanel(new FlowLayout());
                add(menu, BorderLayout.NORTH);
                workingDirectory = new File(System.getProperty("user.dir"));
                //configFile = new File(workingDirectory.getAbsolutePath() + "/conf");
                //conf = new Scanner(configFile);
                currentFile = new File(fileName);
                editor = new JTextArea(5, 20);
                JScrollPane sp = new JScrollPane(editor);
                try 
                {
                        Scanner in = new Scanner(currentFile);
                        StringBuilder text = new StringBuilder();
                        while (in.hasNextLine())
                        {
                                text.append(in.nextLine());
                                text.append("\n");
                        }
                        editor.append(text.toString());
                       
                }
                catch (FileNotFoundException e)
                {
                }
                save = new Button("Save");
                save.addActionListener(new Save(currentFile, editor));
                menu.add(save);
                compile = new Button("Compile");
                compile.addActionListener(new Compile(fileName, editor));
                menu.add(compile);
                //run = new Button("Run");
                //run.addActionListener(new Run(fileName, editor));
                //menu.add(run);
                add(editor, BorderLayout.CENTER);
                pack();
                setVisible(true);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
               
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
                                        default:
                                                break;
                                        }
                                if (args.length > 1)
                                        new DomJ(args[0]);
                        }
                        else
                                new DomJ(args[0]);
                }
                else
                        System.out.println("-h for usage");
        }
}

