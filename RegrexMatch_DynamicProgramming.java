/*

Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

*/

// Dynamic Programming
// References: 
// 1) https://discuss.leetcode.com/topic/40371/easy-dp-java-solution-with-detailed-explanation/2
// 2) https://www.youtube.com/watch?v=l3hda49XcDE


public boolean isMatch(String s, String p) 
{
    if (s == null || p == null) 
	{
        return false;
    }
		
		boolean T[][] = new boolean[s.length + 1][p.length + 1];

        T[0][0] = true;
        
		//Deals with patterns like a* or a*b* or a*b*c*
        for (int i = 1; i < T[0].length; i++) 
		{
            if (p[i-1] == '*') 
			{
                T[0][i] = T[0][i - 2];
            }
        }

        for (int i = 1; i < T.length; i++) 
		{
            for (int j = 1; j < T[0].length; j++) 
			{
                if (p[j - 1] == '.' || p[j - 1] == s[i - 1]) 
				{
                    T[i][j] = T[i-1][j-1];
                } 
				else if (p[j - 1] == '*')  
				{
                    T[i][j] = T[i][j - 2];
                    if (p[j-2] == '.' || p[j - 2] == s[i - 1]) 
					{
                        T[i][j] = T[i][j] | T[i - 1][j];
                    }
                } 
				else 
				{
                    T[i][j] = false;
                }
            }
        }
        return T[s.length][p.length];
}