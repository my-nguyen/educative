package practice1.top_k_elements

import java.util.*

class KClosestElements {
    fun findClosestElements(array: IntArray, closest: Int, target: Int): List<Int> {
        val maxHeap = PriorityQueue<Int> { a, b -> kotlin.math.abs(b - target) - kotlin.math.abs(a - target) }
        for (i in 0 until closest)
            maxHeap.add(array[i])
        for (i in closest until array.size) {
            if (kotlin.math.abs(array[i]-target) < kotlin.math.abs(maxHeap.peek()-target)) {
                maxHeap.poll()
                maxHeap.add(array[i])
            }
        }
        return maxHeap.toList()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                intArrayOf(5,6,7,8,9), intArrayOf(2,4,5,6,9), intArrayOf(2,4,5,6,9)
            )
            val closest = arrayOf(3, 3, 3)
            val targets = arrayOf(7, 6, 10)
            for (i in arrays.indices) {
                val closest = KClosestElements().findClosestElements(arrays[i], closest[i], targets[i])
                println("array: ${arrays[i].contentToString()}, closest: ${closest[i]}, target: ${targets[i]}, closest elements: $closest")
            }
        }
    }
}