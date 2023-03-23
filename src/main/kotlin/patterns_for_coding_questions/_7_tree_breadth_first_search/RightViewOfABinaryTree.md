Given a binary tree, return an array containing nodes in its right view.
The right view of a binary tree is the set of **nodes visible when the tree is seen from the right side.**

##Solution

This problem follows the Binary Tree Level Order Traversal pattern. We can follow the same BFS approach. The only
additional thing we will be doing is to append the last node of each level to the result array.
