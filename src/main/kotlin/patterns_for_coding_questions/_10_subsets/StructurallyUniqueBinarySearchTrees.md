Given a number ‘n’, write a function to return all structurally unique Binary Search Trees (BST) that can store values 1 to ‘n’?

**Example 1:**  
**Input:** 2  
**Output:** List containing root nodes of all structurally unique BSTs.  
**Explanation:** Here are the 2 structurally unique BSTs storing all numbers from 1 to 2:

**Example 2:**  
**Input:** 3  
**Output:** List containing root nodes of all structurally unique BSTs.  
**Explanation:** Here are the 5 structurally unique BSTs storing all numbers from 1 to 3:

##Solution

This problem follows the Subsets pattern and is quite similar to Evaluate Expression. Following a similar approach,
we can iterate from 1 to ‘n’ and consider each number as the root of a tree. All smaller numbers will make up the left
sub-tree and bigger numbers will make up the right sub-tree. We will make recursive calls for the left and right sub-trees