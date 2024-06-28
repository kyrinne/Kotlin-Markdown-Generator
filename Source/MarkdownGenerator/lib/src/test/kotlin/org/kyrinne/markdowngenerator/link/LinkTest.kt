package org.kyrinne.markdowngenerator.link

import org.junit.jupiter.api.Test

/**
 * Created by steppschuh on 15/12/2016.
 */
class LinkTest {
    @Test
    @Throws(Exception::class)
    fun example1() {
        val text = "I am a link"
        val url = "http://steppschuh.net"
        val link = Link(text, url)
        println(link)
    }

    @Test
    @Throws(Exception::class)
    fun example2() {
        val url = "http://steppschuh.net"
        val link = Link(url)
        println(link)
    }
}