Given a number ‘n’, write a function to return the count of structurally unique Binary Search Trees (BST)
that can store values 1 to ‘n’.

**Example 1:**  
**Input:** 2  
**Output:** 2  
**Explanation:** As we saw in the previous problem, there are 2 unique BSTs storing numbers from 1-2.

**Example 2:**  
**Input:** 3  
**Output:** 5  
**Explanation:** There will be 5 unique BSTs that can store numbers from 1 to 3.

##Solution

This problem is similar to Structurally Unique Binary Search Trees. Following a similar approach, we can iterate from 1
to ‘n’ and consider each number as the root of a tree and make two recursive calls to count the number of left and right
sub-trees.