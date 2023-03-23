package patterns_for_coding_questions._14_k_way_merge

import java.util.*

class KthSmallestNumberInASortedMatrix {
    fun findKthSmallest(matrix: Array<IntArray>, k: Int): Int {
        // return mine(matrix, k)
        // return educativeMinHeap(matrix, k)
        return educativeBinarySearch(matrix, k)
    }

    // from educative
    // This problem follows the K-way merge pattern and can be easily converted to Kth Smallest Number in M Sorted Lists.
    // As each row (or column) of the given matrix can be seen as a sorted list, we essentially need to find the Kth
    // smallest number in ‘N’ sorted lists.
    data class Node(val row: Int, var col: Int)

    private fun educativeMinHeap(matrix: Array<IntArray>, k: Int): Int {
        val minHeap = PriorityQueue<Node> { n1, n2 ->
            matrix[n1.row][n1.col] - matrix[n2.row][n2.col]
        }

        // put the 1st element of each row in the min heap; we don't need to push more than 'k' elements in the heap
        for (i in 0 until minOf(k, matrix.size)) {
            minHeap.add(Node(i, 0))
        }

        // take the smallest (top) element form the min heap, if the running count is equal to k return the number
        // if the row of the top element has more elements, add the next element to the heap
        var numberCount = 0
        var result = 0
        while (minHeap.isNotEmpty()) {
            val node = minHeap.poll()
            result = matrix[node.row][node.col]
            if (++numberCount == k) {
                break
            } else {
                node.col++
                if (matrix[0].size > node.col) {
                    minHeap.add(node)
                }
            }
        }
        return result
    }

    // Since each row and column of the matrix is sorted, is it possible to use Binary Search to find the Kth smallest number?
    // The biggest problem to use Binary Search, in this case, is that we don’t have a straightforward sorted array,
    // instead, we have a matrix. As we remember, in Binary Search, we calculate the middle index of the search space
    // (‘1’ to ‘N’) and see if our required number is pointed out by the middle index; if not we either search in
    // the lower half or the upper half. In a sorted matrix, we can’t really find a middle. Even if we do consider
    // some index as middle, it is not straightforward to find the search space containing numbers bigger or smaller
    // than the number pointed out by the middle index.
    // An alternative could be to apply the Binary Search on the “number range” instead of the “index range”. As we know
    // that the smallest number of our matrix is at the top left corner and the biggest number is at the bottom right
    // corner. These two numbers can represent the “range” i.e., the start and the end for the Binary Search. Here is
    // how our algorithm will work:
    // 1. Start the Binary Search with start = matrix[0][0] and end = matrix[n-1][n-1].
    // 2. Find middle of the start and the end. This middle number is NOT necessarily an element in the matrix.
    // 3. Count all the numbers smaller than or equal to middle in the matrix. As the matrix is sorted, we can do this in O(N)
    // 4. While counting, we can keep track of the “smallest number greater than the middle” (let’s call it n1) and
    //    at the same time the “biggest number less than or equal to the middle” (let’s call it n2). These two numbers
    //    will be used to adjust the “number range” for the Binary Search in the next iteration.
    // 5. If the count is equal to ‘K’, n1 will be our required number as it is the “biggest number less than or equal
    //    to the middle”, and is definitely present in the matrix.
    // 6. If the count is less than ‘K’, we can update start = n2 to search in the higher part of the matrix and if
    //    the count is greater than ‘K’, we can update end = n1 to search in the lower part of the matrix in the next iteration.
    // Example matrix is:
    // 2 6 8
    // 3 7 10
    // 5 8 11
    // start = matrix[0][0] = 2
    // end = matrix[2][2] = 11
    // middle = (start + end)/2 = (2 + 11)/2 = 6
    // Smallest number greater than the middle(6): 7
    // Biggest number less than or equal to the middle(6): 6
    // Number of elements less than or equal to the middle(6) = 4
    // there are only 4 elements less than or equal to the middle, and we are looking for the 5th smallest number,
    // so let's search higher and update our 'start' to the smallest number greater than the middle.
    // start = matrix[1][1] = 7
    // end = matrix[2][2] = 11
    // middle = (start + end)/2 = (7 + 11)/2 = 9
    // Smallest number greater than the middle(9): 10
    // Biggest number less than or equal to the middle(9): 8
    // Number of elements less than or equal to the middle(9) = 7
    // there are 7 elements less than or equal to the middle, and we are looking for the 5th smallest number,
    // so let's search lower and update our 'end'  to the biggest number less than or equal to the middle.
    // start = matrix[0][0] = 2
    // end = matrix[2][2] = 11
    // middle = (start + end)/2 = (2 + 11)/2 = 6
    // Smallest number greater than the middle(6): 7
    // Biggest number less than or equal to the middle(6): 6
    // Number of elements less than or equal to the middle(6) = 4
    // start = matrix[1][1] = 7
    // end = matrix[2][1] = 8
    // middle = (start + end)/2 = (7 + 8)/2 = 7
    // Smallest number greater than the middle(7): 8
    // Biggest number less than or equal to the middle(7): 7
    // Number of elements less than or equal to the middle(7) = 5
    // there are 5 elements less than or equal to the middle therefore '7', which is the biggest number less than or
    // equal to the middle, is our required number
    private fun educativeBinarySearch(matrix: Array<IntArray>, k: Int): Int {
        val n = matrix.size
        var start = matrix[0][0]
        var end = matrix[n - 1][n - 1]
        while (start < end) {
            val mid = start + (end - start) / 2
            // first number is the smallest and the second number is the largest
            val smallLargePair = intArrayOf(matrix[0][0], matrix[n - 1][n - 1])
            val count = countLessEqual(matrix, mid, smallLargePair)
            when {
                count < k -> start = smallLargePair[1] // search higher
                count > k -> end = smallLargePair[0] // search lower
                else -> return smallLargePair[0]
            }
        }
        return start
    }

    private fun countLessEqual(matrix: Array<IntArray>, mid: Int, smallLargePair: IntArray): Int {
        var count = 0
        val n = matrix.size
        var row = n - 1
        var col = 0
        while (row >= 0 && col < n) {
            if (matrix[row][col] > mid) {
                // as matrix[row][col] is bigger than the mid, let's keep track of the smallest number greater than the mid
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col])
                row--
            } else {
                // as matrix[row][col] is less than or equal to the mid, let's keep track of the biggest number less than
                // or equal to the mid
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col])
                count += row + 1
                col++
            }
        }
        return count
    }

    data class Entry(val i: Int, val j: Int)

    private fun mine(matrix: Array<IntArray>, k: Int): Int {
        val minHeap = PriorityQueue<Entry> { e1, e2 -> matrix[e1.i][e1.j] - matrix[e2.i][e2.j] }
        for (i in matrix.indices) {
            val entry = Entry(i, 0)
            minHeap.offer(entry)
        }

        for (i in 1 until k) {
            val top = minHeap.poll()
            val i = top.i
            val j = top.j
            if (i < matrix.size && j < matrix[i].size) {
                val entry = Entry(i, j + 1)
                minHeap.offer(entry)
            }
        }
        val top = minHeap.poll()
        return matrix[top.i][top.j]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val matrix = arrayOf(
                intArrayOf(2, 6, 8), intArrayOf(3, 7, 10), intArrayOf(5, 8, 11)
            )
            val k = 5
            val smallest = KthSmallestNumberInASortedMatrix().findKthSmallest(matrix, k)
            println("matrix:")
            for (row in matrix) {
                println("${row.contentToString()}")
            }
            println("k: $k, smallest: $smallest")
        }
    }
}