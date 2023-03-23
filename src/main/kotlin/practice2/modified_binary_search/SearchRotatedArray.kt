package practice2.modified_binary_search

class SearchRotatedArray {
    fun search(array: IntArray, key: Int): Int {
        return practice6(array, key)
    }

    fun practice6(array: IntArray, key: Int): Int {
        var low = 0
        var high = array.lastIndex
        while (low <= high) {
            val mid = (low + high)/2
            if (array[mid] == key)
                return mid

            if (array[low] < array[mid]) {
                if (key in array[low]..array[mid])
                    high = mid - 1
                else
                    low = mid + 1
            } else {
                if (key in array[mid]..array[high])
                    low = mid + 1
                else
                    high = mid - 1
            }
        }
        return -1
    }

    fun practice5(array: IntArray, key: Int): Int {
        var low = 0
        var high = array.lastIndex
        while (low <= high) {
            val mid = (low + high)/2
            if (array[mid] == key)
                return mid

            if (array[low] < array[mid]) {
                if (key in array[low]..array[mid])
                    high = mid - 1
                else
                    low = mid + 1
            } else {
                if (key in array[mid]..array[high])
                    low = mid + 1
                else
                    high = mid - 1
            }
        }
        return -1
    }

    fun practice4(array: IntArray, key: Int): Int {
        var low = 0
        var high = array.lastIndex
        while (low <= high) {
            val mid = low + (high-low)/2
            if (array[mid] == key)
                return mid

            if (array[low] <= array[mid]) {
                if (key in array[low]..array[mid])
                    high = mid - 1
                else
                    low = mid + 1
            } else {
                if (key in array[mid]..array[high])
                    low = mid + 1
                else
                    high = mid - 1
            }
        }
        return -1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(10,15,1,3,8), intArrayOf(4,5,7,9,10,-1,2),
                intArrayOf(4,5,6,7,0,1,2), intArrayOf(4,5,6,7,0,1,2), intArrayOf(1),
                intArrayOf(7,9,11,13,15,17,19,1,2,3,4,5,6), intArrayOf(7,9,11,13,15,17,19,1,2,3,4,5,6),
                intArrayOf(7,9,11,13,15,17,19,1,2,3,4,5,6), intArrayOf(7,9,11,13,15,17,19,1,2,3,4,5,6)
            )
            val keys = arrayOf(15,10,0,3,0,3,13,0,8)
            for (i in arrays.indices) {
                val index = SearchRotatedArray().search(arrays[i], keys[i])
                println("array: ${arrays[i].contentToString()}, key: ${keys[i]}, index: $index")
            }
        }
    }
}