package org.kyrinne.markdowngenerator.table

import net.steppschuh.markdowngenerator.MarkdownElement
import net.steppschuh.markdowngenerator.util.StringUtil
import java.util.*
import kotlin.math.max

class Table() : MarkdownElement() {
    private var rows: MutableList<org.kyrinne.markdowngenerator.table.TableRow<*>>
    private var alignments: List<Int>
    var isFirstRowHeader: Boolean = true
        private set
    private var minimumColumnWidth = org.kyrinne.markdowngenerator.table.Table.Companion.DEFAULT_MINIMUM_COLUMN_WIDTH
    var trimmingIndicator: String = org.kyrinne.markdowngenerator.table.Table.Companion.DEFAULT_TRIMMING_INDICATOR

    class Builder {
        private val table = org.kyrinne.markdowngenerator.table.Table()
        private var rowLimit = 0

        fun withRows(tableRows: MutableList<org.kyrinne.markdowngenerator.table.TableRow<*>>): org.kyrinne.markdowngenerator.table.Table.Builder {
            table.setRows(tableRows)
            return this
        }

        fun addRow(tableRow: org.kyrinne.markdowngenerator.table.TableRow<*>): org.kyrinne.markdowngenerator.table.Table.Builder {
            table.getRows().add(tableRow)
            return this
        }

        fun addRow(vararg objects: Any?): org.kyrinne.markdowngenerator.table.Table.Builder {
            val tableRow: org.kyrinne.markdowngenerator.table.TableRow<*> =
                org.kyrinne.markdowngenerator.table.TableRow(Arrays.asList(*objects))
            table.getRows().add(tableRow)
            return this
        }

        fun withAlignments(alignments: List<Int>): org.kyrinne.markdowngenerator.table.Table.Builder {
            table.setAlignments(alignments)
            return this
        }

        fun withAlignments(vararg alignments: Int?): org.kyrinne.markdowngenerator.table.Table.Builder {
            return withAlignments(Arrays.asList(*alignments))
        }

        fun withAlignment(alignment: Int): org.kyrinne.markdowngenerator.table.Table.Builder {
            return withAlignments(listOf(alignment))
        }

        fun withRowLimit(rowLimit: Int): org.kyrinne.markdowngenerator.table.Table.Builder {
            this.rowLimit = rowLimit
            return this
        }

        fun withTrimmingIndicator(trimmingIndicator: String): org.kyrinne.markdowngenerator.table.Table.Builder {
            table.trimmingIndicator = trimmingIndicator
            return this
        }

        fun build(): org.kyrinne.markdowngenerator.table.Table {
            if (rowLimit > 0) {
                table.trim(rowLimit)
            }
            return table
        }
    }

    init {
        this.rows = ArrayList()
        this.alignments = ArrayList()
    }

    constructor(rows: MutableList<org.kyrinne.markdowngenerator.table.TableRow<*>>) : this() {
        this.rows = rows
    }

    constructor(rows: MutableList<org.kyrinne.markdowngenerator.table.TableRow<*>>, alignments: List<Int>) : this(rows) {
        this.alignments = alignments
    }

    override fun serialize(): String {
        val columnWidths = org.kyrinne.markdowngenerator.table.Table.Companion.getColumnWidths(rows, minimumColumnWidth)

        val sb = StringBuilder()

        val headerSeparator =
            org.kyrinne.markdowngenerator.table.Table.Companion.generateHeaderSeparator(columnWidths, alignments)
        var headerSeperatorAdded = !isFirstRowHeader
        if (!isFirstRowHeader) {
            sb.append(headerSeparator).append(System.lineSeparator())
        }

        for (row in rows) {
            for (columnIndex in 0 until columnWidths.size) {
                sb.append(org.kyrinne.markdowngenerator.table.Table.Companion.SEPARATOR)

                var value = ""
                if (row.columns.size > columnIndex) {
                    val valueObject = row.columns[columnIndex]
                    if (valueObject != null) {
                        value = valueObject.toString()
                    }
                }

                if (value == trimmingIndicator) {
                    value = StringUtil.fillUpLeftAligned(value, trimmingIndicator, columnWidths[columnIndex]!!)
                    value = StringUtil.surroundValueWith(value,
                        org.kyrinne.markdowngenerator.table.Table.Companion.WHITESPACE
                    )
                } else {
                    val alignment =
                        org.kyrinne.markdowngenerator.table.Table.Companion.getAlignment(alignments, columnIndex)
                    value = StringUtil.surroundValueWith(value,
                        org.kyrinne.markdowngenerator.table.Table.Companion.WHITESPACE
                    )
                    value = StringUtil.fillUpAligned(value,
                        org.kyrinne.markdowngenerator.table.Table.Companion.WHITESPACE, columnWidths[columnIndex]!! + 2, alignment)
                }

                sb.append(value)

                if (columnIndex == row.columns.size - 1) {
                    sb.append(org.kyrinne.markdowngenerator.table.Table.Companion.SEPARATOR)
                }
            }

            if (rows.indexOf(row) < rows.size - 1 || rows.size == 1) {
                sb.append(System.lineSeparator())
            }

            if (!headerSeperatorAdded) {
                sb.append(headerSeparator).append(System.lineSeparator())
                headerSeperatorAdded = true
            }
        }
        return sb.toString()
    }

    /**
     * Removes [TableRow]s from the center of this table until only the requested amount of
     * rows is left.
     *
     * @param rowsToKeep Amount of [TableRow]s that should not be removed
     * @return the trimmed table
     */
    fun trim(rowsToKeep: Int): org.kyrinne.markdowngenerator.table.Table {
        rows = org.kyrinne.markdowngenerator.table.Table.Companion.trim(this, rowsToKeep, trimmingIndicator).getRows()
        return this
    }

    fun getRows(): MutableList<org.kyrinne.markdowngenerator.table.TableRow<*>> {
        return rows
    }

    fun setRows(rows: MutableList<org.kyrinne.markdowngenerator.table.TableRow<*>>) {
        this.rows = rows
        invalidateSerialized()
    }

    fun getAlignments(): List<Int> {
        return alignments
    }

    fun setAlignments(alignments: List<Int>) {
        this.alignments = alignments
        invalidateSerialized()
    }

    fun useFirstRowAsHeader(firstRowIsHeader: Boolean) {
        this.isFirstRowHeader = firstRowIsHeader
        invalidateSerialized()
    }

    fun getMinimumColumnWidth(): Int {
        return minimumColumnWidth
    }

    fun setMinimumColumnWidth(minimumColumnWidth: Int) {
        this.minimumColumnWidth = minimumColumnWidth
        invalidateSerialized()
    }

    companion object {
        const val SEPARATOR: String = "|"
        const val WHITESPACE: String = " "
        const val DEFAULT_TRIMMING_INDICATOR: String = "~"
        const val DEFAULT_MINIMUM_COLUMN_WIDTH: Int = 3

        const val ALIGN_CENTER: Int = 1
        const val ALIGN_LEFT: Int = 2
        const val ALIGN_RIGHT: Int = 3

        /**
         * Removes [TableRow]s from the center of the specified table until only the requested
         * amount of rows is left.
         *
         * @param table             Table to remove [TableRow]s from
         * @param rowsToKeep        Amount of [TableRow]s that should not be removed
         * @param trimmingIndicator The content that trimmed cells should be filled with
         * @return The trimmed table
         */
        fun trim(table: org.kyrinne.markdowngenerator.table.Table, rowsToKeep: Int, trimmingIndicator: String?): org.kyrinne.markdowngenerator.table.Table {
            if (table.getRows().size <= rowsToKeep) {
                return table
            }

            val trimmedEntriesCount = table.getRows().size - (table.getRows().size - rowsToKeep)
            val trimmingStartIndex = Math.round((trimmedEntriesCount / 2).toFloat()) + 1
            val trimmingStopIndex = table.getRows().size - trimmingStartIndex

            val trimmedRows: MutableList<org.kyrinne.markdowngenerator.table.TableRow<*>> = ArrayList()
            for (i in trimmingStartIndex..trimmingStopIndex) {
                trimmedRows.add(table.getRows()[i])
            }

            table.getRows().removeAll(trimmedRows)

            val trimmingIndicatorRow: org.kyrinne.markdowngenerator.table.TableRow<*> =
                org.kyrinne.markdowngenerator.table.TableRow<Any?>()
            for (columnIndex in table.getRows()[0].columns.indices) {
                trimmingIndicatorRow.columns.add(trimmingIndicator)
            }
            table.getRows().add(trimmingStartIndex, trimmingIndicatorRow)

            return table
        }

        fun generateHeaderSeparator(columnWidths: Map<Int, Int>, alignments: List<Int>): String {
            val sb = StringBuilder()
            for (columnIndex in columnWidths.entries.indices) {
                sb.append(org.kyrinne.markdowngenerator.table.Table.Companion.SEPARATOR)

                var value = StringUtil.fillUpLeftAligned("", "-", columnWidths[columnIndex]!!)

                val alignment =
                    org.kyrinne.markdowngenerator.table.Table.Companion.getAlignment(alignments, columnIndex)
                value = when (alignment) {
                    org.kyrinne.markdowngenerator.table.Table.Companion.ALIGN_RIGHT -> {
                        org.kyrinne.markdowngenerator.table.Table.Companion.WHITESPACE + value + ":"
                    }

                    org.kyrinne.markdowngenerator.table.Table.Companion.ALIGN_CENTER -> {
                        ":$value:"
                    }

                    else -> {
                        StringUtil.surroundValueWith(
                            value,
                            org.kyrinne.markdowngenerator.table.Table.Companion.WHITESPACE
                        )
                    }
                }
                sb.append(value)
                if (columnIndex == columnWidths.entries.size - 1) {
                    sb.append(org.kyrinne.markdowngenerator.table.Table.Companion.SEPARATOR)
                }
            }
            return sb.toString()
        }

        fun getColumnWidths(rows: List<org.kyrinne.markdowngenerator.table.TableRow<*>>, minimumColumnWidth: Int): Map<Int, Int> {
            val columnWidths: MutableMap<Int, Int> = HashMap()
            if (rows.isEmpty()) {
                return columnWidths
            }
            for (columnIndex in rows[0].columns.indices) {
                columnWidths[columnIndex] = org.kyrinne.markdowngenerator.table.Table.Companion.getMaximumItemLength(
                    rows,
                    columnIndex,
                    minimumColumnWidth
                )
            }
            return columnWidths
        }

        fun getMaximumItemLength(rows: List<org.kyrinne.markdowngenerator.table.TableRow<*>>, columnIndex: Int, minimumColumnWidth: Int): Int {
            var maximum = minimumColumnWidth
            for (row in rows) {
                if (row.columns.size < columnIndex + 1) {
                    continue
                }
                val value = row.columns[columnIndex] ?: continue
                maximum = max(value.toString().length.toDouble(), maximum.toDouble()).toInt()
            }
            return maximum
        }

        fun getAlignment(alignments: List<Int>, columnIndex: Int): Int {
            var columnIndex = columnIndex
            if (alignments.isEmpty()) {
                return org.kyrinne.markdowngenerator.table.Table.Companion.ALIGN_LEFT
            }
            if (columnIndex >= alignments.size) {
                columnIndex = alignments.size - 1
            }
            return alignments[columnIndex]
        }
    }
}
