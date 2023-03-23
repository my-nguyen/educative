package practice1.two_pointers

class DutchFlag {
    fun sort(array: IntArray) {
        var i = 0
        var j = array.lastIndex
        var k = 0
        while (i < j) {
            when (array[k]) {
                0 -> {
                    swap(array, i, k)
                    i++
                    k++
                }
                1 -> k++
                else -> {
                    swap(array, j, k)
                    j--
                }
            }
        }
    }

    fun sort2(array: IntArray) {
        var i = 0
        var j = array.lastIndex
        var k = 0
        while (i < j) {
            when (array[k]) {
                0 -> {
                    swap(array, i, k)
                    i++
                    k++
                }
                1 -> k++
                else -> {
                    swap(array, j, k)
                    j--
                }
            }
        }
    }

    fun sort1(array: IntArray) {
        var i = 0
        var j = array.lastIndex
        var k = 0
        while (k <= j) {
            when (array[k]) {
                0 -> {
                    swap(array, i, k)
                    i++
                    k++
                }
                1 -> k++
                else -> {
                    swap(array, k, j)
                    j--
                }
            }
        }
    }

    private fun swap(array: IntArray, i: Int, j: Int) {
        val tmp = array[i]
        array[i] = array[j]
        array[j] = tmp
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1,0,2,1,0), intArrayOf(2,2,0,1,2,0),
                intArrayOf(2,1,0,0,2,1,1,2,0,0,1,2,1,0,0,2,0,2,1,1,2,1,0,2,0,2,1)
            )
            for (array in arrays) {
                print("original: ${array.contentToString()}, ")
                DutchFlag().sort(array)
                println("sorted: ${array.contentToString()}")
            }
        }
    }
}