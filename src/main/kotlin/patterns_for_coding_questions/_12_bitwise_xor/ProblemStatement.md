Given a binary matrix representing an image, we want to flip the image horizontally, then invert it.

To flip an image horizontally means that each row of the image is reversed.
For example, flipping [0, 1, 1] horizontally results in [1, 1, 0].

To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0.
For example, inverting [1, 1, 0] results in [0, 0, 1].

**Example 1:**
**Input:**
<pre>
[
    [1,0,1],
    [1,1,1],
    [0,1,1]
]
</pre>

**Output:**
<pre>
[
    [0,1,0],
    [0,0,0],
    [0,0,1]
]
</pre>
**Explanation:** First reverse each row: [[1,0,1],[1,1,1],[1,1,0]]. Then, invert the image: [[0,1,0],[0,0,0],[0,0,1]]

**Example 2:**
**Input:**
<pre>
[
    [1,1,0,0],
    [1,0,0,1],
    [0,1,1,1],
    [1,0,1,0]
]
</pre>
**Output:**
<pre>
[
    [1,1,0,0],
    [0,1,1,0],
    [0,0,0,1],
    [1,0,1,0]
]
</pre>
**Explanation:** First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]

##Solution

* Flip: We can flip the image in place by replacing ith element from left with the ith element from the right.
* Invert: We can take XOR of each element with 1. If it is 1 then it will become 0 and if it is 0 then it will become 1.