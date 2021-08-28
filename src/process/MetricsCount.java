package process;

public class MetricsCount
{
    private int numOfLocalVariable = 0;
    private int numOfNonLocalVariable = 0;
    private int numOfLoop = 0;
    private int numOfStatement = 0;
    private int numOfFunction = 0;
    private int numOfParameter = 0;
    private String fileName;

    public MetricsCount(int numOfLocalVariable, int numOfNonLocalVariable, int numOfLoop, int numOfStatement, int numOfFunction, int numOfParameter, String fileName )
    {
        this.fileName= fileName;
        this.numOfFunction= numOfFunction;
        this.numOfLocalVariable= numOfLocalVariable;
        this.numOfLoop= numOfLoop;
        this.numOfNonLocalVariable= numOfNonLocalVariable;
        this.numOfParameter= numOfParameter;
        this.numOfStatement= numOfStatement;
    }
}
