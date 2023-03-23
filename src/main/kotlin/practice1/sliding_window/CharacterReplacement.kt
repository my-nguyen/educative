package practice1.sliding_window

class CharacterReplacement {
    fun findLength(string: String, replace: Int): Int {
        val map = mutableMapOf<Char, Int>()
        for (c in string) {
            val count = map.getOrDefault(c, 0)
            map[c] = count + 1
        }
        return map.values.maxOrNull()!! + replace
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strings = arrayOf("aabccbb", "abbcb", "abccde")
            val replaces = arrayOf(2, 1, 1)
            for (i in strings.indices) {
                val length = CharacterReplacement().findLength(strings[i], replaces[i])
                println("string: ${strings[i]}, replace: ${replaces[i]}, length: $length")
            }
        }
    }
}