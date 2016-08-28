/*
	Dynamic programming to solve Longest common substring problem
	
	e.g. string1 = "abcdaf", string2= "zbcdf"
	LCS -> bcd of length 3

	Time and space -> O(n^2)
	
*/

public String longestCommonSubstring(String string1, String string2)
{

		char[] str1 = string1.toCharArray();
		char[] str2 = string2.toCharArray();		

		
        int T[][] = new int[str1.length+1][str2.length+1];

		StringBuilder sb = new StringBuilder();
		
        int max = 0;
		int end = 0;
		
        for(int i=1; i <= str1.length; i++)
		{
            for(int j=1; j <= str2.length; j++)
			{
                if(str1[i-1] == str2[j-1])
				{
                    T[i][j] = T[i-1][j-1] +1;
                    
					if(max < T[i][j])
					{
                        max = T[i][j];
						int start = i - T[i][j]; 
						if(start == end)
						{
							sb.append(str1.charAt(i-1));
						}
						else
						{
							end = start;
							sb = new StringBuilder();
							sb.append(string1.substring(end,i));
						}
                    }
                }
            }
        }
        return sb.toString();
}