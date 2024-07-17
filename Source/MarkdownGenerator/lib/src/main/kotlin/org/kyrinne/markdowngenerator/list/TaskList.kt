package org.kyrinne.markdowngenerator.list

class TaskList : UnorderedList<TaskListItem?> {
    constructor() {
        this.items = ArrayList()
    }

    constructor(items: List<TaskListItem?>) {
        this.items = items.toMutableList()
    }
}
