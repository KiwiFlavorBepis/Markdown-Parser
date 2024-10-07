class MarkdownNode : Node() {

    var type: Int? = null
    /*
        0   Text
        1   Heading 1
        2   Heading 2
        3   Heading 3
        4   Bold text
        5   Italicized text
        6   Block quote
        7   Horizontal line
     */

    override fun toHTML(): String {

        return ""
    }
}