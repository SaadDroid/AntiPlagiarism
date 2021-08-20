package keywords;

public class EditDistance {

    public int stringMatching(String s1, String s2)
    {
        int n1= s1.length(), n2= s2.length();
        int dp[][]= new int[n1+1][n2+1];

        for(int i=0; i<=n1; i++)
            for (int j = 0; j <= n2; j++)
            {
                if (i == 0)
                    dp[i][j] = j;

                else if (j == 0)
                    dp[i][j] = i;

                else if (s1.charAt(i - 1) == s1.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];

                else
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
            }

        return dp[n1][n2];
    }
}
