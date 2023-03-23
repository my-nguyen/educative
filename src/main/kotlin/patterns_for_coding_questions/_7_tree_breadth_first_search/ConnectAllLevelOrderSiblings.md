Given a binary tree, connect each node with its level order successor.
The last node of each level should point to the first node of the next level.

##Solution

This problem follows the Binary Tree Level Order Traversal pattern. We can follow the same BFS approach. The only difference
will be that while traversing we will remember (irrespective of the level) the previous node to connect it with
the current node.