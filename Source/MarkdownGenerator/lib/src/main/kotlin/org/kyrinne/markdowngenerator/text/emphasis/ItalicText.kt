package org.kyrinne.markdowngenerator.text.emphasis

import org.kyrinne.markdowngenerator.text.Text

class ItalicText(value: Any?) : Text(value) {
    fun getPredecessor(): String {
        return "_"
    }
}
