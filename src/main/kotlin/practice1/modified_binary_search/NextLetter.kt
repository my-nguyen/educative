package practice1.modified_binary_search

class NextLetter {
    fun searchNextLetter(letters: CharArray, key: Char): Char {
        if (key >= letters.last())
            return letters.first()

        var low = 0
        var high = letters.lastIndex
        while (low <= high) {
            val mid = low + (high-low)/2
            when {
                letters[mid] < key -> low = mid + 1
                letters[mid] > key -> high = mid - 1
                else -> return letters[mid+1]
            }
        }
        return letters[low]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                charArrayOf('a','c','f','h'), charArrayOf('a','c','f','h'),
                charArrayOf('a','c','f','h'), charArrayOf('a','c','f','h')
            )
            val keys = arrayOf('f', 'b', 'm', 'h')
            for (i in arrays.indices) {
                val next = NextLetter().searchNextLetter(arrays[i], keys[i])
                println("letters: ${arrays[i].contentToString()}, key: ${keys[i]}, next: $next")
            }
        }
    }
}