package org.kyrinne.markdowngenerator.text.emphasis

import net.steppschuh.markdowngenerator.text.Text

class MarkedText(value: Any?) : Text(value) {
    override fun getPredecessor(): String {
        return "=="
    }
}