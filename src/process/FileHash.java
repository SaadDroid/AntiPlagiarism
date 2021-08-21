package process;

import keywords.Keywords;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class FileHash
{
    private Scanner input;

    Keywords keywords= new Keywords();

    private HashMap<String, String> id= new HashMap<String, String>();

    private String filePath;
    private String fileName;

    public HashMap<String, String> HashCalculator() throws FileNotFoundException
    {
        try
        {
            File folder= new File("G:\\Java Projects\\AntiPlagiarism\\src\\Files\\outputFiles");
            File[] listOfFiles= folder.listFiles();


            String s, hash= "_";

            for(File fileIt : listOfFiles)
            {
                if(fileIt.isFile())
                {
                    filePath= fileIt.getAbsolutePath();
                    File fileReader= new File(filePath);

                    fileName= fileIt.getName();

                    input= new Scanner(fileReader);

                    while (input.hasNext())
                    {
                        boolean found= false;
                        String matcher= input.next();

                        for(String cKeyword : keywords.cKeyWord)
                            if(matcher.equals(cKeyword))
                            {
                                hash+="cKeyWord_";
                                found= true;
                                break;
                            }

                        if(found==true) continue;

                        for(String specialSymbol : keywords.specialSymbol)
                            if(matcher.equals(specialSymbol))
                            {
                                hash+="specialSymbol_";
                                found= true;
                                break;
                            }

                        if(found==true) continue;

                        for(String pungchuator : keywords.pungchuator)
                            if(matcher.equals(pungchuator))
                            {
                                hash+="pungchuator_";
                                found= true;
                                break;
                            }

                        if(found==false) hash+="token_";
                    }
                }

                id.put(fileName, hash);

                input= null;
                hash= "_";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }
}
