package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CommentReader
{
    public static void commentDeleter() throws FileNotFoundException
    {
        File inputFile= new File("src\\inputFiles\\input1.c");
        Scanner reader= new Scanner(inputFile);
        ArrayList<String>code_lines= new ArrayList<>();
        while (reader.hasNext())
        {
            code_lines.add(reader.nextLine());
        }
        System.out.println(code_lines.get(0).toCharArray()[0]);
        char[] firstLine = code_lines.get(0).toCharArray();

        System.out.println(firstLine);
//        for(int i=0; i<code_lines.size(); i++)
//        {
//            System.out.println(code_lines.get(i));
//        }

    }
}
