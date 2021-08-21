package process;

public class Calculation
{
    protected double clonePersentage;

    public double calculate(double len1, double len2, double dis)
    {
        double totalLen= len1+ len2;
        clonePersentage= (totalLen-dis)/totalLen;

        return clonePersentage*100;
    }
}
