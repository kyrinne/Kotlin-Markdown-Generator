package org.kyrinne.markdowngenerator.list

import org.junit.jupiter.api.Test

/**
 * Created by steppschuh on 15/12/2016.
 */
class UnorderedListTest {
    @Test
    @Throws(java.lang.Exception::class)
    fun example1() {
        val items: List<String> = mutableListOf("Item 1", "Item 2", "Item 3")
        val list: UnorderedList<*> = UnorderedList<String>(items)
        println(list)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun example2() {
        val items = java.util.Arrays.asList(
            java.util.Date(0),
            java.util.Date(1337001337),
            java.util.Date()
        )
        val list: UnorderedList<*> = UnorderedList(items)
        println(list)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun example3() {
        val items = java.util.Arrays.asList(
            "Item 1",
            "Item 2",
            UnorderedList(
                java.util.Arrays.asList(
                    "Item 2.1",
                    "Item 2.2",
                    UnorderedList(
                        java.util.Arrays.asList(
                            "Item 2.2.1",
                            "Item 2.2.2",
                            UnorderedList(
                                mutableListOf(
                                    "Item 2.2.2.1",
                                    "Item 2.2.2.2",
                                    "Item 2.2.2.3"
                                )
                            ),
                            "Item 2.2.3"
                        )
                    ),
                    "Item 2.3"
                )
            ),
            "Item 3"
        )
        val list: UnorderedList<*> = UnorderedList(items)
        println(list)
    }
}