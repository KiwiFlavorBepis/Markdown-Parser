import java.io.File

class MarkdownParser : CodeParser {
    override fun parseCode(fileName: String): List<MarkdownNode> {
        val nodeList = mutableListOf<MarkdownNode>()
        File(fileName).forEachLine {
            nodeList.add(MarkdownNode(it))
        }
        return nodeList
    }
}