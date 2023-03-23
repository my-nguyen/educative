package practice1.sliding_window

class MaxFruitCountOf2Types {
    fun findLength(fruits: CharArray): Int {
        var i = 0
        var maxLength = 0
        val map = mutableMapOf<Char, Int>()
        for (j in fruits.indices) {
            val rfruit = fruits[j]
            val rcount = map.getOrDefault(rfruit, 0)
            map[rfruit] = rcount + 1

            while (map.size > 2) {
                val lfruit = fruits[i]
                val lcount = map[lfruit]
                map[lfruit] = lcount!! - 1
                // println("j: $j, i: $i, lfruit: $lfruit, map: ${map[lfruit]}")
                if (map[lfruit] == 0)
                    map.remove(lfruit)
                i++
            }
            // println("i: $i, j: $j, maxLenght: $maxLength")
            maxLength = maxOf(maxLength, j-i+1)
        }
        return maxLength
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val fruits = arrayOf(charArrayOf('A','B','C','A','C'), charArrayOf('A','B','C','B','B','C'))
            for (fruit in fruits) {
                val max = MaxFruitCountOf2Types().findLength(fruit)
                println("fruit: ${fruit.contentToString()}, max: $max")
            }
        }
    }
}