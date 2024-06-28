package org.kyrinne.markdowngenerator.image

import org.junit.jupiter.api.Test
/**
 * Created by steppschuh on 15/12/2016.
 */
class ImageTest {
    @Test
    @Throws(Exception::class)
    fun example1() {
        val text = "I am an image"
        val url = "https://dummyimage.com/300"
        val image = Image(text, url)
        println(image)
    }

    @Test
    @Throws(Exception::class)
    fun example2() {
        val url = "https://dummyimage.com/300"
        val image = Image(url)
        println(image)
    }
}