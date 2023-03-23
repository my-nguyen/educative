package patterns_for_coding_questions._4_merge_intervals

import java.util.*

class MaximumCPULoad {
    data class Job(val start: Int, val end: Int, val cpuLoad: Int) {
        override fun toString() = "[$start, $end, $cpuLoad]"
    }

    fun findMaxCPULoad(jobs: MutableList<Job>): Int {
        // return mine(jobs)
        return educative(jobs)
    }

    // from educative
    // The problem follows the Merge Intervals pattern and can easily be converted to Minimum Meeting Rooms. Similar to
    // ‘Minimum Meeting Rooms’ where we were trying to find the maximum number of meetings happening at any time, for
    // ‘Maximum CPU Load’ we need to find the maximum number of jobs running at any time. We will need to keep a
    // running count of the maximum CPU load at any time to find the overall maximum load.
    private fun educative(jobs: MutableList<Job>): Int {
        jobs.sortWith { j1, j2 -> j1.start - j2.start }

        var maxLoad = 0
        var currentLoad = 0
        val minHeap = PriorityQueue<Job>(jobs.size) { j1, j2 -> j1.end - j2.end }
        for (job in jobs) {
            // remove all jobs that have ended
            while (minHeap.isNotEmpty() && minHeap.peek().end < job.start) {
                currentLoad -= minHeap.poll().cpuLoad
            }

            // add the current job into minHeap
            minHeap.offer(job)
            currentLoad += job.cpuLoad
            maxLoad = maxOf(maxLoad, currentLoad)
        }
        return maxLoad
    }

    private fun mine(jobs: MutableList<Job>): Int {
        jobs.sortWith { j1, j2 -> j1.start - j2.start }
        var max = jobs[0].cpuLoad
        for (i in 0 until jobs.lastIndex) {
            for (j in i+1..jobs.lastIndex) {
                val cpuLoad = if (jobs[i].end > jobs[j].start) {
                    jobs[i].cpuLoad + jobs[j].cpuLoad
                } else {
                    jobs[j].cpuLoad
                }
                max = maxOf(max, cpuLoad)
            }
        }
        return max
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arrays = arrayOf(
                arrayOf(intArrayOf(1,4,3), intArrayOf(2,5,4), intArrayOf(7,9,6)),
                arrayOf(intArrayOf(6,7,10), intArrayOf(2,4,11), intArrayOf(8,12,15)),
                arrayOf(intArrayOf(1,4,2), intArrayOf(2,4,1), intArrayOf(3,6,5))
            )
            for (array in arrays) {
                val jobs = mutableListOf<Job>()
                for (triple in array) {
                    val job = Job(triple[0], triple[1], triple[2])
                    jobs.add(job)
                }
                print("jobs: $jobs")
                val cpuLoad = MaximumCPULoad().findMaxCPULoad(jobs)
                println(", sorted: $jobs, max CPU load: $cpuLoad")
            }
        }
    }
}