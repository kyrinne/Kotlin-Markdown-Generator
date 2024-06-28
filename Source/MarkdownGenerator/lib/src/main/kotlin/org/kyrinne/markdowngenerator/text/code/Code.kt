package org.kyrinne.markdowngenerator.text.code

import net.steppschuh.markdowngenerator.text.Text

class Code(value: Any?) : Text(value) {
    override fun getPredecessor(): String {
        return "`"
    }
}
