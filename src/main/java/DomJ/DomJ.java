package DomJ;
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
                this(fileName, Lang.JAVA);
        }
        public DomJ(String fileName, Lang lang)
        {
                super("DomJ, the best IDE");
                setLayout(new BorderLayout());
                workingDirectory = new File(System.getProperty("user.dir"));
                currentFile = new File(fileName);
                editor = new RSyntaxTextArea(20, 60);
                switch (lang)
                {
                case ASM: 
                        editor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86);
                        break;
                case FORTH: 
                        editor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
                        break;
                case LISP: 
                        editor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
                        break;
                default:
                        editor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
                }
                        
                
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
                                                        remake(prompt.getText(), lang);
                                                        p.dispose();
                                                }
                                        }
                                });
                        }
                        
                });
                file.add(open);
                compile = new JMenuItem("Compile");
                switch (lang)
                {
                case ASM: 
                        compile.addActionListener(new CompileAssembly(fileName, editor));
                        break;
                //case FORTH:
                  //      compile.addActionListener(new CompileJava(fileName, editor));
                    //    break;
                //case LISP:
                  //      compile.addActionListener(new CompileJava(fileName, editor));
                    //    break;
                default: 
                        compile.addActionListener(new CompileJava(fileName, editor));
                }
                
                menuBar.add(compile);
                run = new JMenuItem("Run");
                run.addActionListener(new Run(fileName, editor, lang));
                menuBar.add(run);
                setJMenuBar(menuBar);
                add(editor, BorderLayout.CENTER);
                pack();
                setVisible(true);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        }
        

        private static DomJ dj;
        public static void main(String[] args)
        {
                Lang lang = null;
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
                                                System.out.println("Usage:\nDomJ [-h] [-l language] fileName");
                                                break;
                                        case 'l':
                                                lang = Lang.fromString(args[1]);
                                        default:
                                                break;
                                        }

                                if (args.length > 1 && lang == null)
                                        dj = new DomJ(args[1]);
                                else if (args.length > 2 && lang != null)
                                        dj = new DomJ(args[2], lang);
                        }
                        else
                                dj = new DomJ(args[0]);
                }
                else
                        System.out.println("-h for usage");
        }
        public static void remake(String fileName)
        {
                remake(fileName, Lang.JAVA);
        }
        public static void remake(String fileName, Lang lang)
        {
                dj.dispose();
                dj = new DomJ(fileName, lang);
        }       
}

