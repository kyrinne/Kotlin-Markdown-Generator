package org.kyrinne.markdowngenerator.text.code

import org.kyrinne.markdowngenerator.text.Text

class Code(value: Any?) : Text(value) {
    fun getPredecessor(): String {
        return "`"
    }
}
