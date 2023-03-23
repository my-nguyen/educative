package patterns_for_coding_questions._2_two_pointers

class SubarraysWithProductLessThanATarget {
    fun findSubarrays(array: IntArray, target: Int): List<List<Int>> {
        // return mine(array, target)
        return educative(array, target)
    }

    private fun educative(array: IntArray, target: Int): List<List<Int>> {
        val list = mutableListOf<List<Int>>()
        var product = 1
        var left = 0
        for (right in array.indices) {
            product *= array[right]
            while (product >= target && left < array.size) {
                product /= array[left]
                left++
            }

            // since the product of all numbers from left to right is less than the target,
            // all subarrays from left to right will have a product less than the target too; to avoid
            // duplicates, we will start with a subarray containing only array[right] and then extend it
            val tmp = mutableListOf<Int>()
            // this loop will instead create an extra subarray; don't know why.
            /*for (i in left..right) {
                tmp.add(array[i])
                list.add(tmp.toList())
            }*/
            for (i in right downTo left) {
                tmp.add(0, array[i])
                list.add(tmp.toList())
            }
        }
        return list
    }

    private fun mine(array: IntArray, target: Int): List<List<Int>> {
        val list = mutableListOf<List<Int>>()
        for (left in array.indices) {
            if (array[left] < target) {
                val single = listOf(array[left])
                list.add(single)

                var product = array[left]
                for (right in (left + 1)..array.lastIndex) {
                    product *= array[right]
                    if (product < target) {
                        val multiple = mutableListOf<Int>()
                        for (i in left..right) {
                            multiple.add(array[i])
                        }
                        list.add(multiple)
                    } else {
                        break
                    }
                }
            }
        }
        return list
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(2,5,3,10), /*intArrayOf(8,2,6,5), intArrayOf(9,1,1,15,2,2,2,1,15)*/)
            val targets = arrayOf(30, 50, 13)
            for (i in arrays.indices) {
                val subarrays = SubarraysWithProductLessThanATarget().findSubarrays(arrays[i], targets[i])
                println("array: ${arrays[i].contentToString()}, target: ${targets[i]}, subarrays: ")
                for (subarray in subarrays) {
                    print("${subarray}, ")
                }
                println()
            }
        }
    }
}