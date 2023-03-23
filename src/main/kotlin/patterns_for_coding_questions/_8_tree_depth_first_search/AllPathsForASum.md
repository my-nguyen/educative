Given a binary tree and a number ‘S’,
find all paths from root-to-leaf such that the sum of all the node values of each path equals ‘S’.

##Solution

This problem follows the Binary Tree Path Sum pattern. We can follow the same DFS approach. There will be two differences:
1. Every time we find a root-to-leaf path, we will store it in a list.
2. We will traverse all paths and will not stop processing after finding the first path.
