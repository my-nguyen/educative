Given an N∗N matrix where each row and column is sorted in ascending order, find the Kth smallest element in the matrix.

**Example 1:**  
**Input:** Matrix=
<pre>
[
   [2, 6, 8],
   [3, 7, 10],
   [5, 8, 11]
],
</pre>
K=5  
**Output:** 7  
**Explanation:** The 5th smallest number in the matrix is 7.

##Solution
This problem follows the K-way merge pattern and can be easily converted to Kth Smallest Number in M Sorted Lists.
As each row (or column) of the given matrix can be seen as a sorted list, we essentially need to find the Kth
smallest number in ‘N’ sorted lists.