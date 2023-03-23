package patterns_for_coding_questions._11_modified_binary_search

class NextLetter {
    fun searchNextLetter(letters: CharArray, key: Char): Char {
        return if (key >= letters.last()) {
            letters.first()
        } else {
            var left = 0
            var right = letters.lastIndex
            while (left <= right) {
                val mid = left + (right-left)/2
                if (letters[mid] <= key) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }
            letters[left]
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                charArrayOf('a','c','f','h'), charArrayOf('a','c','f','h'), charArrayOf('a','c','f','h'), charArrayOf('a','c','f','h')
            )
            val keys = arrayOf('f', 'b', 'm', 'h')
            for (i in arrays.indices) {
                val next = NextLetter().searchNextLetter(arrays[i], keys[i])
                println("letters: ${arrays[i].contentToString()}, key: ${keys[i]}, next: $next")
            }
        }
    }
}