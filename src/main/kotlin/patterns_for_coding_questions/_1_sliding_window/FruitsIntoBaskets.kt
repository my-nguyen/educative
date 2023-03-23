package patterns_for_coding_questions._1_sliding_window

class FruitsIntoBaskets {
    fun findLength(arr: CharArray): Int {
        var left = 0
        var max = 0
        val map = mutableMapOf<Char, Int>()
        for (right in arr.indices) {
            val rightChar = arr[right]
            val rightCount = map.getOrDefault(rightChar, 0)
            map[rightChar] = rightCount+1
            while (map.size > 2) {
                val leftChar = arr[left]
                val leftCount = map[leftChar]!! - 1
                if (leftCount == 0) {
                    map.remove(leftChar)
                } else {
                    map[leftChar] = leftCount
                }
                left++
            }
            max = maxOf(max, right-left+1)
        }
        return max
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val fruits = arrayOf(charArrayOf('A', 'B', 'C', 'A', 'C'), charArrayOf('A', 'B', 'C', 'B', 'B', 'C'))
            for (fruit in fruits) {
                val max = FruitsIntoBaskets().findLength(fruit)
                println("fruit: ${fruit.contentToString()}, max: $max")
            }
        }
    }
}