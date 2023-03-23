package patterns_for_coding_questions._3_fast_slow_pointers

class CycleInACircularArray {
    fun loopExists(array: IntArray): Boolean {
        // return mine(array)
        return educative(array)
    }

    // from educative
    // This problem involves finding a cycle in the array and, as we know, the Fast & Slow pointer method is an efficient
    // way to do that. We can start from each index of the array to find the cycle. If a number does not have a cycle
    // we will move forward to the next element. There are a couple of additional things we need to take care of:
    // 1. As mentioned in the problem, the cycle should have more than one element. This means that when we move a pointer
    //    forward, if the pointer points to the same element after the move, we have a one-element cycle. Therefore,
    //    we can finish our cycle search for the current element.
    // 2. The other requirement mentioned in the problem is that the cycle should not contain both forward and backward
    //    movements. We will handle this by remembering the direction of each element while searching for the cycle.
    //    If the number is positive, the direction will be forward and if the number is negative, the direction will be
    //    backward. So whenever we move a pointer forward, if there is a change in the direction, we will finish
    //    our cycle search right there for the current element.
    private fun educative(array: IntArray): Boolean {
        for (i in array.indices) {
            val isForward = array[i] >= 0
            var slow = i
            var fast = i
            // if slow or fast becomes '-1' this means we can't find cycle for this number
            do {
                // move one step for slow and fast pointers
                slow = next(array, isForward, slow)
                fast = next(array, isForward, fast)
                if (fast != -1) {
                    // move one more step for fast pointer
                    fast = next(array, isForward, fast)
                }
            } while (slow != -1 && fast != -1 && slow != fast)

            if (slow != -1 && slow == fast) {
                return true
            }
        }
        return false
    }

    private fun next(array: IntArray, isForward: Boolean, current: Int): Int {
        val direction = array[current] >= 0
        if (isForward != direction) {
            // change in direction
            return -1
        } else {
            var next = (current + array[current]) % array.size
            if (next < 0) {
                // wrap around for negative numbers
                next += array.size
            }
            if (next == current) {
                // one element cycle
                next = -1
            }
            return next
        }
    }

    private fun mine(array: IntArray): Boolean {
        for (i in array.indices) {
            val set = mutableSetOf<Int>()
            // try to find a loop with each index
            var current = i
            while (true) {
                if (set.contains(current)) {
                    // current is in set means a loop exists
                    return true
                } else {
                    set.add(current)
                    val next = (current + array[current]) % array.size
                    if (array[i] * array[next] < 0) {
                        // current and next elements have different signs: there's no circle with the current index
                        break
                    } else {
                        // they have the same sign: move on to the next element
                        current = next
                    }
                }
            }
        }
        return false
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1, 2, -1, 2, 2), intArrayOf(2, 2, -1, 2), intArrayOf(2, 1, -1, -2))
            for (array in arrays) {
                val isCircular = CycleInACircularArray().loopExists(array)
                println("array: ${array.contentToString()}, isCircular? $isCircular")
            }
        }
    }
}