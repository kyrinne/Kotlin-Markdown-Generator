package org.kyrinne.markdowngenerator.list

import net.steppschuh.markdowngenerator.MarkdownBuilder
import net.steppschuh.markdowngenerator.MarkdownSerializable

/**
 * Created by steppschuh on 23/12/2016.
 */
class ListBuilder : MarkdownBuilder<ListBuilder?, UnorderedList<*>?> {
    constructor() : super()

    constructor(parentBuilder: MarkdownBuilder<*, *>?) : super(parentBuilder)

    override fun getBuilder(): ListBuilder {
        return this
    }

    override fun createMarkdownElement(): UnorderedList<*> {
        return UnorderedList<Any?>()
    }

    override fun append(value: Any): ListBuilder {
        markdownElement!!.getItems().add(value)
        return this
    }

    override fun append(value: MarkdownSerializable): ListBuilder {
        if (value is ListBuilder) {
            val unorderedList = value.markdownElement
            unorderedList!!.incrementIndentationLevel()
            markdownElement!!.getItems().add(unorderedList)
            return this
        }
        return super.append(value)!!
    }
}
