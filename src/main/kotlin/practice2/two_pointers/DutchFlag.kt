package practice2.two_pointers

class DutchFlag {
    fun sort(array: IntArray) {
        // solution(array)
        // practice1(array)
        // practice2(array)
        // practice3(array)
        practice4(array)
    }

    fun practice4(array: IntArray) {
        var low = 0
        var high = array.lastIndex
        var i = 0
        while (i <= high) {
            when (array[i]) {
                0 -> {
                    swap(array, i, low)
                    i++
                    low++
                }
                1 -> i++
                else -> {
                    swap(array, i, high)
                    high--
                }
            }
        }
    }

    fun practice3(array: IntArray) {
        var low = 0
        var high = array.lastIndex
        var i = 0
        while (i <= high) {
            when (array[i]) {
                0 -> {
                    swap(array, i, low)
                    i++
                    low++
                }
                1 -> i++
                else -> {
                    swap(array, i, high)
                    high--
                }
            }
        }
    }

    fun practice2(array: IntArray) {
        var i = 0
        var j = array.lastIndex
        var k = 0
        while (k <= j) {
            when (array[k]) {
                0 -> {
                    swap(array, k, i)
                    k++
                    i++
                }
                1 -> k++
                else -> {
                    swap(array, k, j)
                    j--
                }
            }
        }
    }

    fun practice1(array: IntArray) {
        var i = 0
        var j = array.lastIndex
        var k = 0
        while (k <= j) {
            when (array[k]) {
                0 -> {
                    swap(array, k, i)
                    k++
                    i++
                }
                1 -> k++
                else -> {
                    swap(array, k, j)
                    j--
                }
            }
        }
    }

    fun recall3(array: IntArray) {
        var i = 0
        var j = array.lastIndex
        var k = 0
        while (k <= j) {
            when (array[k]) {
                0 -> {
                    swap(array, k, i)
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

    fun solution(array: IntArray) {
        // all elements < low are 0 and all elements > high are 2
        // all elements from >= low < i are 1
        var low = 0
        var high = array.lastIndex
        var i = 0
        while (i <= high) {
            when (array[i]) {
                0 -> {
                    swap(array, i, low)
                    // increment 'i' and 'low'
                    i++
                    low++
                }
                1 -> i++
                else -> {
                    swap(array, i, high)
                    // decrement 'high' only, after the swap the number at index 'i' could be 0, 1 or 2
                    high--
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