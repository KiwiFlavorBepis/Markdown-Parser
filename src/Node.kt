open class Node(var text: String) {
    open fun toHTML(): String {
            return "<p>$text</p>"
    }
}