Given a binary tree, populate an array to represent its zigzag level order traversal.
You should populate the values of all **nodes of the first level from left to right**,
then **right to left for the next level** and keep alternating in the same manner for the following levels.

##Solution

This problem follows the Binary Tree Level Order Traversal pattern. We can follow the same BFS approach. The only
additional step we have to keep in mind is to alternate the level order traversal, which means that for every other level,
we will traverse similar to Reverse Level Order Traversal.