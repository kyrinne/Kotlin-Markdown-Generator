package org.kyrinne.markdowngenerator.text.emphasis

import org.kyrinne.markdowngenerator.text.Text

class StrikeThroughText(value: Any?) : Text(value) {
    fun getPredecessor(): String {
        return "~~"
    }
}
