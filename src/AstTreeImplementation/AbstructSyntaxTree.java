package AstTreeImplementation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import TreeStructure.TreeNode;

public class AbstructSyntaxTree {

    private String code;
    private Queue<Character> codeChar = new LinkedList<Character>();
    private Queue<Character> queue = new LinkedList<Character>();
    private LinkedList<String> tree = new LinkedList<String>();
    private Stack<Character> stack = new Stack<>();
    private int fristBracketOpen = 0;
    private int fristBracketClose = 0;
    private int secoundBracketOpen = 0;
    private int secoundBracketClose = 0;
    private  String[] globalVariable;
    private static String[] function = new String[1000];
    private  String[] functionClose = new String[1000];
    private  TreeNode<String> root;
    private  TreeNode<String> psudoRoot;
    // private String node="node";
    private int strCount = 0;
    private int funCount = 0;
    private int funCount1 = 0;
    private int index = 0;
    private int gobal=0;

    public AbstructSyntaxTree(String code) {

        this.code = code;
    }
/*	public AbstructSyntaxTree() {

	}*/


    public void clear() {

        fristBracketOpen = 0;
        fristBracketClose = 0;
        secoundBracketOpen = 0;
        secoundBracketClose = 0;




        funCount = 0;
        funCount1 = 0;
        index = 0;
    }
    public int getNumberOfFunction() {


        int i=0;
        for(String s : function) {
            if(s!=null) {
                i++;
                //System.out.println(s);
            }
        }

        return i;
    }

    public void addToQueue() {

        clear() ;

        String str = "";

        try {

            tree.add("root");

            int i;
            int size = code.length();
            for (i = 0; i < size; i++) {

                char ch = code.charAt(i);

                if (ch == '(') {

                    fristBracketOpen++;
                }

                if (ch == ')') {

                    fristBracketClose++;
                }

                if (ch == '{') {

                    secoundBracketOpen++;

                }

                if (ch == '}') {

                    secoundBracketClose++;
                }

                codeChar.add(ch);
            }

            int j;
            int cS = codeChar.size();
            str = "";// this string is for global variable
            int b1 = 0, b2 = 0, b3 = 0, b4 = 0;
            // b1=fristBracketOpen
            // b2=fristBracketClose
            // b3=secoundBracketOpen
            // b4=secoundBracketClose
            String fun = "";

            for (j = 0; j < size; j++) {

                char ch = codeChar.peek();
                codeChar.poll();

                if (ch == '(') {

                    b1++;
                    fun += ch;

                    while (b1 != b2) {

                        ch = codeChar.peek();
                        codeChar.poll();
                        j++;

                        if (ch == '(') {
                            b1++;
                        }

                        if (ch == ')') {
                            b2++;
                        }
                        fun += ch;

                    }
                    // System.out.println(fun);
                    if (fun != null) {
                        function[funCount] = fun;
                        funCount++;
                        // funCount++;
                    }
                    fun = "";

                }

                if (ch == '{') {

                    b3++;
                    fun += ch;
                    while (b3 != b4) {

                        ch = codeChar.peek();
                        codeChar.poll();
                        j++;

                        if (ch == '{') {
                            b3++;
                        }

                        if (ch == '}') {
                            b4++;
                        }

                        fun += ch;

                    }

                    //System.out.println(fun);
                    if (fun != null) {
                        functionClose[funCount1] = fun;
                        fun = "";
                        funCount1++;
                    }

                }

                str += ch;
            }

            // System.out.println(str);

            // parseTreeConstruct();

        } catch (Exception e) {

            // TODO Auto-generated catch block
            System.out.println(e);
            System.out.println("lala");
            e.printStackTrace();

        }str = str.replaceAll(";\\s*[_a-zA-Z]+[_a-zA-Z0-9]*\\)}", ";");
        str = str.replaceAll(",", ";");
        // System.out.println(str);

        if(str.contains(";")==false) {

            str=null;

        }
        else {

            globalVariable = str.split(";");

        }

        int i;
        for (i = 0; i < funCount; i++) {

            if (function[i] != null && functionClose[i] != null) {

                function[i] = function[i] + functionClose[i];
                //System.out.println(function[i]);
                function[i]=function[i].replaceAll("void", "");

                String fun[]=function[i].split(";");
                function[i]="";
                for(String h : fun) {

                    h+=";";
                    //System.out.println(h);
                    h=h.replaceAll("[_a-zA-Z]+[_a-zA-Z0-9]*\\(.*\\);", "");

                    function[i]+=h;
                }


                //function[i]=function[i].replaceAll("[_a-zA-Z]+[_a-zA-Z0-9]*\\(.*\\);", "");
            }
        }

        // parseTreeConstruct();

        TreeNode<String> treeRoot = parseTreeConstruct();
        for (TreeNode<String> node : treeRoot) {
            String indent = createIndent(node.getLevel());
            System.out.println(indent + node.data);
        }

    }

    public int getNumberOfGlobalVariable() {

        System.out.println(gobal);
        return gobal;
    }

    public  TreeNode<String> parseTreeConstruct() {

        root = new TreeNode<String>("root");
        {
            if (globalVariable != null) {
                for (String d : globalVariable) {
                    gobal++;

                    // node=node+Integer.toString(strCount);
                    TreeNode<String> node = root.addChild(d);
                    {

                        if (d.contains("=") != false) {

                            String[] hasEqual = d.split("=");

                            TreeNode<String> node1 = node.addChild(hasEqual[0]);
                            TreeNode<String> node2 = node.addChild("=");
                            TreeNode<String> node3 = node.addChild(hasEqual[1]);

                        }

                    }
                }

            }

            for (String d : function) {

                if (d != null) {
                    TreeNode<String> node = root.addChild(d);
                    {

                        for (index = 0; index < d.length(); index++) {
                            int openCerlyBrace = 0;

                            String s = "";

                            // System.out.println(d.charAt(i));

                            if (d.charAt(index) == '{') {

                                funHandler(d, node);

                            }

                            else if (d.charAt(index) == '(') {
                                openCerlyBrace++;
                                while (openCerlyBrace != 0) {
                                    index++;
                                    if (d.charAt(index) == '(')
                                        openCerlyBrace++;
                                    if (d.charAt(index) == ')')
                                        openCerlyBrace--;
                                    s += d.charAt(index);

                                }

                                s = s.replaceAll("\\(", "");
                                s = s.replaceAll("\\)", "");
                                String argument[];
                                argument = s.split(";|,");
                                for (String parameter : argument) {
                                    TreeNode<String> node1 = node.addChild(parameter);
                                    {
                                        if (parameter.contains("=") != false && parameter.contains("==") == false) {

                                            String[] hasEqual = parameter.split("=");

                                            TreeNode<String> node2 = node1.addChild(hasEqual[0]);
                                            TreeNode<String> node3 = node1.addChild("=");
                                            TreeNode<String> node4 = node1.addChild(hasEqual[1]);

                                        }

                                        else if (parameter.contains("==") != false) {

                                            String[] hasEqual = parameter.split("==");

                                            TreeNode<String> node2 = node1.addChild(hasEqual[0]);
                                            TreeNode<String> node3 = node1.addChild("=");
                                            TreeNode<String> node31 = node1.addChild("=");
                                            TreeNode<String> node4 = node1.addChild(hasEqual[1]);

                                        }

                                        else if (parameter.contains("<") != false) {

                                            String[] hasEqual = parameter.split("<");

                                            TreeNode<String> node2 = node1.addChild(hasEqual[0]);
                                            TreeNode<String> node3 = node1.addChild("<");
                                            TreeNode<String> node4 = node1.addChild(hasEqual[1]);

                                        }

                                        else if (parameter.contains(">") != false) {

                                            String[] hasEqual = parameter.split(">");

                                            TreeNode<String> node2 = node1.addChild(hasEqual[0]);
                                            TreeNode<String> node3 = node1.addChild(">");
                                            TreeNode<String> node4 = node1.addChild(hasEqual[1]);

                                        }
                                    }
                                }

                            }
                        }

                    }
                }

            }

        }

        return root;

    }

    public  void funHandler(String line , TreeNode<String> mainNode) {

        TreeNode<String> node = null;
        String statement="";
        for(index=index+1 ; index<line.length() ;index++) {

            char ch=line.charAt(index);

            if(ch=='}') return;

            if(ch=='{') {

                funHandler(line,node);

            }

            statement+=ch;

        }

        node=mainNode.addChild(statement);
        //	return;

    }

    public void rawParseTreeConstruct() {

        try {

            int size = codeChar.size();
            int i;

            String st = "";

            for (i = 0; i < size - 2; i++) {

                char ch;
                ch = codeChar.peek();
                codeChar.poll();

                if (ch != ';') {
                    st += ch;
                }

                else {
                    System.out.println(st);
                    st = "";
                }

                if (ch == '{') {

                    ch = codeChar.peek();
                    codeChar.poll();

                    while (ch != '}') {

                        st += ch;
                        ch = codeChar.peek();
                        codeChar.poll();

                    }
                    // System.out.println(st);
                    st = "";
                }

                if (ch == '(') {

                    ch = codeChar.peek();
                    codeChar.poll();

                    while (ch != ')') {

                        st += ch;
                        ch = codeChar.peek();
                        codeChar.poll();

                    }
                    // System.out.println(st);
                    st = "";

                }

            }

        } catch (Exception ex) {

            // TODO Auto-generated catch block
            ex.printStackTrace();

        }

    }

    public String createIndent(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }

    public int getGlobalVariableNumber() {

        return globalVariable.length;

    }

}
