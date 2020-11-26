package org.dukecon.domain.model

data class Conference(val id: String, val name: String, val url: String, val homeUrl: String, val icon: String,
                      val metaData: MetaData, val events: List<Event>, val speakers: List<Speaker>)
