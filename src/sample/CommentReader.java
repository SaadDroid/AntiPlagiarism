package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.text.Text;

public class CommentReader
{
    public static void fileChooser() throws IOException
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        Stage inputStage = new Stage();
        File folder = directoryChooser.showDialog(inputStage);
//        inputStage.show();
//        File folder = new File("src/Files/inputFiles/");
        File[] listOfFiles = folder.listFiles();

        deleteComment(listOfFiles);

//        File inputFile= new File("src\\Files\\inputFiles\\input1.c");
        /*Scanner reader= new Scanner(inputFile);
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

        writer.close();*/
        System.out.println("File written Successfully");
    }

    public static void deleteComment(File[] listOfFiles) throws IOException
    {
        int fileNo= 0;
        for(File inputFile : listOfFiles)
        {
            Scanner reader= new Scanner(inputFile);
            ArrayList<String>code_lines= new ArrayList<>();


            FileWriter writer= new FileWriter("src\\Files\\outputFiles\\" + inputFile.getName());

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
