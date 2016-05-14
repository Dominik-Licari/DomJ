import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class DomJ extends JFrame
{
        private JMenuBar menuBar;
        private JMenu file;
        private JMenuItem save, saveAs, open, run, compile;
        private RSyntaxTextArea editor;
        private File workingDirectory;
        private File currentFile;
        private File configFile;
        private Scanner conf;
        public DomJ(String fileName)
        {
                super("DomJ, the best IDE");
                setLayout(new BorderLayout());
                workingDirectory = new File(System.getProperty("user.dir"));
                currentFile = new File(fileName);
                editor = new RSyntaxTextArea(20, 60);
                editor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
                editor.setCodeFoldingEnabled(true);
                RTextScrollPane sp = new RTextScrollPane(editor);
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
                menuBar = new JMenuBar();
                file = new JMenu("File");
                menuBar.add(file);
                save = new JMenuItem("Save");
                save.addActionListener(new Save(currentFile, editor));
                file.add(save);
                saveAs = new JMenuItem("SaveAs");
                saveAs.addActionListener(new SaveAs(editor));
                file.add(saveAs);
                open = new JMenuItem("Open");
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
                file.add(open);
                compile = new JMenuItem("Compile");
                compile.addActionListener(new CompileJava(fileName, editor));
                menuBar.add(compile);
                //run = new JMenuItem("Run");
                //run.addActionListener(new Run(fileName, editor));
                //menuBar.add(run);
                setJMenuBar(menuBar);
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

