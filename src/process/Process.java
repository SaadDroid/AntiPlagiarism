package process;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static sample.Main.mainScene;
import static sample.Main.primaryStage;


public class Process
{
    public static TextField input_ResultName= new TextField("Write output file name");
    public static String resultName;
    public static double[][] result;
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

        result= new double[fileNames.length][fileNames.length];

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
                    per= Double.parseDouble(String.format("%.4f", per));
                }
                result[i][j]= per;
            }
        }



        primaryStage.show();


        input_ResultName.setTranslateX(50);
        input_ResultName.setTranslateY(20);
        input_ResultName.setMinHeight(20);
        input_ResultName.setMinWidth(100);


        Button proceedButton= new Button("Proceed");
        proceedButton.setTranslateX(50);
        proceedButton.setTranslateY(100);


        proceedButton.setOnAction(event -> {
            if(!input_ResultName.getText().equals(""))
            {
                resultName = input_ResultName.getText();
                System.out.println(resultName);
            }
            else
                resultName = "Result";

            try {
                outputFileWriting();
            } catch (IOException e) {
                e.printStackTrace();
            }

            primaryStage.close();
        });

        Group resultNamingGrp= new Group();
        resultNamingGrp.getChildren().addAll(input_ResultName, proceedButton);
        Scene resultNamingScene= new Scene(resultNamingGrp, 300, 300);
        primaryStage.setScene(resultNamingScene);

//        System.out.println(resultName);

    }

    public void outputFileWriting() throws IOException {
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
                csvWriter.append(String.valueOf(result[i][j])+"%");
                if(j!= fileNames.length-1)
                    csvWriter.append(",");
            }

            csvWriter.append("\n");
        }
        csvWriter.close();
    }
}
