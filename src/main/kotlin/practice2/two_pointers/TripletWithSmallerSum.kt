package practice2.two_pointers

class TripletWithSmallerSum {
    fun searchTriplets(array: IntArray, target: Int): Int { // -1,0,2,3; 3
        array.sort()
        var count = 0
        for (i in 0 until array.size-2) { // 0
            var j = i + 1 // 1
            var k = array.lastIndex // 3
            while (j < k) { // 2 < 3
                val sum = array[i] + array[j] + array[k] // 2
                if (sum < target) { // 2 < 3
                    count += k - j
                    j++
                } else
                    k-- //
            }
        }
        return count
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(-1,0,2,3), intArrayOf(-1,4,2,1,3))
            val targets = arrayOf(3, 5)
            for (i in arrays.indices) {
                val count = TripletWithSmallerSum().searchTriplets(arrays[i], targets[i])
                println("array: ${arrays[i].contentToString()}, target: ${targets[i]}, count: $count")
            }
        }
    }
}