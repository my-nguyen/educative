Given the head of a **Singly LinkedList** that contains a cycle, write a function to find the **starting node of the cycle.**

##Solution

If we know the length of the LinkedList cycle, we can find the start of the cycle through the following steps:
1. Take two pointers. Let’s call them pointer1 and pointer2.
2. Initialize both pointers to point to the start of the LinkedList.
3. We can find the length of the LinkedList cycle using the approach discussed in LinkedList Cycle. Let’s assume that
   the length of the cycle is ‘K’ nodes.
4. Move pointer2 ahead by ‘K’ nodes.
5. Now, keep incrementing pointer1 and pointer2 until they both meet.
6. As pointer2 is ‘K’ nodes ahead of pointer1, which means, pointer2 must have completed one loop in the cycle when
   both pointers meet. Their meeting point will be the start of the cycle.