Given a string and a pattern, find **all anagrams of the pattern in the given string**.

Every **anagram** is a **permutation** of a string.
As we know, when we are not allowed to repeat characters while finding permutations of a string,
we get N! permutations (or anagrams) of a string having N characters.
For example, here are the six anagrams of the string “abc”:

1. abc
2. acb
3. bac
4. bca
5. cab
6. cba

Write a function to return a list of starting indices of the anagrams of the pattern in the given string

**Example 1:**  
**Input:** String="ppqp", Pattern="pq"  
**Output:** [1, 2]  
**Explanation:** The two anagrams of the pattern in the given string are "pq" and "qp".

**Example 2:**  
**Input:** String="abbcabc", Pattern="abc"  
**Output:** [2, 3, 4]  
**Explanation:** The three anagrams of the pattern in the given string are "bca", "cab", and "abc".

##Solution

This problem follows the Sliding Window pattern and is very similar to Permutation in a String. In this problem, we need
to find every occurrence of any permutation of the pattern in the string. We will use a list to store the starting indices
of the anagrams of the pattern in the string.