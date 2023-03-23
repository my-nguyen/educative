package patterns_for_coding_questions._13_top_k_elements

import java.util.*

class KClosestPointsToTheOrigin {
    data class Point(val x: Int, val y: Int) {
        val distance = x*x + y*y
    }

    fun findClosestPoints(points: Array<Point>, k: Int): List<Point> {
        val maxHeap = PriorityQueue<Point> { a, b -> b.distance - a.distance }
        for (i in 0 until k) {
            maxHeap.offer(points[i])
        }

        for (i in k..points.lastIndex) {
            if (points[i].distance < maxHeap.peek().distance) {
                maxHeap.poll()
                maxHeap.offer(points[i])
            }
        }
        return maxHeap.toList()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1,2), intArrayOf(1,3)),
                arrayOf(intArrayOf(1,3), intArrayOf(3,4), intArrayOf(2,-1))
            )
            val Ks = arrayOf(1, 2)
            for (i in arrays.indices) {
                val points = mutableListOf<Point>()
                for (array in arrays[i]) {
                    val point = Point(array[0], array[1])
                    points.add(point)
                }
                val closest = KClosestPointsToTheOrigin().findClosestPoints(points.toTypedArray(), Ks[i])
                println("points: $points, K: ${Ks[i]}, closest: $closest")
            }
        }
    }
}