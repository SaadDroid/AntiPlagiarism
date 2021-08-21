package process;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Process
{
    private FileHash fileHasher= new FileHash();
    private Map<String, String> stringFolder= new HashMap<String, String>();
    private String[] fileNames;
    private String[] hash;
    private EditDistance mat= new EditDistance();
    private Calculation calculation= new Calculation();

    public void HashFolder() throws IOException
    {
        stringFolder= fileHasher.HashCalculator();
        fileNames= new String[stringFolder.size()];
        hash= new String[stringFolder.size()];

        Set<Map.Entry<String, String >> st= stringFolder.entrySet();

        int idx= 0;

        for(Map.Entry<String, String> itr : st)
        {
            fileNames[idx]= itr.getKey();
            hash[idx]= itr.getValue();

            FileWriter writer= new FileWriter("src\\Files\\OutputHash\\" + fileNames[idx]+"_Hash");
            writer.write(hash[idx]);
            writer.close();

            idx++;
        }

        double[][] result= new double[fileNames.length][fileNames.length];

        for(int i=0; i<fileNames.length; i++)
        {
            for(int j=0; j<fileNames.length; j++)
            {
                double mxm;
                double per;
                if(i==j)
                {
                    mxm= 0;
                    per= 0;
                }
                else {
                    mxm = mat.stringMatching(hash[i], hash[j]);
                    per = calculation.calculate(hash[i].length(), hash[j].length(), mxm);
                }
                result[i][j]= per;
            }
        }

        String resultName= new String("res");
        FileWriter csvWriter= new FileWriter("src\\Files\\result\\" + resultName+ ".csv");

        for (int i=0; i<=fileNames.length; i++)
        {
            if(i==0) csvWriter.append("file");
            else csvWriter.append(fileNames[i-1]);
            if(i!= fileNames.length) csvWriter.append(",");
        }

        csvWriter.append("\n");

        for(int i=0; i<fileNames.length; i++)
        {
            csvWriter.append(fileNames[i]);
            csvWriter.append(",");
            for (int j = 0; j < fileNames.length; j++)
            {
                csvWriter.append(String.valueOf(result[i][j]));
                if(j!= fileNames.length-1)
                    csvWriter.append(",");
            }

            csvWriter.append("\n");
        }
        csvWriter.close();
    }
}
