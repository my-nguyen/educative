Given a binary tree and a number sequence, find if the sequence is present as a root-to-leaf path in the given tree.

##Solution

This problem follows the Binary Tree Path Sum pattern. We can follow the same DFS approach and additionally, track
the element of the given sequence that we should match with the current node. Also, we can return false as soon as
we find a mismatch between the sequence and the node value.