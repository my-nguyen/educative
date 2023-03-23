Given an array, find the sum of all numbers between the K1’th and K2’th smallest elements of that array.

**Example 1:**  
**Input:** [1, 3, 12, 5, 15, 11], and K1=3, K2=6  
**Output:** 23  
**Explanation:** The 3rd smallest number is 5 and 6th smallest number 15. The sum of numbers coming between 5 and 15 is 23 (11+12).

**Example 2:**  
**Input:** [3, 5, 8, 7], and K1=1, K2=4  
**Output:** 12  
**Explanation:** The sum of the numbers between the 1st smallest number (3) and the 4th smallest number (8) is 12 (5+7).

##Solution
This problem follows the Top ‘K’ Numbers pattern, and shares similarities with Kth Smallest Number.

We can find the sum of all numbers coming between the K1’th and K2’th smallest numbers in the following steps:
1. First, insert all numbers in a min-heap.
2. Remove the first K1 smallest numbers from the min-heap.
3. Now take the next K2-K1-1 numbers out of the heap and add them. This sum will be our required output.