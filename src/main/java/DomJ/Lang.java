package DomJ;
public enum Lang
{
        JAVA, ASM, FORTH, LISP;
        public static Lang fromString(String l)
        {
                return fromChar(l.toUpperCase().charAt(0));
        }
        public static Lang fromChar(char l)
        {
                switch (l)
                {
                        case 'A':
                                return Lang.ASM;
                        case 'F':
                                return Lang.FORTH;
                        case 'L':
                                return Lang.LISP;
                        default:
                                return Lang.JAVA;
                }
        }
}
