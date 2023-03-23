package practice2.modified_binary_search

class SearchBitonicArray {
    fun search(array: IntArray, key: Int): Int {
        val peak = peak(array)
        val index = ascend(array, key, peak)
        return if (index != -1)
            index
        else
            descend(array, key, peak)
    }

    fun peak(array: IntArray): Int {
        var low = 0
        var high = array.lastIndex
        while (low < high) {
            val mid = low + (high-low)/2
            if (array[mid] < array[mid+1])
                low = mid + 1
            else
                high = mid
        }
        return high
    }

    fun ascend(array: IntArray, key: Int, peak: Int): Int {
        var low = 0
        var high = peak
        while (low <= high) {
            val mid = low + (high-low)/2
            when {
                array[mid] < key -> low = mid + 1
                array[mid] > key -> high = mid - 1
                else -> return mid
            }
        }
        return -1
    }

    fun descend(array: IntArray, key: Int, peak: Int): Int {
        var low = peak
        var high = array.lastIndex
        while (low <= high) {
            val mid = low + (high-low)/2
            when {
                array[mid] < key -> high = mid - 1
                array[mid] > key -> low = mid + 1
                else -> return mid
            }
        }
        return -1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1,3,8,4,3), intArrayOf(3,8,3,1), intArrayOf(1,3,8,12), intArrayOf(10,9,8),
                intArrayOf(1,3,5,7,9,11,10,8,6,4,2,0), intArrayOf(1,3,5,7,9,11,10,8,6,4,2,0),
                intArrayOf(1,3,5,7,9,11,10,8,6,4,2,0), intArrayOf(1,3,5,7,9,11,10,8,6,4,2,0),
                intArrayOf(1,3,5,7,9,11,10,8,6,4,2,0), intArrayOf(1,3,5,7,9,11,10,8,6,4,2,0),
            )
            val keys = arrayOf(4,8,12,10,11,5,4,12,-1,-2)
            for (i in arrays.indices) {
                val index = SearchBitonicArray().search(arrays[i], keys[i])
                println("array: ${arrays[i].contentToString()}, key: ${keys[i]}, index: $index")
            }
        }
    }
}