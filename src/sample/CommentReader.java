package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CommentReader
{
    public static void commentDeleter() throws IOException
    {
        File inputFile= new File("src\\Files\\inputFiles\\input1.c");
        Scanner reader= new Scanner(inputFile);
        ArrayList<String>code_lines= new ArrayList<>();

        FileWriter writer= new FileWriter("src\\Files\\outputFiles\\output1.c");

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
        System.out.println("File written Successfully");
    }
}
