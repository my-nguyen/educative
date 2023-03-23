package practice2.two_pointers

class SortedArraySquares {
    fun makeSquares(array: IntArray): IntArray { // -2,-1,0,2,3
        var i = 0
        while (i < array.size && array[i] < 0) // 0,1,2
            i++
        var j = i - 1 // 1
        var k = 0
        val squares = IntArray(array.size)
        while (i < array.size && j >= 0) { // i=3,j=0
            val squareI = array[i] * array[i] // 0,4,4
            val squareJ = array[j] * array[j] // 1,1,4
            if (squareI < squareJ) {
                squares[k] = squareI  // [0]
                i++                   // i=3
            } else {
                squares[k] = squareJ  // [0,1,4]
                j--                   // j=-1
            }
            k++ // 1,2,3
        }

        for (l in i until array.size) { // 3..4
            squares[k] = array[l] * array[l]
            k++
        }
        for (l in j downTo 0) {
            squares[k] = array[l] * array[l]
            k++
        }
        return squares
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(-2,-1,0,2,3), intArrayOf(-3, -1, 0, 1, 2))
            for (array in arrays) {
                val squares = SortedArraySquares().makeSquares(array)
                println("array: ${array.contentToString()}, squares: ${squares.contentToString()}")
            }
        }
    }
}