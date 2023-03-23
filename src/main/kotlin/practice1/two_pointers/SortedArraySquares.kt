package practice1.two_pointers

class SortedArraySquares {
    fun makeSquares(array: IntArray): IntArray {
        var positive = 0
        while (positive < array.size && array[positive] < 0) // -2, -1, 0, 2, 3
            positive++

        val result = IntArray(array.size)
        var negative = positive - 1
        var i = 0
        while (negative >= 0 && positive < array.size) {
            val negSquare = array[negative] * array[negative]
            val posSquare = array[positive] * array[positive]
            if (negSquare < posSquare) {
                result[i] = negSquare
                negative--
            } else {
                result[i] = posSquare
                positive++
            }
            i++
        }
        while (negative >= 0) {
            result[i] = array[negative] * array[negative]
            negative--
            i++
        }
        while (positive < array.size) {
            result[i] = array[positive] * array[positive]
            positive++
            i++
        }
        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(-2, -1, 0, 2, 3), intArrayOf(-3, -1, 0, 1, 2))
            for (array in arrays) {
                val squares = SortedArraySquares().makeSquares(array)
                println("array: ${array.contentToString()}, squares: ${squares.contentToString()}")
            }
        }
    }
}