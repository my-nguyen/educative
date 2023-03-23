package patterns_for_coding_questions._2_two_pointers

class SquaringASortedArray {
    fun makeSquares(array: IntArray): IntArray {
        // return makeSquares1(array)
        return makeSquares2(array)
    }

    private fun makeSquares1(array: IntArray): IntArray {
        // find the index where the element is non-negative
        var i = 0
        while (array[i] < 0) {
            i++
        }

        // left is to the left of index
        var left = i - 1
        // right is right on the index
        var right = i
        // reuse index to iterate the squares array
        i = 0
        // println("pre, left: $left, right: $right")
        val squares = IntArray(array.size)
        while (left >= 0 && right <= array.lastIndex) {
            if (-array[left] <= array[right]) {
                // println("in, left: $left, i: $i")
                squares[i] = array[left] * array[left]
                left--
            } else {
                // println("in, right: $right,  i: $i")
                squares[i] = array[right] * array[right]
                right++
            }
            i++
        }
        while (left >= 0) {
            // println("out, left: $left, i: $i")
            squares[i] = array[left] * array[left]
            left--
            i++
        }
        while (right <= array.lastIndex) {
            // println("out, right: $right, i: $i")
            squares[i] = array[right] * array[right]
            right++
            i++
        }
        return squares
    }

    private fun makeSquares2(array: IntArray): IntArray {
        var left = 0
        var right = array.lastIndex
        val squares = IntArray(array.size)
        var i = squares.lastIndex
        while (left <= right) {
            val leftSquared = array[left] * array[left]
            val rightSquared = array[right] * array[right]
            if (leftSquared >= rightSquared) {
                squares[i] = leftSquared
                left++
            } else {
                squares[i] = rightSquared
                right--
            }
            i--
        }
        return squares
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(-2, -1, 0, 2, 3), intArrayOf(-3, -1, 0, 1, 2))
            for (array in arrays) {
                val squares = SquaringASortedArray().makeSquares(array)
                println("array: ${array.contentToString()}, squares: ${squares.contentToString()}")
            }
        }
    }
}