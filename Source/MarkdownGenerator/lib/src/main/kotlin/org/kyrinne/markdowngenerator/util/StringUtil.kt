package org.kyrinne.markdowngenerator.util

import net.steppschuh.markdowngenerator.table.Table

/**
 * Created by steppschuh on 15/12/2016.
 */
object StringUtil {
    fun fillUpAligned(value: String, fill: String, length: Int, alignment: Int): String {
        return when (alignment) {
            Table.ALIGN_RIGHT -> {
                fillUpRightAligned(value, fill, length)
            }

            Table.ALIGN_CENTER -> {
                fillUpCenterAligned(value, fill, length)
            }

            else -> {
                fillUpLeftAligned(value, fill, length)
            }
        }
    }

    fun fillUpLeftAligned(value: String, fill: String, length: Int): String {
        var value = value
        if (value.length >= length) {
            return value
        }
        while (value.length < length) {
            value += fill
        }
        return value
    }

    fun fillUpRightAligned(value: String, fill: String, length: Int): String {
        var value = value
        if (value.length >= length) {
            return value
        }
        while (value.length < length) {
            value = fill + value
        }
        return value
    }

    fun fillUpCenterAligned(value: String, fill: String, length: Int): String {
        var value = value
        if (value.length >= length) {
            return value
        }
        var left = true
        while (value.length < length) {
            value = if (left) {
                fillUpLeftAligned(value, fill, value.length + 1)
            } else {
                fillUpRightAligned(value, fill, value.length + 1)
            }
            left = !left
        }
        return value
    }

    fun surroundValueWith(value: String, surrounding: String): String {
        return surrounding + value + surrounding
    }
}
