Given a binary tree, populate an array to represent its level-by-level traversal in reverse order,
i.e., the **lowest level comes first.**
You should populate the values of all nodes in each level from left to right in separate sub-arrays.

##Solution

This problem follows the Binary Tree Level Order Traversal pattern. We can follow the same BFS approach. The only difference
will be that instead of appending the current level at the end, we will append the current level at the beginning of
the result list.