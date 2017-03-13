/*

Longest Common Subsequence (LCS)

*/

public int lcsDynamic(char str1[],char str2[])
{    
	int temp[][] = new int[str1.length + 1][str2.length + 1];
	int max = 0;
	for(int i=1; i < temp.length; i++){
		for(int j=1; j < temp[i].length; j++){
			if(str1[i-1] == str2[j-1]) {
				temp[i][j] = temp[i - 1][j - 1] + 1;
			}
			else
			{
				temp[i][j] = Math.max(temp[i][j-1],temp[i-1][j]);
			}
			if(temp[i][j] > max){
				max = temp[i][j];
			}
		}
	}
	return max;
}


// print all combinations of LCSS
fst = "AATCC"
snd = "ACACG"
i = fst.length
j = snd.length

private Set<String> lcs(int[][] dp, String fst, String snd, int i, int j) {
    Set<String> lcss = new HashSet<>();z

    if (i == 0 || j == 0) {
        lcss.add("");
    } else if (fst.charAt(i - 1) == snd.charAt(j - 1)) {
        for (String lcs : lcs(dp, fst, snd, i - 1, j - 1)) {
            lcss.add(lcs + fst.charAt(i - 1));
        }
    } else {
        if (dp[i - 1][j] >= dp[i][j - 1]) {
            lcss.addAll(lcs(dp, fst, snd, i - 1, j));
        }

        if (dp[i][j - 1] >= dp[i - 1][j]) {
            lcss.addAll(lcs(dp, fst, snd, i, j - 1));
        }
    }
    return lcss;
}