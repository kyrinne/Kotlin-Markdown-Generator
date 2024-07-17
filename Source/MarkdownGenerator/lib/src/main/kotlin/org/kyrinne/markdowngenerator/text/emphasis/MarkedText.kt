package org.kyrinne.markdowngenerator.text.emphasis

import org.kyrinne.markdowngenerator.text.Text


class MarkedText(value: Any?) : Text(value) {
    override fun getPredecessor(): String {
        return "=="
    }
}