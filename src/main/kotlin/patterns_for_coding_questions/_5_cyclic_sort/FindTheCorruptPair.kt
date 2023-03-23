package patterns_for_coding_questions._5_cyclic_sort

class FindTheCorruptPair {
    fun findNumbers(array: IntArray): IntArray {
        // return mine(array)
        return educative(array)
    }

    // from educative
    // This problem follows the Cyclic Sort pattern and shares similarities with Find all Duplicate Numbers. Following
    // a similar approach, we will place each number at its correct index. Once we are done with the cyclic sort, we will
    // iterate through the array to find the number that is not at the correct index. Since only one number got corrupted,
    // the number at the wrong index is the duplicated number and the index itself represents the missing number.
    private fun educative(array: IntArray): IntArray {
        for (i in array.indices) {
            while (array[array[i]-1] != array[i]) {
                swap(array, i, array[i]-1)
            }
        }

        val pair = IntArray(2)
        for (i in array.indices) {
            if (array[i]-1 != i) {
                pair[0] = array[i]
                pair[1] = i + 1
                break
            }
        }
        return pair
    }

    private fun mine(array: IntArray): IntArray { // 3,1,2,3,6,4
        val numbers = IntArray(2)
        for (i in array.indices) {
            // println("iterating $i")
            while (array[i]-1 != i) {
                // println("checking at $i, element=${array[i]}")
                if (array[array[i]-1] == array[i]) {
                    numbers[0] = array[i]
                    break
                } else {
                    // print("swapping at $i with ${array[i]-1}")
                    swap(array, i, array[i]-1)
                    // println(", ${array.contentToString()}")
                }
            }
        }

        for (i in array.indices) {
            if (array[i]-1 != i) {
                numbers[1] = i + 1
            }
        }
        return numbers
    }

    private fun swap(array: IntArray, i: Int, j: Int) {
        val tmp = array[i]
        array[i] = array[j]
        array[j] = tmp
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(3,1,2,5,2), intArrayOf(3,1,2,3,6,4))
            for (array in arrays) {
                print("array: ${array.contentToString()}")
                val pair = FindTheCorruptPair().findNumbers(array)
                println(", sorted: ${array.contentToString()}, corrupt pair: ${pair.contentToString()}")
            }
        }
    }
}