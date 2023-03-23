package practice2.modified_binary_search

class MaxInBitonicArray {
    fun findMax(array: IntArray): Int {
        return practice2(array)
    }

    fun practice2(array: IntArray): Int {
        var low = 0
        var high = array.lastIndex
        while (low < high) {
            val mid = (low + high)/2
            if (array[mid] < array[mid+1])
                low = mid + 1
            else
                high = mid
        }
        return array[high]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(1,2,3,4,5,6,7,8,7,6,5,4,3,2,1), intArrayOf(1,2,3,4,5,6,7,8,7,6,5,4,3,2),
                intArrayOf(1,2,3,4,5,6,7,8,7,6,5,4,3), intArrayOf(5,6,7,8,7,6,5,4,3,2,1),
                intArrayOf(1,3,8,12,4,2), intArrayOf(3,8,3,1), intArrayOf(1,3,8,12), intArrayOf(10,9,8)
            )
            for (array in arrays) {
                val peak = MaxInBitonicArray().findMax(array)
                println("array: ${array.contentToString()}, peak: $peak")
            }
        }
    }
}