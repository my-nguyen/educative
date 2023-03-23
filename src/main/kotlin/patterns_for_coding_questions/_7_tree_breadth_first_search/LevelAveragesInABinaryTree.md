Given a binary tree, populate an array to represent the **averages of all of its levels.**

##Solution

This problem follows the Binary Tree Level Order Traversal pattern. We can follow the same BFS approach. The only difference
will be that instead of keeping track of all nodes of a level, we will only track the running sum of the values of
all nodes in each level. In the end, we will append the average of the current level to the result array.