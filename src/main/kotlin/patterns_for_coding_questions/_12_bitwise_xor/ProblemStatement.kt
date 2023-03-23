package patterns_for_coding_questions._12_bitwise_xor

class ProblemStatement {
    // from educative
    // Flip: We can flip the image in place by replacing ith element from left with the ith element from the right.
    // Invert: We can take XOR of each element with 1. If it is 1 then it will become 0 and if it is 0 then it will become 1.
    fun flipAndInvertImage(array: Array<IntArray>): Array<IntArray> {
        val C = array[0].size
        for (row in array) {
            for (i in 0 until (C + 1) / 2) {
                val tmp = row[i]
                row[i] = row[C - 1 - i] xor 1
                row[C - 1 - i] = tmp xor 1
            }
        }
        return array
    }

    fun print(caption: String, array: Array<IntArray>) {
        println(caption)
        for (i in array.indices) {
            for (j in 0 until array[i].size) {
                print("${array[i][j]} ")
            }
            println()
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1, 0, 1), intArrayOf(1, 1, 1), intArrayOf(0, 1, 1)),
                arrayOf(intArrayOf(1, 1, 0, 0), intArrayOf(1, 0, 0, 1), intArrayOf(0, 1, 1, 1), intArrayOf(1, 0, 1, 0))
            )
            val solution = ProblemStatement()
            for (array in arrays) {
                solution.print("original:", array)
                val flipped = solution.flipAndInvertImage(array)
                solution.print("flipped:", flipped)
                println()
            }
        }
    }
}