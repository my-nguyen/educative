package practice2.top_k_elements

import java.util.*

class KClosestPointsToOrigin {
    data class Point(var x: Int = 0, var y: Int = 0)

    fun findClosestPoints(points: Array<Point>, k: Int): List<Point> {
        return listOf()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1,2), intArrayOf(1,3)),
                arrayOf(intArrayOf(1,3), intArrayOf(3,4), intArrayOf(2,-1)),
                arrayOf(intArrayOf(1,1), intArrayOf(-1,-1), intArrayOf(0,2), intArrayOf(0,1), intArrayOf(1,2)),
                arrayOf(intArrayOf(1,1), intArrayOf(-1,-1), intArrayOf(0,2), intArrayOf(0,1), intArrayOf(1,2)),
                arrayOf(intArrayOf(1,1), intArrayOf(-1,-1), intArrayOf(0,2), intArrayOf(0,1), intArrayOf(1,2)),
                arrayOf(intArrayOf(1,1), intArrayOf(-1,-1), intArrayOf(0,2), intArrayOf(0,1), intArrayOf(1,2)),
            )
            val Ks = arrayOf(1, 2, 1, 2, 3, 4)
            for (i in arrays.indices) {
                val points = Array(arrays[i].size) { Point() }
                for (j in arrays[i].indices) {
                    points[j] = Point(arrays[i][j][0], arrays[i][j][1])
                }
                val closest = KClosestPointsToOrigin().findClosestPoints(points, Ks[i])
                println("points: ${points.contentToString()}, K: ${Ks[i]}, closest: $closest")
            }
        }
    }
}