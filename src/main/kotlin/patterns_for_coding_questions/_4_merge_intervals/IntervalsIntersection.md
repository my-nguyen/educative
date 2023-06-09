Given two lists of intervals, find the **intersection of these two lists.**
Each list consists of **disjoint intervals sorted on their start time.**

**Example 1:**  
**Input:** arr1=[[1, 3], [5, 6], [7, 9]], arr2=[[2, 3], [5, 7]]  
**Output:** [2, 3], [5, 6], [7, 7]  
**Explanation:** The output list contains the common intervals between the two lists.

**Example 2:**  
**Input:** arr1=[[1, 3], [5, 7], [9, 12]], arr2=[[5, 10]]  
**Output:** [5, 7], [9, 10]  
**Explanation:** The output list contains the common intervals between the two lists.

## Solution

This problem follows the Merge Intervals pattern. As we have discussed under Insert patterns_for_coding_questions.Interval, there are five overlapping
possibilities between two intervals ‘a’ and ‘b’. A close observation will tell us that whenever the two intervals overlap,
one of the interval’s start time lies within the other interval. This rule can help us identify if any two intervals
overlap or not.
1. a and b don't overlap
2. a and b overlap, b start time lies within a
3. a and b overlap, b start time lies within a
4. a and b overlap, a start time lies within b
5. a and b overlap, a start time lies within b

Now, if we have found that the two intervals overlap, how can we find the overlapped part?

Again from the above diagram, the overlapping interval will be equal to:
    start = max(a.start, b.start)
    end = min(a.end, b.end) 

That is, the highest start time and the lowest end time will be the overlapping interval.

So our algorithm will be to iterate through both the lists together to see if any two intervals overlap. If two intervals
overlap, we will insert the overlapped part into a result list and move on to the next interval which is finishing early.