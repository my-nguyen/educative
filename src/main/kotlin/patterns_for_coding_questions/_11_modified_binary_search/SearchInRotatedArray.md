Given an array of numbers which is sorted in ascending order and also rotated by some arbitrary number,
find if a given ‘key’ is present in it.

Write a function to return the index of the ‘key’ in the rotated array. If the ‘key’ is not present, return -1.
You can assume that the given array does not have any duplicates.

**Example 1:**
**Input:** [10, 15, 1, 3, 8], key = 15
**Output:** 1
**Explanation:** '15' is present in the array at index '1'.

**Example 2:**
**Input:** [4, 5, 7, 9, 10, -1, 2], key = 10
**Output:** 4
**Explanation:** '10' is present in the array at index '4'.

##Solution

The problem follows the Binary Search pattern. We can use a similar approach as discussed in Order-agnostic Binary Search
and modify it similar to Search Bitonic Array to search for the ‘key’ in the rotated array.

After calculating the middle, we can compare the numbers at indices start and middle. This will give us two options:
1. If arr[start] <= arr[middle], the numbers from start to middle are sorted in ascending order.
2. Else, the numbers from middle+1 to end are sorted in ascending order.

Once we know which part of the array is sorted, it is easy to adjust our ranges. For example, if option-1 is true, we have
two choices:
1. By comparing the ‘key’ with the numbers at index start and middle we can easily find out if the ‘key’ lies between
   indices start and middle; if it does, we can skip the second part => end = middle -1.
2. Else, we can skip the first part => start = middle + 1.

4 - 5 - 7 - 9 - 10 - -1 - 2
key=10
start=0, middle=3, end=6

Since arr[start] <= arr[middle], we can conclude that all numbers from start --> middle are sorted in ascending order
By looking at the sorted part, from its start and end, we can conclude that the key can't be in the sorted part, hence
start = middle +1
start=4, middle=5, end=6
Since arr[start] >  arr[middle], we can conclude that all numbers from middle --> end are sorted in ascending order
By looking at the sorted part, from its start and end, we can conclude that the key can't be in the sorted part,
hence end = middle - 1
We have found the key!

Since there are no duplicates in the given array, it is always easy to skip one part of the array in each iteration.
However, if there are duplicates, it is not always possible to know which part is sorted. We will look into this case
in the ‘Similar Problems’ section.