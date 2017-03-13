/*
There is a building of 100 floors. If an egg drops from the Nth floor or above, it will break. If it's dropped from any floor below, it will not break. You're given two eggs. Find N, while minimizing the number of drops for the worst case.
*/

// Interesting article: http://datagenetics.com/blog/july22012/index.html

/*
sub-problem def.

dp[n][m] : the minimal max drops we need, if we have n-floor building and m eggs.

base case def.

dp[0][m] = 0 (m >= 1)

dp[n][1] = n (n >= 1)

dp[n][m] = min { max { dp[i-1][m-1], dp[n-i][m] } } + 1 ( n >= i >= 1 )


*/

int ans[500][500];
int f(int n, int m)
{
	if (ans[n][m] != -1) 
		return ans[n][m];
	
	if (n == 0) 
	{ 
		ans[n][m] = 0; 
		return 0; 
	}
	if (m == 1) 
	{ 
		ans[n][m] = n; 
		return n; 
	}
	int res = INT_MAX;
	for (int i = 1; i <= n; ++i)
	{
		int x = max(f(i-1,m-1),f(n-i,m))+1;
		res = min(res, x);
	}
	ans[n][m] = res;
	return res;
}

int main()
{
	for (int i = 0; i < 500; ++i){
		for (int j = 0; j < 500; ++j){
			ans[i][j] = -1;
		}
	}
	cout << f(39, 2) << endl;
	return 0;
}



public int calculateRecursive(int eggs, int floors){
	if(eggs == 1){
		return floors;
	}
	if(floors == 0){
		return 0;
	}
	int min = 1000;
	for(int i=1; i <= floors; i++){
		int val = 1 + Math.max(calculateRecursive(eggs-1, i-1),calculateRecursive(eggs, floors-i));
		if(val < min){
			min = val;
		}
	}
	return min;
}
	
	
public int calculate(int eggs, int floors){
        
	int T[][] = new int[eggs+1][floors+1];
	int c =0;
	for(int i=0; i <= floors; i++){
		T[1][i] = i;
	}
	
	for(int e = 2; e <= eggs; e++){
		for(int f = 1; f <=floors; f++){
			T[e][f] = Integer.MAX_VALUE;
			for(int k = 1; k <=f ; k++){
				c = 1 + Math.max(T[e-1][k-1], T[e][f-k]);
				if(c < T[e][f]){
					T[e][f] = c;
				}
			}
		}
	}
	return T[eggs][floors];
}	