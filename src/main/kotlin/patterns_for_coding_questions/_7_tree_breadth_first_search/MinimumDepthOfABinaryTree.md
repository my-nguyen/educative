Find the minimum depth of a binary tree.
The minimum depth is the number of nodes along the **shortest path from the root node to the nearest leaf node.**

##Solution

This problem follows the Binary Tree Level Order Traversal pattern. We can follow the same BFS approach. The only difference
will be, instead of keeping track of all the nodes in a level, we will only track the depth of the tree. As soon as
we find our first leaf node, that level will represent the minimum depth of the tree.