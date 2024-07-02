package org.kyrinne.markdowngenerator

import org.kyrinne.markdowngenerator.table.TableRow
import java.util.*

/**
 * Created by steppschuh on 15/12/2016.
 */
class DummyObject : MarkdownSerializable {
    var foo: Any
        private set
    var bar: String
        private set
    var baz: Int
        private set

    constructor() {
        this.foo = true
        this.bar = "qux"
        this.baz = 1337
    }

    constructor(foo: Any, bar: String, baz: Int) {
        this.foo = foo
        this.bar = bar
        this.baz = baz
    }

    fun toMarkdownTableRow(): TableRow<*> {
        return TableRow(
            Arrays.asList(
                foo, bar, baz
            )
        )
    }

    @Throws(MarkdownSerializationException::class)
    override fun toMarkdownElement(): MarkdownElement {
        return toMarkdownTableRow()
    }

    override fun toString(): String {
        return try {
            toMarkdownElement().toString()
        } catch (e: MarkdownSerializationException) {
            this.javaClass.simpleName
        }
    }
}
