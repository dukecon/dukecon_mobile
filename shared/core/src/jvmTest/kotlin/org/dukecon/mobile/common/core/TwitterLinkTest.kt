package org.dukecon.mobile.common.core

import org.dukecon.aspects.twitter.TwitterLinks
import kotlin.test.Test
import kotlin.test.assertTrue

class TwitterLinksTest {

  @Test
  fun simpleTwitterLinkTest() {
    assertTrue(
        TwitterLinks().getHandle("https://twitter.com/kotlin").contains("kotlin"),
        "Check kotlin mentioned")
  }
}
