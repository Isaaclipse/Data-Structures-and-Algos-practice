// Hackerrank Problem: Trees: Is This a Binary Search Tree?
// https://www.hackerrank.com/challenges/ctci-is-binary-search-tree/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=trees //

	  class Node {
        int data;
        Node left;
        Node right;
     }
*/
boolean checkBST(Node root) {
	root =
	return check(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
}
boolean check(Node n, int min, int max){
	if(n==null)
		return true;
	if(n.data <= min || n.data >= max)
		return false;
	return check(n.left, min, n.data)
		&& check(n.right, n.data, max);
}
