rootProject.name = "dukecon-mobile"

include(":shared:core")
include(":shared:domain")
include(":shared:data")
include(":shared:mvvm")
//include(":shared:backend:sessionize")
include(":shared:backend:dukecon")
include(":shared:backend:macoun")

include (":frontend:jetDukecon")
include (":frontend:desktop")
include (":frontend:web")
include (":shared:conference:javaland")
//include (":shared:DukeconSdk")