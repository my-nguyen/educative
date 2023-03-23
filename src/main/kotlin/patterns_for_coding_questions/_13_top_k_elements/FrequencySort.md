Given a string, sort it based on the decreasing frequency of its characters.

**Example 1:**  
**Input:** "Programming"  
**Output:** "rrggmmPiano"  
**Explanation:** 'r', 'g', and 'm' appeared twice, so they need to appear before any other character.

**Example 2:**  
**Input:** "abcbab"  
**Output:** "bbbaac"  
**Explanation:** 'b' appeared three times, 'a' appeared twice, and 'c' appeared only once.

##Solution
This problem follows the Top ‘K’ Elements pattern, and shares similarities with Top ‘K’ Frequent Numbers.

We can follow the same approach as discussed in the Top ‘K’ Frequent Numbers problem. First, we will find the frequencies
of all characters, then use a max-heap to find the most occurring characters