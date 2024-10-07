class Main {
    fun main() {
        val fileName = "markdown.md"
        var markdownParser = MarkdownParser()
        var nodes = markdownParser.parseCode(fileName)
        for (node in nodes) {
            println(node.toHTML())
        }
    }
}