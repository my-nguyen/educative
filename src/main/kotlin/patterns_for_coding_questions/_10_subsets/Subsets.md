Given a set with distinct elements, find all of its distinct subsets.

**Example 1:**  
**Input:** [1, 3]  
**Output:** [], [1], [3], [1,3]

**Example 2:**  
**Input:** [1, 5, 3]  
**Output:** [], [1], [5], [3], [1,5], [1,3], [5,3], [1,5,3]

##Solution

To generate all subsets of the given set, we can use the Breadth First Search (BFS) approach. We can start with
an empty set, iterate through all numbers one-by-one, and add them to existing sets to create new subsets.

Let’s take the example-2 mentioned above to go through each step of our algorithm:

Given set: [1, 5, 3]
1. Start with an empty set: [[]]
2. Add the first number (1) to all the existing subsets to create new subsets: [[], [1]];
3. Add the second number (5) to all the existing subsets: [[], [1], [5], [1,5]];
4. Add the third number (3) to all the existing subsets: [[], [1], [5], [1,5], [3], [1,3], [5,3], [1,5,3]].