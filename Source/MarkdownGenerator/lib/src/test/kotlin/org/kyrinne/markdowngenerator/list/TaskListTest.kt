package org.kyrinne.markdowngenerator.list

import org.junit.jupiter.api.Test

/**
 * Created by steppschuh on 15/12/2016.
 */
class TaskListTest {
    @Test
    @Throws(java.lang.Exception::class)
    fun example1() {
        val items = listOf(
            TaskListItem("Task 1", true),
            TaskListItem("Task 2", false),
            TaskListItem("Task 3")
        )
        val list = TaskList(items)
        println(list)
    }
}