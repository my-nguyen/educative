Given a binary tree where each node can only have a digit (0-9) value, each root-to-leaf path will represent a number.
Find the total sum of all the numbers represented by all paths.

##Solution

This problem follows the Binary Tree Path Sum pattern. We can follow the same DFS approach. The additional thing we need
to do is to keep track of the number representing the current path.

How do we calculate the path number for a node? Taking the first example mentioned above, say we are at node ‘7’. As we
know, the path number for this node is ‘17’, which was calculated by: 1 * 10 + 7 => 17. We will follow the same approach
to calculate the path number of each node.