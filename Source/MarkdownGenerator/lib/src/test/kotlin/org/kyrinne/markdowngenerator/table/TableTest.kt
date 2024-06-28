package org.kyrinne.markdowngenerator.table

import org.junit.jupiter.api.Test

/**
 * Created by steppschuh on 15/12/2016.
 */
class TableTest {
    @Test
    @Throws(java.lang.Exception::class)
    fun example1() {
        val rows = java.util.Arrays.asList<TableRow<*>>(
            TableRow<Any?>(
                mutableListOf<String?>(
                    "Left",
                    "Center",
                    "Right"
                )
            ),
            TableRow<Any?>(
                java.util.Arrays.asList<org.kyrinne.markdowngenerator.text.Text?>(
                    org.kyrinne.markdowngenerator.text.Text("Normal Text"),
                    BoldText("Bold Text"),
                    org.kyrinne.markdowngenerator.text.code.Code("Code Text")
                )
            ),
            TableRow<Any?>(
                mutableListOf<Int?>(
                    1,
                    2,
                    3
                )
            )
        )

        val alignments = java.util.Arrays.asList(
            Table.ALIGN_LEFT,
            Table.ALIGN_CENTER,
            Table.ALIGN_RIGHT
        )

        val table = Table(rows, alignments)
        println(table)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun example2() {
        val tableBuilder = Table.Builder()
            .withAlignments(Table.ALIGN_RIGHT, Table.ALIGN_LEFT)
            .withRowLimit(7)
            .addRow("Index", "Boolean")

        for (i in 1..20) {
            tableBuilder.addRow(i, Math.random() > 0.5)
        }

        println(tableBuilder.build())
    }

    @Test
    fun example3() {
        val tableBuilder = Table.Builder()
            .addRow("Index", "Boolean")

        println(tableBuilder.build())
    }
}