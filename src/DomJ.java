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
        private Button saveAs;
        private Button open;
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
                saveAs = new Button("SaveAs");
                saveAs.addActionListener(new SaveAs(editor));
                menu.add(saveAs);
                open = new Button("Open");
                open.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                                JFrame p = new JFrame();
                                JTextField prompt = new JTextField();
                                p.setPreferredSize(new Dimension(100,70));
                                p.add(prompt);
                                p.pack();
                                p.setVisible(true);
                                prompt.addKeyListener(new KeyAdapter()
                                {
                                        public void keyPressed(KeyEvent e)
                                        {
                                                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                                                {
                                                        new Save(currentFile, editor);
                                                        remake(prompt.getText());
                                                        p.dispose();
                                                }
                                        }
                                });
                        }
                        
                });
                menu.add(open);
                add(editor, BorderLayout.CENTER);
                pack();
                setVisible(true);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        }
        

        private static DomJ dj;
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
                                        dj = new DomJ(args[0]);
                        }
                        else
                                dj = new DomJ(args[0]);
                }
                else
                        System.out.println("-h for usage");
        }
        public static void remake(String fileName)
        {
                dj.dispose();
                dj = new DomJ(fileName);
        }
                
}

