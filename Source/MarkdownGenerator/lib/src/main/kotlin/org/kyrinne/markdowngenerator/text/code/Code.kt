package org.kyrinne.markdowngenerator.text.code

import org.kyrinne.markdowngenerator.text.Text

class Code(value: Any?) : Text(value) {
    override fun getPredecessor(): String {
        return "`"
    }
}
