package practice2.two_pointers

class SubarrayProductLessThanK {
    fun findSubarrays(array: IntArray, target: Int): List<List<Int>> { // 2,5,3,10; 30
        val result = mutableListOf<List<Int>>()
        for (i in array.indices) {
            if (array[i] >= target)
                continue

            val list = mutableListOf(array[i]) // list=[2]
            var product = array[i]             // product=2
            result.add(list.toList())          // [[2]]
            for (j in i+1 until array.size) {  // j=1, 2
                if (product * array[j] < target) { // 10*2=20 < 30
                    product *= array[j]       // 20
                    list.add(array[j])        // [2,5]
                    result.add(list.toList()) // [[2], [2,5]]
                } else
                    break
            }
        }
        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(2,5,3,10), intArrayOf(8,2,6,5))
            val targets = arrayOf(30, 50)
            for (i in arrays.indices) {
                print("array: ${arrays[i].contentToString()}, target: ${targets[i]}, ")
                val subarrays = SubarrayProductLessThanK().findSubarrays(arrays[i], targets[i])
                for (subarray in subarrays)
                    print("$subarray, ")
                println()
            }
        }
    }
}