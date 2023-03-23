Find the path with the maximum sum in a given binary tree. Write a function that returns the maximum sum.

A path can be defined as a **sequence of nodes between any two nodes** and doesn’t necessarily pass through the root.
The path must contain at least one node.

##Solution

This problem follows the Binary Tree Path Sum pattern and shares the algorithmic logic with Tree Diameter. We can follow
the same DFS approach. The only difference will be to ignore the paths with negative sums. Since we need to find
the overall maximum sum, we should ignore any path which has an overall negative sum.