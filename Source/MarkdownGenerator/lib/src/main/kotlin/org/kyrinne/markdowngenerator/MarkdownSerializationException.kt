package org.kyrinne.markdowngenerator

/**
 * Created by steppschuh on 15/12/2016.
 */
class MarkdownSerializationException : Exception {
    constructor() : super()

    constructor(s: String?) : super(s)

    constructor(s: String?, throwable: Throwable?) : super(s, throwable)

    constructor(throwable: Throwable?) : super(throwable)
}
