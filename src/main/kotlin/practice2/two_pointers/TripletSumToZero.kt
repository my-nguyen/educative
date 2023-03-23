package practice2.two_pointers

class TripletSumToZero {
    fun searchTriplets(array: IntArray): List<IntArray> {
        array.sort()
        val result = mutableListOf<IntArray>()
        for (i in 0 until array.size-2) {
            var j = i + 1
            var k = array.lastIndex
            while (j < k) {
                val sum = array[i] + array[j] + array[k]
                when {
                    sum < 0 -> j++
                    sum > 0 -> k--
                    else -> {
                        val tmp = intArrayOf(array[i], array[j], array[k])
                        result.add(tmp)
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