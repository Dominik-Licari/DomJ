import javax.swing.*;
import java.awt.*;
public class DomJ extends JFrame
{
        TextArea editor;
        Button run;
        Button compile;
        Button jar;
        public DomJ()
        {
               super("DomJ, the best IDE");
               editor = new TextArea();
        }
        public DomJ(String fileName)
        {
               super("DomJ, the best IDE");
               editor = new TextArea();
        }
        public static void main(String[] args)
        {
                new DomJ();
        }
        
}
