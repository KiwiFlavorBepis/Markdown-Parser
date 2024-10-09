class MarkdownNode(text: String) : Node(text) {

    var type: Int
    /*
        0   Text
        1   Heading 1
        2   Heading 2
        3   Heading 3
        4   Italic
        5   Bold
        6   Block quote
        7   Horizontal line
     */

    init {
        //On creation, determine the type of Markdown and grab the text from inside the Markdown formatting
        type = parse()
    }

    /**
     * Formats the text of the MarkdownNode as HTML based on the type of the MarkdownNode
     *
     * @return A string in HTML format
     */
    override fun toHTML(): String {
        when(type) {
            0   ->  {   return  "<p>$text</p>"    }
            1   ->  {   return  "<h1>$text</h1>"    }
            2   ->  {   return  "<h2>$text</h2>"    }
            3   ->  {   return  "<h3>$text</h3>"    }
            4   ->  {   return  "<p><i>$text</i></p>"  }
            5   ->  {   return  "<p><b>$text</b></p>"  }
            6   ->  {   return  "<blockquote>$text</blockquote>"    }
            7   ->  {   return  "<hr>"  }
        }
        return ""
    }

    /**
     * Uses regex to match Markdown formatting and determine, and strips the formatting
     * This method changes the MarkdownNode's text property
     *
     * @return The type code of the Markdown formatting
     */
    private fun parse() : Int {

        val heading = Regex("^(#{1,3})\\s(.*)")  // Matches with 1-3 leading '#'s, a whitespace, and anything after
                                                        // Returns both the '#'s (Group 1) and everything after the whitespace (Group 2)

        val asterisk = Regex("^(\\*{1,2})(.*)\\1\\s*\$")    // Matches with 1-2 leading '*', anything or nothing, and a trailing Group 1 and whitespace or nothing
                                                                    // Returns the 1-2 '*' (Group 1) and the anything or nothing (Group 2)

        val blockquote = Regex("^>\\s(.+)")     // Matches a leading '>', a whitespace, and anything or nothing after
                                                       // Returns the anything or nothing (Group 1)

        val horizontal = Regex("^---\\s*\$")    //Matches a leading '---', and a trailing whitespace or nothing


        /**
         * Combines some operations that both the asterisk and heading regex matches need
         *
         * @param matchGroups The groups returned by a regex.matchEntire(String). Assumed to have 2 groups
         * @return The number of characters in the first group ('*'s or '#'s) (Is only ever 1, 2, or 3)
         */
        fun multiParser(matchGroups: MatchGroupCollection) : Int { //Used for the repetition dependent formats (Headings, Italics, Bolds)
            val level = matchGroups[1]!!.value.length //Asserting non-null guaranteed by regex.matches(text) == true
            text = matchGroups[2]!!.value
            return level
        }

        //Heading
        if (heading.matches(text)) {
            return multiParser(heading.matchEntire(text)!!.groups) //Asserting non-null guaranteed by heading.matches(text) == true
        }

        //Bold and Italic
        if (asterisk.matches(text)) {
            return multiParser(asterisk.matchEntire(text)!!.groups) + 3 //Asserting non-null guaranteed by asterisk.matches(text) == true
        }

        //Block Quote
        if (blockquote.matches(text)) {
            text = blockquote.matchEntire(text)!!.groups[1]!!.value //Asserting non-null guaranteed by blockquote.matches(text) == true
            return 6
        }

        //Horizontal Line
        if (horizontal.matches(text)) return 7

        //Regular Text
        return 0
    }
}