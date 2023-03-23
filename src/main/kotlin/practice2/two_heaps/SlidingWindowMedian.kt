package practice2.two_heaps

class SlidingWindowMedian {
    fun findSlidingWindowMedian(array: IntArray, window: Int): DoubleArray {
        return doubleArrayOf()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1,2,-1,3,5), intArrayOf(1,2,-1,3,5),)
            val windows = arrayOf(2, 3)
            for (i in arrays.indices) {
                print("array: ${arrays[i].contentToString()}, window: ${windows[i]}, ")
                val median = SlidingWindowMedian().findSlidingWindowMedian(arrays[i], windows[i])
                println("sliding window median: ${median.contentToString()}")
            }
        }
    }
}