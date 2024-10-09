import java.io.File

fun main(args: Array<String>) {

    val mdFile = args.getOrNull(0) ?: "markdown.md"
    val htmlFile = args.getOrNull(1) ?: "markup.html"

    val markdownParser = MarkdownParser()
    val nodes = markdownParser.parseCode(mdFile)

    writeHTML(nodes, htmlFile)
}

fun writeHTML(html: List<Node>, file: String) {

    File(file).writeText(html.joinToString(separator = "\n") {it.toHTML()})

}
