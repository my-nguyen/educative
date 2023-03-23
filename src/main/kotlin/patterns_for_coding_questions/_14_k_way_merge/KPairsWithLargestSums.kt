package patterns_for_coding_questions._14_k_way_merge

import java.util.*

class KPairsWithLargestSums {
    // from educative
    // This problem follows the K-way merge pattern and we can follow a similar approach as discussed in Merge K Sorted Lists.
    // We can go through all the numbers of the two input arrays to create pairs and initially insert them all in the heap
    // until we have ‘K’ pairs in Min Heap. After that, if a pair is bigger than the top (smallest) pair in the heap,
    // we can remove the smallest pair and insert this pair in the heap.
    // We can optimize our algorithms in two ways:
    // 1. Instead of iterating over all the numbers of both arrays, we can iterate only the first ‘K’ numbers from both
    //    arrays. Since the arrays are sorted in descending order, the pairs with the maximum sum will be constituted
    //    by the first ‘K’ numbers from both the arrays.
    // 2. As soon as we encounter a pair with a sum that is smaller than the smallest (top) element of the heap, we don’t
    //    need to process the next elements of the array. Since the arrays are sorted in descending order, we won’t be
    //    able to find a pair with a higher sum moving forward.
    fun findKLargestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<IntArray> {
        val minHeap = PriorityQueue<IntArray> { p1, p2 ->
            (p1[0] + p1[1]) - (p2[0] + p2[1])
        }
        for (i in 0 until minOf(nums1.size, k)) {
            for (j in 0 until minOf(nums2.size, k)) {
                if (minHeap.size < k) {
                    val array = intArrayOf(nums1[i], nums2[j])
                    minHeap.add(array)
                } else {
                    if (nums1[i] + nums2[j] < minHeap.peek()[0] + minHeap.peek()[1]) {
                        // if the sum of the two numbers from the two arrays is smaller than the smallest (top) element
                        // of the heap, we can 'break' here. Since the arrays are sorted in the descending order,
                        // we'll not be able to find a pair with a higher sum moving forward.
                        break
                    } else {
                        // else: we have a pair with a larger sum, remove top and insert this pair in the heap
                        minHeap.poll()
                        val array = intArrayOf(nums1[i], nums2[j])
                        minHeap.add(array)
                    }
                }
            }
        }
        return minHeap.toList()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nums1 = arrayOf(intArrayOf(9, 8, 2), intArrayOf(5, 2, 1))
            val nums2 = arrayOf(intArrayOf(6, 3, 1), intArrayOf(2, -1))
            val k = arrayOf(3, 3)
            for (i in nums1.indices) {
                val pairs = KPairsWithLargestSums().findKLargestPairs(nums1[i], nums2[i], k[i])
                for (array in pairs) {
                    print("${array.contentToString()} ")
                }
                println()
            }
        }
    }
}