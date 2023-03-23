Given a list of intervals, **merge all the overlapping intervals** to produce a list that has only mutually exclusive intervals.

**Example 1:**  
**Intervals:** [[1,4], [2,5], [7,9]]  
**Output:** [[1,5], [7,9]]  
**Explanation:** Since the first two intervals [1,4] and [2,5] overlap, we merged them into one [1,5].

**Example 2:**  
**Intervals:** [[6,7], [2,4], [5,9]]  
**Output:** [[2,4], [5,9]]  
**Explanation:** Since the intervals [6,7] and [5,9] overlap, we merged them into one [5,9].

**Example 3:**  
**Intervals:** [[1,4], [2,6], [3,5]]  
**Output:** [[1,6]]  
**Explanation:** Since all the given intervals overlap, we merged them into one.

##Solution

Let’s take the example of two intervals (‘a’ and ‘b’) such that a.start <= b.start. There are four possible scenarios:
1. a and b do not overlap
2. some part of b overlaps with a
3. a fully overlaps b
4. b fully overlaps a but both have same start time.

Our goal is to merge the intervals whenever they overlap. For the above-mentioned three overlapping scenarios
(2, 3, and 4), this is how we will merge them:
1.
2. c(a.start, b.end)
3. c(a.start, a.end)
4. c(a.start, b.end)

The diagram above clearly shows a merging approach. Our algorithm will look like this:
1. Sort the intervals on the start time to ensure a.start <= b.start
2. If ‘a’ overlaps ‘b’ (i.e. b.start <= a.end), we need to merge them into a new interval ‘c’ such that:  
    c.start = a.start  
    c.end = max(a.end, b.end)
3. We will keep repeating the above two steps to merge ‘c’ with the next interval if it overlaps with ‘c’.