Given a binary tree, connect each node with its level order successor.
The last node of each level should point to a null node.

##Solution

This problem follows the Binary Tree Level Order Traversal pattern. We can follow the same BFS approach. The only difference
is that while traversing a level we will remember the previous node to connect it with the current node.