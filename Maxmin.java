/* this program finds mim and max value from array using recursion /divide and conquer technique  
 * Time complexity - O(n)
 * */

class Maxmin
{
	public static int[] MaxMinValue(int a[], int i, int j)
	{
		if(i == j)
		{
			return new int[]{a[i], a[j]};
		}
		if ( i == j-1)
		{
			int min  = Math.min(a[i], a[j]);
			int max = Math.max(a[i], a[j]);
			return new int[]{min,max};		
		}
		int k = (i+j)/2;
		int left[] = MaxMinValue(a,i,k);
		int right[] = MaxMinValue(a,k+1,j);
		return new int[]{Math.min(left[0], right[0]), Math.max(left[1], right[1]) };
	}
	
	public static void main(String args[])
	{
		int a[] = {3, 5, 0, 10, 5, 1};
		int minmax[]  = MaxMinValue(a, 0, a.length-1);
		System.out.println("Min = " + minmax[0] + " Max = " + minmax[1]);
	}
}
