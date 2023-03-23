package practice2.sliding_window

class MaxFruitCountOf2Types {
    fun findLength(fruits: CharArray): Int { // 'A','B','C','A','C'
        var i = 0
        var max = 0
        val map = mutableMapOf<Char, Int>()
        for (j in fruits.indices) { // 0,1,2,3,4
            val charJ = fruits[j]   // A,B,C,A,C
            val countJ = map.getOrDefault(charJ, 0)
            map[charJ] = countJ + 1 // C->2,A->1
            while (map.size > 2) {  // 1,2,3,3
                val charI = fruits[i] // A,B
                map[charI] = map[charI]!! - 1 // A->0,B->0
                if (map[charI] == 0)
                    map.remove(charI) // C->1,A->1
                i++                   // 1,2
            }
            max = maxOf(max, j-i+1) // 1,2,2,2,3
        }
        return max
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