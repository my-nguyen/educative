package practice2.modified_binary_search

class RotationCountOfRotatedArray {
    fun countRotations(array: IntArray): Int {
        return practice5(array)
    }

    fun practice5(array: IntArray): Int {
        var low = 0
        var high = array.lastIndex
        while (low < high) {
            val mid = (low + high)/2
            if (mid+1 < array.size && array[mid] > array[mid+1])
                return mid + 1
            if (0 < mid-1 && array[mid-1] > array[mid])
                return mid

            if (array[0] < array[mid])
                low = mid + 1
            else
                high = mid - 1
        }
        return 0
    }

    fun practice4(array: IntArray): Int {
        var low = 0
        var high = array.lastIndex
        while (low < high) {
            val mid = (low + high)/2
            if (mid < array.lastIndex && array[mid] > array[mid+1])
                return mid + 1
            if (0 < mid && array[mid-1] > array[mid])
                return mid

            if (array[0] < array[mid])
                low = mid + 1
            else
                high = mid - 1
        }
        return 0
    }

    fun practice3(array: IntArray): Int {
        var low = 0
        var high = array.lastIndex
        while (low < high) {
            val mid = low + (high-low)/2
            if (mid < high && array[mid] > array[mid+1])
                return mid + 1
            if (low < mid && array[mid-1] > array[mid])
                return mid

            if (array[low] < array[mid])
                low = mid + 1
            else
                high = mid - 1
        }
        return 0
    }

    fun practice2(array: IntArray): Int {
        var low = 0
        var high = array.lastIndex
        while (low < high) {
            val mid = low + (high-low)/2
            if (mid < high && array[mid] > array[mid+1])
                return mid + 1
            if (low < mid && array[mid-1] > array[mid])
                return mid

            if (array[low] < array[mid])
                low = mid + 1
            else
                high = mid - 1
        }
        return 0
    }

    fun practice1(array: IntArray): Int {
        var low = 0
        var high = array.lastIndex
        while (low < high) {
            val mid = low + (high-low)/2
            if (mid < array.size && array[mid] > array[mid+1])
                return mid + 1
            if (0 < mid && array[mid-1] > array[mid])
                return mid

            if (array[low] < array[mid])
                low = mid + 1
            else
                high = mid - 1
        }
        return 0
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(10,15,1,3,8), intArrayOf(4,5,7,9,10,-1,2), intArrayOf(1,3,8,10)
            )
            for (array in arrays) {
                val rotations = RotationCountOfRotatedArray().countRotations(array)
                println("array: ${array.contentToString()}, rotations: $rotations")
            }
        }
    }
}