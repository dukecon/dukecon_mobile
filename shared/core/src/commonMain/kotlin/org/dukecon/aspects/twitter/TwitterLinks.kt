package org.dukecon.aspects.twitter

class TwitterLinks {

  fun getNormalizedTwitterUrl(twitter: String): String {
    if (twitter.isNotEmpty()) {
      return if (twitter.startsWith("@")) {
        "https://twitter.com/${twitter.substring(1)}"
      } else {
        // https://mathiasbynens.be/demo/url-regex
        // @rodneyrehm (109 chars)
        val twiterRegEx =
            "((https?://|ftp://|www\\.|[^\\s:=]+@www\\.).*?[a-z_\\/0-9\\-\\#=&])(?=(\\.|,|;|\\?|\\!)?(\"|'|«|»|\\[|\\s|\\r|\\n|\$))".toRegex()
        if (twiterRegEx.matches(twitter)) {
          twitter
        } else {
          "https://twitter.com/$twitter"
        }
      }
    }
    return twitter
  }

  fun getHandle(twitter: String): String {
    if (twitter.isNotEmpty()) {
      if (twitter.startsWith("@")) {
        return twitter
      } else {
        // @rodneyrehm (109 chars)
        val twiterRegEx =
            "((https?://|ftp://|www\\.|[^\\s:=]+@www\\.).*?[a-z_\\/0-9\\-\\#=&])(?=(\\.|,|;|\\?|\\!)?(\"|'|«|»|\\[|\\s|\\r|\\n|\$))".toRegex()
        if (twiterRegEx.matches(twitter)) {
          val a = "@${twitter.substring(twitter.lastIndexOf("/") + 1)}"
          if ("@".equals(a)) {
            return ""
          }
          return a
        } else {
          return "@$twitter"
        }
      }
    }
    return twitter
  }
}
