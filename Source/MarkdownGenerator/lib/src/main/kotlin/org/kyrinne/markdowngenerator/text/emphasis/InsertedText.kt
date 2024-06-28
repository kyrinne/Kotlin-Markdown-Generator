package org.kyrinne.markdowngenerator.text.emphasis

import net.steppschuh.markdowngenerator.text.Text

class InsertedText(value: Any?) : Text(value) {
    override fun getPredecessor(): String {
        return "++"
    }
}