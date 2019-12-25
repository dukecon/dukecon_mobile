package org.dukecon.macoun.mapper

class TwitterLinks {

    fun getNormalizedTwitterUrl(twitter: String): String {
        if (twitter.length > 0) {
            if (twitter.startsWith("@")) {
                return "https://twitter.com/${twitter.substring(1)}"
            } else {
                //https://mathiasbynens.be/demo/url-regex
                // @rodneyrehm (109 chars)
                val twiterRegEx = "((https?://|ftp://|www\\.|[^\\s:=]+@www\\.).*?[a-z_\\/0-9\\-\\#=&])(?=(\\.|,|;|\\?|\\!)?(\"|'|«|»|\\[|\\s|\\r|\\n|\$))".toRegex()
                if (twiterRegEx.matches(twitter)) {
                    return twitter
                } else {
                    return "https://twitter.com/$twitter"
                }
            }
        }
        return twitter
    }
}