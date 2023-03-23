Given a binary tree and a node, find the level order successor of the given node in the tree.
The level order successor is the node that appears right after the given node in the level order traversal.

##Solution

This problem follows the Binary Tree Level Order Traversal pattern. We can follow the same BFS approach. The only difference
will be that we will not keep track of all the levels. Instead we will keep inserting child nodes to the queue. As soon as
we find the given node, we will return the next node from the queue as the level order successor.