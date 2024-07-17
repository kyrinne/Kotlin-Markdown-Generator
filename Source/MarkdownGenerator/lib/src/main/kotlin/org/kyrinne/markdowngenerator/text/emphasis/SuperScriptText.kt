package org.kyrinne.markdowngenerator.text.emphasis

import org.kyrinne.markdowngenerator.text.Text

class SuperScriptText(value: Any?) : Text(value) {
    override fun getPredecessor(): String {
        return "^"
    }
}