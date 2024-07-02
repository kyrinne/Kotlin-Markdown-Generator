package org.kyrinne.markdowngenerator.list

import org.junit.jupiter.api.Test
import java.util.*

/**
 * Created by steppschuh on 15/12/2016.
 */
class UnorderedListTest {
    @Test
    @Throws(java.lang.Exception::class)
    fun example1() {
        val items = mutableListOf("Item 1", "Item 2", "Item 3")
        val list: UnorderedList<*> = UnorderedList(items)
        println(list)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun example2() {
        val items = mutableListOf(
            Date(0),
            Date(1337001337),
            Date()
        )
        val list: UnorderedList<*> = UnorderedList(items)
        println(list)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun example3() {
        val items = mutableListOf(
            "Item 1",
            "Item 2",
            UnorderedList(
                mutableListOf(
                    "Item 2.1",
                    "Item 2.2",
                    UnorderedList(
                        mutableListOf(
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