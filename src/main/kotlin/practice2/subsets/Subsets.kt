package practice2.subsets

class Subsets {
    fun findSubsets(array: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        result.add(listOf())
        for (i in array.indices) { // [1,5,3]
            val list = mutableListOf<Int>()
            for (j in i until array.size) { // j=1
                list.add(array[j])          // list=[5]
                result.add(list.toList())   // result=[[], [1], [1,5], [1,5,3], [5]
            }
        }
        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(intArrayOf(1,3), intArrayOf(1,5,3), /*intArrayOf(1,3,3)*/)
            for (array in arrays) {
                print("array: ${array.contentToString()}, ")
                val subsets = Subsets().findSubsets(array)
                print("subsets: ")
                for (subset in subsets) {
                    print("$subset, ")
                }
                println()
            }
        }
    }
}