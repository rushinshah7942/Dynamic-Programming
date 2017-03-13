/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.
*/


// THIS IS BY FAR THE BEST PROBLEM THAT I FACED
// For, good explanation follow this post -> https://discuss.leetcode.com/topic/39834/step-by-step-tackling-of-the-problem by @fun4LeetCode 
// Below is the solution

// Naive solution - Recursive 

/*
Termination condition: when do we know the answer to rob(root) without any calculation? Of course when the tree is empty -- we've got nothing to rob so the amount of money is zero.

Recurrence relation: i.e., how to get rob(root) from rob(root.left), rob(root.right), ... etc. From the point of view of the tree root, there are only two scenarios at the end: "root" is robbed or is not. If it is, due to the constraint that "we cannot rob any two directly-linked houses", the next level of subtrees that are available would be the four "grandchild-subtrees" (root.left.left, root.left.right, root.right.left, root.right.right). However if root is not robbed, the next level of available subtrees would just be the two "child-subtrees" (root.left, root.right). We only need to choose the scenario which yields the larger amount of money.
*/

public int rob(TreeNode root) {
    if (root == null) {
        return 0;
    }
    
    int val = 0;
    
    if (root.left != null) {
        val += rob(root.left.left) + rob(root.left.right); // 2 sub-trees
    }
    
    if (root.right != null) {
        val += rob(root.right.left) + rob(root.right.right); // 2 sub-trees
    }
    
    return Math.max( val + root.val ,  rob(root.left) + rob(root.right) );
	// val + root.val (if root is also considered)
	// rob(root.left) + rob(root.right) (root is not considered)
	
}

/*
We only considered the aspect of "optimal substructure", but think little about the possibilities of overlapping of the subproblems. For example, to obtain rob(root), we need rob(root.left), rob(root.right), rob(root.left.left), rob(root.left.right), rob(root.right.left), rob(root.right.right); but to get rob(root.left), we also need rob(root.left.left), rob(root.left.right), similarly for rob(root.right). The naive solution above computed these subproblems repeatedly, which resulted in bad time performance. Now if you recall the two conditions for dynamic programming: "optimal substructure" + "overlapping of subproblems", we actually have a DP problem. A naive way to implement DP here is to use a hash map to record the results for visited subtrees.
*/

public int rob(TreeNode root) {
    Map<TreeNode, Integer> map = new HashMap<>();
    return robSub(root, map);
}

private int robSub(TreeNode root, Map<TreeNode, Integer> map) {
    if (root == null) 
		return 0;
   
	// just return from here, without computing val for this node
	if (map.containsKey(root)) 
		return map.get(root);
    
    int val = 0;
    
    if (root.left != null) {
        val += robSub(root.left.left, map) + robSub(root.left.right, map); // 2 sub-trees
    }
    
    if (root.right != null) {
        val += robSub(root.right.left, map) + robSub(root.right.right, map); // 2 sub-trees
    }
    
    val = Math.max( val + root.val, robSub(root.left, map) + robSub(root.right, map));
    
	map.put(root, val);
    
    return val;
}

// preserving the logic of two scenarios i) if root is robbed ii) root is not robbed
/*
Redefine rob(root) as a new function which will return an array of two elements, the first element of which denotes the maximum amount of money that can be robbed if "root" is not robbed, while the second element signifies the maximum amount of money robbed if root is robbed.
*/

public int rob(TreeNode root) {
    int[] res = robSub(root);
    return Math.max(res[0], res[1]);
}

private int[] robSub(TreeNode root) {
    if (root == null) {
    	return new int[2];
    }
    
    int[] left = robSub(root.left);
    int[] right = robSub(root.right);
    
    int[] res = new int[2];
    res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    res[1] = root.val + left[0] + right[0];
    
    return res;
}