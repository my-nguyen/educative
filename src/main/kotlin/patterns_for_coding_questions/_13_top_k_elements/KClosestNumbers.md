Given a sorted number array and two integers ‘K’ and ‘X’, find ‘K’ closest numbers to ‘X’ in the array.
Return the numbers in the sorted order. ‘X’ is not necessarily present in the array.

**Example 1:**  
**Input:** [5, 6, 7, 8, 9], K = 3, X = 7  
**Output:** [6, 7, 8]

**Example 2:**  
**Input:** [2, 4, 5, 6, 9], K = 3, X = 6  
**Output:** [4, 5, 6]

**Example 3:**  
**Input:** [2, 4, 5, 6, 9], K = 3, X = 10  
**Output:** [5, 6, 9]

##Solution

This problem follows the Top ‘K’ Numbers pattern. The biggest difference in this problem is that we need to find
the closest (to ‘X’) numbers compared to finding the overall largest numbers. Another difference is that the given array
is sorted.

Utilizing a similar approach, we can find the numbers closest to ‘X’ through the following algorithm:
1. Since the array is sorted, we can first find the number closest to ‘X’ through Binary Search. Let’s say that number is ‘Y’.
2. The ‘K’ closest numbers to ‘Y’ will be adjacent to ‘Y’ in the array. We can search in both directions of ‘Y’ to find
   the closest numbers.
3. We can use a heap to efficiently search for the closest numbers. We will take ‘K’ numbers in both directions of ‘Y’
   and push them in a Min Heap sorted by their absolute difference from ‘X’. This will ensure that the numbers with
   the smallest difference from ‘X’ (i.e., closest to ‘X’) can be extracted easily from the Min Heap.
4. Finally, we will extract the top ‘K’ numbers from the Min Heap to find the required numbers.