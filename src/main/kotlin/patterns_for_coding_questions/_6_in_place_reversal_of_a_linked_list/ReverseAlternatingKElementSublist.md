Given the head of a LinkedList and a number ‘k’, **reverse every alternating ‘k’ sized sub-list** starting from the head.

If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.

##Solution

The problem follows the In-place Reversal of a LinkedList pattern and is quite similar to Reverse every K-element Sub-list.
The only difference is that we have to skip ‘k’ alternating elements. We can follow a similar approach, and in each
iteration after reversing ‘k’ elements, we will skip the next ‘k’ elements.