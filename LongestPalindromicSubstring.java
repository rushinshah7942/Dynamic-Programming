/*
	Given a string S, find the longest palindromic substring in S. 
	You may assume that the maximum length of S is 1000, and 
	there exists one unique longest palindromic substring.
	
	Logic: 
			Let s be the input string, i and j are two indices of the string. Define a 2-dimension array "table" and let table[i][j] denote whether a substring from i to j is palindrome.
				
				Changing condition:
				table[i+1][j-1] == 1 && s.charAt(i) == s.charAt(j)
				=>
				table[i][j] == 1
*/

public class Solution
{
    public String longestPalindrome(String s) 
	{
		if(s==null || s.length()<=1)
			return s;
 
		int len = s.length();
		int maxLen = 1;
		boolean [][] dp = new boolean[len][len];
 
		String longest = null;
		for(int l=0; l<s.length(); l++)
		{
			for(int i=0; i<len-l; i++)
			{
				int j = i+l;
				if(s.charAt(i)==s.charAt(j) && (j-i<=2||dp[i+1][j-1]))
				{
					dp[i][j]=true;
 
					if(j-i+1>maxLen)
					{
						maxLen = j-i+1; 
						longest = s.substring(i, j+1);
					}
				}
			}
		}
		return longest;
	}
}
