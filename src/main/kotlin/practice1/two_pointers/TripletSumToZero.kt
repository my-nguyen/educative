package practice1.two_pointers

class TripletSumToZero {
    fun searchTriplets(array: IntArray): List<IntArray> {
        array.sort()
        val result = mutableListOf<IntArray>()
        for (i in 0 until array.size-2) {
            var j = i + 1
            while (j < array.size && array[j] == array[j+1])
                j++
            var k = array.lastIndex
            while (k > i && array[k-1] == array.last())
                k--
            // println("array: ${array.contentToString()}, i: $i, j: $j, k: $k")
            while (j < k) {
                val sum = array[i] + array[j] + array[k]
                // println("sum: $sum, i: $i, j: $j, k: $k")
                when {
                    sum < 0 -> j++
                    sum > 0 -> k--
                    else -> {
                        val triplet = intArrayOf(array[i], array[j], array[k])
                        result.add(triplet)
                        // println("trip: ${triplet.contentToString()}, j: $j, k: $k")
                        while (j < k && array[j] == array[j+1])
                            j++
                        while (j < k && array[k-1] == array[k])
                            k--
                        j++
                        k--
                    }
                }
            }
        }
        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(-3,0,1,2,-1,1,-2), intArrayOf(-5,2,-1,-2,3))
            for (array in arrays) {
                val triplets = TripletSumToZero().searchTriplets(array)
                print("array: ${array.contentToString()}, triplets: ")
                for (triplet in triplets) {
                    print("${triplet.contentToString()}, ")
                }
                println()
            }
        }
    }
}