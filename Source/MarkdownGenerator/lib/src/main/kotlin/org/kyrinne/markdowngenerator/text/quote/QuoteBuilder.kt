package org.kyrinne.markdowngenerator.text.quote

import net.steppschuh.markdowngenerator.MarkdownBuilder

/**
 * Created by Stephan on 12/25/2016.
 */
class QuoteBuilder : MarkdownBuilder<QuoteBuilder?, Quote?> {
    constructor() : super()

    constructor(parentBuilder: MarkdownBuilder<*, *>?) : super(parentBuilder)

    override fun getBuilder(): QuoteBuilder {
        return this
    }

    override fun createMarkdownElement(): Quote {
        return Quote("")
    }

    override fun append(value: Any): QuoteBuilder {
        markdownElement!!.value = StringBuilder()
            .append(markdownElement!!.value)
            .append(value)
            .toString()
        return this
    }
}
