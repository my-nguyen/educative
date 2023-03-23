package patterns_for_coding_questions._13_top_k_elements

import java.util.*

class KthLargestNumberInAStream {
    class KthLargestNumber(numbers: IntArray, val largest: Int) {
        val minHeap = PriorityQueue<Int>()

        init {
            // init1(numbers)
            init2(numbers)
        }

        fun add(number: Int): Int {
            // return add1(number)
            return add2(number)
        }

        private fun init1(numbers: IntArray) {
            for (i in 0 until largest) {
                minHeap.offer(numbers[i])
            }
            for (i in largest..numbers.lastIndex) {
                add(numbers[i])
            }
        }

        private fun init2(numbers: IntArray) {
            for (number in numbers) {
                add(number)
            }
        }

        fun add1(number: Int): Int {
            if (number > minHeap.peek()) {
                minHeap.poll()
                minHeap.add(number)
            }
            return minHeap.peek()
        }

        fun add2(number: Int): Int {
            minHeap.offer(number)
            if (minHeap.size > largest) {
                minHeap.poll()
            }
            return minHeap.peek()
        }
    }

    interface Command {
        fun run(array: IntArray, count: Int? = null): Int?
    }

    lateinit var stream: KthLargestNumber

    fun buildMap(): Map<String, Command> {
        val map = mutableMapOf<String, Command>()
        map["KthLargestNumber"] = object: Command {
            override fun run(array: IntArray, count: Int?): Int? {
                stream = KthLargestNumber(array, count!!)
                return null
            }
        }

        map["add"] = object: Command {
            override fun run(array: IntArray, count: Int?) = stream.add(array[0])
        }

        return map
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val methods = arrayOf("KthLargestNumber", "add", "add", "add")
            val arguments = arrayOf(
                intArrayOf(3, 1, 5, 12, 2, 11) to 4, intArrayOf(6) to null,
                intArrayOf(13) to null, intArrayOf(4) to null
            )
            val map = KthLargestNumberInAStream().buildMap()
            for (i in methods.indices) {
                val largest = map[methods[i]]?.run(arguments[i].first, arguments[i].second)
                println("$largest")
            }
        }
    }
}