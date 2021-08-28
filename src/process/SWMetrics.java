
package process;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.Scanner;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

        import AstTreeImplementation.AbstructSyntaxTree;
//        import AstTreeImplementation.LexicalAnalayzer;

public class SWMetrics {
    private int numOfNonLocalVariable = 0;
//    private int numOfLoop = 0;
//    private int numOfStatement = 0;
//    private int numOfFunction = 0;
    private int numOfParameter = 0;
    private int index=0;
    private String fileName;
    private String code;


//    public int getNumOfLocalVariable() {
//        setNumOfLocalVariable();
//        return numOfLocalVariable;
//    }

    public int setNumOfLocalVariable(String[] codes) {

        int i, numOfLocalVariable= 0;
        for (i = 0; i < index; i++) {

            String p="\\s*(int|double|float|char)\\s*[_a-zA-z][_a-zA-Z0-9]*(=.*)*;";

            Pattern pa = Pattern.compile(p);
            Matcher m = pa.matcher(codes[i]);

            if (m.find()) {

                numOfLocalVariable++;

            }


        }
        return numOfLocalVariable;

    }

    public int getNumOfNonLocalVariable() {
        setNumOfNonLocalVariable();
        return numOfNonLocalVariable;
    }

    public void setNumOfNonLocalVariable() {

        AbstructSyntaxTree obj = new AbstructSyntaxTree(code);

//        this.numOfLocalVariable = obj.getNumberOfGlobalVariable();
//        System.out.println(numOfLocalVariable);
    }

//    public int getNumOfLoop() {
//        setNumOfLoop();
//        //	System.out.println(numOfLoop+"swmet49");
//        return numOfLoop;
//    }

    public int setNumOfLoop(String[] codes) {
        int i, numOfLoop= 0;
        for (i = 0; i < index; i++) {

            String p = "(while\\(\\s*.*\\s*\\)|for\\(\\s*.*\\s*;\\s*.*\\s*;\\s*.*\\s*\\))";

            Pattern pa = Pattern.compile(p);
            Matcher m = pa.matcher(codes[i]);

            if (m.find()) {

                numOfLoop++;

            }


        }
        return numOfLoop;

    }

//    public int getNumOfStatement()
//    {
//        setNumOfStatement();
//        return numOfStatement;
//    }

    public int setNumOfStatement(String[] codes)
    {
        int i, numOfStatement= 0;
        for (i = 0; i < index; i++) {

            String p = ".*;";

            Pattern pa = Pattern.compile(p);
            Matcher m = pa.matcher(codes[i]);

            if (m.find()) {

                numOfStatement++ ;

            }

        }
        return numOfStatement;
    }

//    public int getNumOfFunction()
//    {
//        setNumOfFunction();
//
//        return numOfFunction;
//    }

    public int setNumOfFunction()
    {
        int numOfFunction= 0;
        AbstructSyntaxTree obj = new AbstructSyntaxTree(code);

        numOfFunction = obj.getNumberOfFunction();
        return numOfFunction;
    }

//    public SWMetrics(String fileName) throws FileNotFoundException
//    {
//
//        this.fileName = fileName;
//
//        File file = new File(fileName);
//
//        Scanner input = new Scanner(file);
//
//
//        while(input.hasNext()) {
//
//            codes[index]=input.nextLine();
//            index++;
//        }
//
//    }

    public void calclateSWMetrics() throws IOException {
        File folder= new File("Src\\Files\\InputFiles\\");
        File[] rawCodes= folder.listFiles();
        deleteComment(rawCodes);
        File ncfolder= new File("Src\\Files\\NoCmntCodes\\");
        File[] ncCodes= ncfolder.listFiles();
        int idx= 0;
        MetricsCount[] swm= new MetricsCount[(int) ncfolder.length()];
        for(File it: ncCodes)
        {
            Scanner input = new Scanner(it);
            String[] codes= new String[5000];
            int index= 0;
            while(input.hasNext())
            {
                codes[index]=input.nextLine();
                index++;
            }

            int numOfLocalVariable= setNumOfLocalVariable(codes);
//            int numOfNonLocalVariable=
            int numOfLoop= setNumOfLoop(codes);
            int numOfStatement= setNumOfStatement(codes);
//            int numOfFunction=
//            int numOfParameter=
        }

    }

    public int getNumOfParameter()
    {
        return numOfParameter;
    }

    public void setNumOfParameter(int numOfParameter)
    {
        this.numOfParameter = numOfParameter;
    }

    public static void deleteComment(File[] listOfFiles) throws IOException
    {
        int fileNo= 0;
        for(File inputFile : listOfFiles)
        {
            Scanner reader= new Scanner(inputFile);
            ArrayList<String> code_lines= new ArrayList<>();


            FileWriter writer= new FileWriter("src\\Files\\NoCmntCodes\\" + inputFile.getName());

            while (reader.hasNext())
                code_lines.add(reader.nextLine());
//        System.out.println(code_lines.get(0).toCharArray()[0]);
//        char[] firstLine = code_lines.get(0).toCharArray();
//
//        System.out.println(firstLine);
//        for(int i=0; i<code_lines.size(); i++)
//        {
//            System.out.println(code_lines.get(i));
//        }

            boolean flag= false, tf= false;
            for(int i=0; i<code_lines.size(); i++)
            {
                tf= false;
                String line= code_lines.get(i);

                for(int j=0; j< line.length()-1; j++)
                {
                    if(line.charAt(j)=='/' && line.charAt(j+1)=='/')
                    {
                        tf= true;
                        break;
                    }
                    else if(line.charAt(j)=='/' && line.charAt(j+1)=='*') flag= true;
                    else if(line.charAt(j)=='*' && line.charAt(j+1)=='/')
                    {
                        j++;
                        flag= false;
                        continue;
                    }
                    if(flag==false) writer.write(line.charAt(j));
                }
                if(tf==false && flag==false && line.length()!=0 && line.charAt(line.length()-1)!='/')
                    writer.write(line.charAt(line.length()-1));

                writer.write('\n');
            }

            writer.close();
        }
    }


}