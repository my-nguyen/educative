Given a binary tree and a number ‘S’, find all paths in the tree such that the sum of all the node values of each path equals ‘S’.
Please note that the paths can start or end at any node but all paths must follow direction from parent to child (top to bottom).

##Solution

This problem follows the Binary Tree Path Sum pattern. We can follow the same DFS approach. But there will be four differences:
1. We will keep track of the current path in a list which will be passed to every recursive call.
2. Whenever we traverse a node we will do two things:
   * Add the current node to the current path.
   * As we added a new node to the current path, we should find the sums of all sub-paths ending at the current node.
     If the sum of any sub-path is equal to ‘S’ we will increment our path count.
3. We will traverse all paths and will not stop processing after finding the first path.
4. Remove the current node from the current path before returning from the function. This is needed to Backtrack while
   we are going up the recursive call stack to process other paths.