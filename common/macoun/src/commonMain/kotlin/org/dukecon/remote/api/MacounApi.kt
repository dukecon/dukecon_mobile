package org.dukecon.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.put
import io.ktor.http.HttpHeaders
import io.ktor.http.takeFrom
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json

/**
 * use default http engine by default
 */
class MacounApi(private val endpoint: String, val conference: String, engine: HttpClientEngine? = null) {
    @UseExperimental(UnstableDefault::class)

    private val client = engine?.let {
        HttpClient(engine) {
            config()
        }
    } ?: HttpClient() {
        config()
    }

    private fun HttpClientConfig<*>.config() {
        /*
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json.nonstrict).apply {
                setMapper(Conference::class, Conference.serializer())
                setMapper(Talk::class, Talk.serializer())
            }
        }*/
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    /**
     * returns list of conferences
     *
     * @return successful operation
     */
    suspend fun getAllConferences(
    ): List<Conference> {
        return client.get<List<Conference>>("$endpoint/conferences") {
        }
    }

    /**
     * returns conference
     *
     * @param id null
     *
     * @return successful operation
     */


    suspend fun getConferenceStr(
            id: String // PATH
    ): String {
        return """{
    "dtd": "https://dtd.macoun.de/fahrplan/json/v1",
    "conference": "Macoun 2019",
    "year": 2019,
    "breakingnews": "",
    "speakers": [
        {
            "id": "ChrisHauser",
            "firstname": "Chris",
            "lastname": "Hauser",
            "image": "https://macoun.de/img/team/chrishauser.jpg",
            "twitter": "@doktorhauser",
            "description": "kümmert sich um die Organisation und Planung. Zudem leitet er die Akquise der Referenten und Vorträge. Von ihm stammten Idee und Konzept."
        },
        {
            "id": "ThomasBiedorf",
            "firstname": "Thomas",
            "lastname": "Biedorf",
            "image": "https://macoun.de/img/team/thomasbiedorf.jpg",
            "twitter": "@eltom",
            "description": "arbeitet im Konzerndatenschutz der Deutschen Bahn und veranstaltet mit Chris Hauser zusammen die Macoun."
        },
        {
            "id": "NinaHauser",
            "firstname": "Nina",
            "lastname": "Hauser",
            "image": "https://macoun.de/img/team/ninahauser.jpg",
            "twitter": "@GitTyCat42",
            "description": "kümmert sich um die Organisation und Planung. Außerdem spielt sie dieses Jahr die Kindergärtnerin im Playground."
        },
        {
            "id": "OrtwinGentz",
            "firstname": "Ortwin",
            "lastname": "Gentz",
            "image": "https://macoun.de/img/speaker/ortwingentz.jpg",
            "twitter": "@ortwingentz",
            "description": "leitet die Firma FutureTap bei München. Dort entwickelt er u.a. den preisgekrönten Location-Finder Where To?\/Wohin?. Sein liebstes Steckenpferd ist die UI-Perfektionierung."
        },
        {
            "id": "PeterMaurer",
            "firstname": "Peter",
            "lastname": "Maurer",
            "twitter": "@petermaurer",
            "image": "https://macoun.de/img/team/petermaurer.jpg",
            "description": "verstärkt die Orga. Er ist hinter den Kulissen tätig und organisiert die Code Werkstatt."
        },
        {
            "id": "FelixSchlegel",
            "firstname": "Felix",
            "lastname": "Schlegel",
            "image": "https://macoun.de/img/speaker/felixschlegel.jpg",
            "twitter": "@felixschlegel_",
            "description": "Felix Schlegel ist mit seinen grade einmal 17 Jahren schon zweifacher WWDC Scholarship Winner und beschäftigt sich vor allem mit AR und Machine Learning."
        },
        {
            "id": "FriedrichRuynat",
            "firstname": "Friedrich",
            "lastname": "Ruynat",
            "image": "https://macoun.de/img/speaker/friedrichruynat.jpg",
            "twitter": "@hdrxs",
            "description": "Friedrich Ruynat arbeitet seit 2012 als einer der mac- und iOS-Entwickler an dem Schreibprogramm Ulysses."
        },
        {
            "id": "NikolajSchumacher",
            "firstname": "Nikolaj",
            "lastname": "Schumacher",
            "image": "https://macoun.de/img/speaker/nikolajschumacher.jpg",
            "twitter": "@nschum",
            "description": "Nikolaj Schumacher ist Swift-Fan und -Skeptiker der ersten Stunde. Bei JetBrains ist er heute für den Swift-Support in AppCode und CLion verantwortlich."
        },
        {
            "id": "BenjaminBoecker",
            "firstname": "Benjamin",
            "lastname": "Böcker",
            "image": "https://macoun.de/img/speaker/benjaminboecker.jpg",
            "twitter": "@BenBoecker",
            "description": "Geboren und wohnhaft ist Benjamin Böcker in Münster und ist über die Geo- in der Medieninformatik gelandet. Früher Hobby-iOS-Entwickler, heute freiberuflicher Softwareentwickler in den Diensten des Palasthotels."
        },
        {
            "id": "FlorianLuecke",
            "firstname": "Florian",
            "lastname": "Lücke",
            "image": "https://macoun.de/img/speaker/florianluecke.jpg",
            "twitter": "@",
            "description": "Florian Lücke ist seit 2017 Entwickler für iOS und macOS bei Ulysses. Dort kümmert er sich unter anderem um Accessibility und verschiedene Projekte rund um Continuous Integration."
        },
        {
            "id": "SebastianMessingfeld",
            "firstname": "Sebastian",
            "lastname": "Messingfeld",
            "image": "https://macoun.de/img/speaker/sebastianmessingfeld.jpg",
            "twitter": "@_messeb",
            "description": "Sebastian ist langjähriger iOS Entwickler und bei inovex in Köln tätig. Neben der iOS Entwicklung hat er in Kundenprojekten häufig den Hut auf, sich auch um die kontinuierliche Bereitstellung der iOS App zu kümmern."
        },
        {
            "id": "OliverBayer",
            "firstname": "Oliver",
            "lastname": "Bayer",
            "image": "https://macoun.de/img/speaker/oliverbayer.jpg",
            "twitter": "@",
            "description": "Oliver Bayer ist iOS Entwickler seit iPhone OS 2.0. Seit mehr als 7 Jahren ist er bei der inovex GmbH und unterstützt Kunden bei der Erstellung von iOS/tvOS Projekten."
        },
        {
            "id": "OliverBoehm",
            "firstname": "Oliver",
            "lastname": "Boehm",
            "image": "https://macoun.de/img/speaker/oliverboehm.jpg",
            "twitter": "@",
            "description": "Oliver Böhm ist Software-Archäologe, Gelegenheitsdozent und Buch-Autor. Über Apple und Lisa lernte er die Höhen und Tiefen der Programmierung kennen. Nach dem Studium an der Uni Stuttgart ist er hauptsächlich im JEE-Umfeld tätig und öfters auf Konferenzen und Meetups anzutreffen."
        },
        {
            "id": "MarcelWeiher",
            "firstname": "Marcel",
            "lastname": "Weiher",
            "image": "https://macoun.de/img/speaker/marcelweiher.jpg",
            "twitter": "@mpweiher",
            "description": "Marcel ist Programmierer und Autor der schon in der NeXT Community durch merkwürdige Thesen aufgefallen ist. Zwischen diversen Startups hat er auch das Corporate Life bei Apple der BBC und Microsoft genossen."
        },
        {
            "id": "MatthiasKrauss",
            "firstname": "Matthias",
            "lastname": "Krauß",
            "image": "https://macoun.de/img/speaker/matthiaskrauss.jpg",
            "twitter": "@mattikra",
            "description": "Tim Becker und Matthias Krauß sind Soft- und Hardwareentwickler, Prototypenbauer und Bastler. Gemeinsam betreiben Sie die Press Every Key UG in Köln, in der sie Prototypen und neue Technologien entwickeln."
        },
        {
            "id": "TimBecker",
            "firstname": "Tim",
            "lastname": "Becker",
            "image": "https://macoun.de/img/speaker/timbecker.jpg",
            "twitter": "@a2800276",
            "description": "Tim Becker und Matthias Krauß sind Soft- und Hardwareentwickler, Prototypenbauer und Bastler. Gemeinsam betreiben Sie die Press Every Key UG in Köln, in der sie Prototypen und neue Technologien entwickeln."
        },
        {
            "id": "MaxSeelemann",
            "firstname": "Max",
            "lastname": "Seelemann",
            "image": "https://macoun.de/img/speaker/maxseelemann.jpg",
            "twitter": "@macguru17",
            "description": "hat in Dresden Informatik studiert und ist Geschäftsführer und Entwickler bei “Ulysses”. Er entwickelt sowohl Ulysses und Daedalus Touch, als auch die Localization Suite."
        },
        {
            "id": "RalfEbert",
            "firstname": "Ralf",
            "lastname": "Ebert",
            "twitter": "@ralfebert",
            "image": "https://macoun.de/img/speaker/ralfebert.jpg",
            "description": "Ralf Ebert ist langjähriger iOS-Entwickler. Er gibt seine Kenntnisse und Erfahrungswerte online auf ralfebert.de und in seinen iOS-Schulungen weiter."
        },
        {
            "id": "AlexsanderAkers",
            "firstname": "Alexsander",
            "lastname": "Akers",
            "image": "https://macoun.de/img/speaker/alexsanderakers.jpg",
            "twitter": "@a2",
            "description": "Alexsander Akers, ehemaliger Macoun-Teilnehmer und Gewinner der Macopardy 2016, ist Softwareentwickler für iOS und macOS und arbeitet in Berlin an der Microsoft To-Do App."
        },
        {
            "id": "ChristopherBeloch",
            "firstname": "Christopher",
            "lastname": "Beloch",
            "image": "https://macoun.de/img/speaker/christopherbeloch.jpg",
            "twitter": "@CBeloch",
            "description": "Christopher Beloch ist seit 8 Jahren iOS-Entwickler bei der opwoco GmbH in Schöppingen und arbeitet dort tagtäglich mit unterschiedlichen APIs zum Austausch der Daten zwischen Cloud und App."
        },
        {
            "id": "AlexanderHeinrich",
            "firstname": "Alexander",
            "lastname": "Heinrich",
            "image": "https://macoun.de/img/speaker/alexanderheinrich.jpg",
            "twitter": "@Sn0wfreeze",
            "description": "Alexander Heinrich ist App-Entwickler seit seiner Schulzeit und hat auch im Studium der IT-Sicherheit seinen Fokus weiterhin auf dem Apple Ökosystem."
        },
        {
            "id": "BjoernLindner",
            "firstname": "Björn",
            "lastname": "Lindner",
            "image": "https://macoun.de/img/speaker/bjoernlindner.jpg",
            "twitter": "@",
            "description": "Björn Lindner ist seit 10 Jahren iOS Entwickler bei einem deutschen Energieerzeuger und widmet sich primär den Themen Augmented Reality, Machine Learning und NFC."
        },
        {
            "id": "MartinWinter",
            "firstname": "Martin",
            "lastname": "Winter",
            "image": "https://macoun.de/img/speaker/martinwinter.jpg",
            "twitter": "@martinwinter",
            "description": "Selbständiger Softwareentwickler für OS X und iOS, lebt seit 2013 in Reading/UK. Begeistert sich als gelernter Mediengestalter neben allem, was mit Programmieren zu tun hat, auch für Design und speziell Typografie."
        },
        {
            "id": "KlausRodewig",
            "firstname": "Klaus",
            "lastname": "Rodewig",
            "image": "https://macoun.de/img/speaker/klausrodewig.jpg",
            "twitter": "@cocoanehead",
            "description": "Klaus Rodewig ist dem Publikum allgemein bekannt und zählt mittlerweil zum Inventar der Macoun. Er war und ist Geschäftsführer verschiedener Firmen und vertreibt sich die Zeit mit IT-Sicherheit und Programmierung."
        },
        {
            "id": "DominikHauser",
            "firstname": "Dr. Dominik",
            "lastname": "Hauser",
            "image": "https://macoun.de/img/speaker/dominikhauser.jpg",
            "twitter": "@dasdom",
            "description": "Dr. Dominik Hauser ist auch ok und zuständig für die Fehler im Code. Er schreibt grundsätzlich nur Code, der sich reimt."
        },
        {
            "id": "RebekkaHoneit",
            "firstname": "Rebekka",
            "lastname": "Honeit",
            "image": "https://macoun.de/img/speaker/rebekkahoneit.jpg",
            "twitter": "@",
            "description": "Rebekka Honeit kommuniziert und vermarktet seit fünf Jahren Ulysses, das ultimative Schreibprogramm. Sie hat einen gesellschaftswissenschaftlichen Uni-Abschluss und erkennt Programmiercode, wenn sie welchen sieht."
        },
        {
            "id": "AndreasZeitler",
            "firstname": "Andreas",
            "lastname": "Zeitler",
            "image": "https://macoun.de/img/speaker/andreaszeitler.jpg",
            "twitter": "@Zettt",
            "description": "Andreas Zeitler ist Digital Marketing Experte. Gearbeitet hat er in vielen Software- und Grosskonzern-Projekten an der Aussendarstellung und Kommunikation – immer mit dem Fokus IT, Design, Technik."
        },
        {
            "id": "ChristianTietze",
            "firstname": "Christian",
            "lastname": "Tietze",
            "image": "https://macoun.de/img/speaker/christiantietze.jpg",
            "twitter": "@ctietze",
            "description": "Christian ist selbstständiger Softwareentwickler. Er schreibt hauptsächlich macOS-Apps für Autoren und Leute, die gern mit Texten arbeiten. Er hat 6 Jahre an der Uni Bielefeld Produktivitätsworkshops für Studenten gehalten."
        },
        {
            "id": "AlexBelow",
            "firstname": "Alexander von",
            "lastname": "Below",
            "image": "https://macoun.de/img/speaker/alexandervonbelow.jpg",
            "twitter": "@avbelow",
            "description": "Statt Bungee Jumping oder Tough Mudder beschäftigt sich Alexander von Below einmal im Jahr mit Android um zu spüren, dass er noch lebt. Er wohnt in Köln und blutet in sechs Farben."
        },
        {
            "id": "MarcoFeltmann",
            "firstname": "Marco",
            "lastname": "Feltmann",
            "image": "https://macoun.de/img/speaker/marcofeltmann.jpg",
            "twitter": "@MarcoFeltmann",
            "description": "Marco Feltmann hat sich 2005 in die Entwicklung für damals Mac OS X verliebt und bis heute trotz Ausflügen in Windows, Android und iOS nicht damit aufgehört."
        },
        {
            "id": "MickeyLauer",
            "firstname": "Dr. Michael",
            "lastname": "Lauer",
            "image": "https://macoun.de/img/speaker/michaellauer.jpg",
            "twitter": "@DrMickeyLauer",
            "description": "Mickey ist freiberuflicher Softwareentwickler, Autor und Musiker. Trotz seines Faibles für quelloffene Software tobt er seine Leidenschaft für intuitive Benutzungsoberflächen und Apps derzeit auf Apple's Plattformen aus."
        },
        {
            "id": "TobiasGuenther",
            "firstname": "Tobias",
            "lastname": "Günther",
            "image": "https://macoun.de/img/speaker/tobiasguenther.jpg",
            "twitter": "@fournova",
            "description": "Tobias Günther ist Gründer und CEO von “Tower”, einem beliebten Desktop-Client für Git auf Mac und Windows (www.git-tower.com). Seit vielen Jahren hilft er Nutzern dabei, produktiver und sicherer mit Git zu arbeiten."
        },
        {
            "id": "UliKusterer",
            "firstname": "Uli",
            "lastname": "Kusterer",
            "image": "https://macoun.de/img/speaker/ulikusterer.jpg",
            "twitter": "@uliwitness",
            "description": "Uli Kusterer baut bei ObjectBox an Datenbanken und Code-Generatoren und deren Swift-Interface. In einem früheren Leben hat er Videostreaming- und TV-Software entwickelt und an der Porsche-iOS-App mitgearbeitet."
        },
        {
            "id": "DanielDoenigus",
            "firstname": "Daniel",
            "lastname": "Dönigus",
            "image": "https://macoun.de/img/speaker/danieldoenigus.jpg",
            "twitter": "@doenigus",
            "description": "Daniel Dönigus ist selbstständiger Softwareentwickler. Seine Leidenschaft ist die 3D-Grafik in Theorie und Praxis."
        }
    ],
    "talks": [
        {
            "id": "M19_GS_Sa_0",
            "web-id": "GS_Sa_0",
            "title": "Keynote",
            "speaker_id": [
                "ChrisHauser",
                "ThomasBiedorf"
            ],
            "description": "Die Eröffnung der Macoun.",
            "room-id": "GS",
            "startdate": "2019-10-04 10:30",
            "duration": 30,
            "category": "drumrum",
            "links": {}
        },
        {
            "id": "M19_GS_Fr_01",
            "web-id": "GS_Fr_01",
            "title": "Motion Capturing in ARKit + RealityKit",
            "speaker_id": [
                "FelixSchlegel"
            ],
            "description": "In diesem Vortrag zeigt Felix Schlegel wie man mit dem dieses Jahr vorgestelltem Motion Capturing in ARKit 3 die Bewegungen von Personen aufnehmen und auf 3D-Figuren projizieren kann.",
            "room-id": "GS",
            "startdate": "2019-10-04 11:00",
            "duration": 60,
            "category": "code",
            "links": {}
        },
        {
            "id": "M19_GS_Fr_02",
            "web-id": "GS_Fr_02",
            "title": "Neues in Swift",
            "speaker_id": [
                "NikolajSchumacher"
            ],
            "description": "Nichts ist so sicher wie stetige Änderungen in Swift. Höchste Zeit mal einen Blick auf die Neuerungen in Swift 5 und 5.1 zu werfen und zu schauen, welche Infrastruktur neue Frameworks wie SwiftUI und Combine möglich macht.",
            "room-id": "GS",
            "startdate": "2019-10-04 12:30",
            "duration": 60,
            "category": "language",
            "links": {}
        },
        {
            "id": "M19_GS_Fr_03",
            "web-id": "GS_Fr_03",
            "title": "Einführung in SwiftUI",
            "speaker_id": [
                "BenjaminBoecker"
            ],
            "description": "SwiftUI ist Apples neues Framework für User Interfaces. In diesem Talk werden grundlegende Fragen geklärt: Wie funktioniert die neue, deklarative Syntax? Welche Interface-Elemente gibt es, und wie kann man sie mit Daten befüllen? Sollte man jetzt schon SwiftUI lernen und in die eigenen Apps einbauen? Anhand einer einfachen Beispiel-App werden diese Fragen geklärt und ein Überblick über die Fähigkeiten des neuen Frameworks gegeben.",
            "room-id": "GS",
            "startdate": "2019-10-04 15:00",
            "duration": 60,
            "category": "language",
            "links": {}
        },
        {
            "id": "M19_GS_Fr_04",
            "web-id": "GS_Fr_04",
            "title": "Bring deine Tests zum Rennen",
            "speaker_id": [
                "FlorianLuecke"
            ],
            "description": "Jeder weiß, dass man viele Tests schreiben soll, um die Qualität eines Programms sicher zu stellen. Aber was, wenn diese auf einmal mehr als eine Stunde laufen? Florian zeigt, wie man ein CI-System bauen kann, dass trotzdem schnell Ergebnisse für viele langsame Tests liefert.",
            "room-id": "GS",
            "startdate": "2019-10-04 16:30",
            "duration": 60,
            "category": "tools",
            "links": {}
        },
        {
            "id": "M19_TS_Fr_02",
            "web-id": "TS_Fr_02",
            "title": "CI/CD für iOS Projekte",
            "speaker_id": [
                "SebastianMessingfeld"
            ],
            "description": "Bei der Umsetzung eines Continuous Delivery Prozess für iOS Apps ist man mit gewissen Herausforderungen konfrontiert. Im Vortrag wird gezeigt, wie man macOS mit Ansible provisionieren kann und wie man sein Xcode-Projekt aufbaut um mit fastlane das Zertifikatsmanagement zu übernehmen und unabhängig von konkreten macOS Buildservern und -services zu sein. Dazu wird gezeigt wie Build-Pipelines mit automatisierten Quality Checks während der Entwicklung und der Distribution der App in der Praxis aussehen können.",
            "room-id": "TS",
            "startdate": "2019-10-04 12:30",
            "duration": 60,
            "category": "tools",
            "links": {}
        },
        {
            "id": "M19_TS_Fr_03",
            "web-id": "TS_Fr_03",
            "title": "lldb - Debugger auf Abwegen",
            "speaker_id": [
                "OliverBayer"
            ],
            "description": "lldb kann mehr als nur einfache Breakpoints oder po. In dem Vortrag zeigt Oliver Bayer, wie sich mit Hilfe von lldb Programmcode zur Ausführungszeit manipulieren lässt, ohne das hierfür der Sourcecode anzupassen ist. Sei es, damit Test- oder Debugcode nicht in die produktiv App gelangt, oder weil der Sourcecode für einen Teil der App nicht vorliegt.",
            "room-id": "TS",
            "startdate": "2019-10-04 15:00",
            "duration": 60,
            "category": "tools",
            "links": {}
        },
        {
            "id": "M19_TS_Fr_04",
            "web-id": "TS_Fr_04",
            "title": "Objective-Smalltalk: die einfache Swift-Alternative",
            "speaker_id": [
                "MarcelWeiher"
            ],
            "description": "Muss das alles denn wirklich so kompliziert sein?\nAnhand von praktischen Beispielen möchte ich Objective-Smalltalk vorstellen. Wenn man von Objective-C lernt anstatt diese Erfahrung über Bord zu schmeissen, kann man aus wenigen orthogonalen Konzepten, einem Fokus auf Architektur und einem soliden Metasystem eine einfache, sehr ausdrucksmächtige und nicht zuletzt ungemein praktische Sprache konstruieren.",
            "room-id": "TS",
            "startdate": "2019-10-04 16:30",
            "duration": 60,
            "category": "language",
            "links": {}
        },
        {
            "id": "M19_NS_Fr_01",
            "web-id": "NS_Fr_01",
            "title": "Einführung in die Em\u00ADbed\u00ADded-Welt mit ESP32",
            "speaker_id": [
                "MatthiasKrauss",
                "TimBecker"
            ],
            "description": "Embedded-Entwicklung eröffnet neue Möglichkeiten. Embedded ist interessant. Embedded macht Spaß. Eigenartigerweise haben viele Entwickler noch nie embedded programmiert. Das wollen wir ändern. Punkt. Eventuell bauen wir unsere eigene Hardware oder wir verwenden ein existierendes Bord auf ESP32-Basis, Ein sehr günstiger und leistungsfähige Embedded-Controller mit Bluetooth, WLAN und vielen weiteren Möglichkeiten. Darauf werden wir eine kleine Anwendung auf Mongoose OS-Basis bauen, ein einfaches und leistungsfähiges Echtzeitbetriebssystem.\nVoraussetzungen: Elementare Programmierkenntnisse, ein eigener Rechner und ein Micro USB Kabel werden benötigt.",
            "room-id": "NS",
            "startdate": "2019-10-04 11:00",
            "duration": 420,
            "category": "workshop",
            "booking": {
                "from": "2019-07-23 12:00",
                "to": "2019-10-02 23:59",
                "seats": 20
            },
            "links": {}
        },
        {
            "-sidetracked": true,
            "id": "M19_WS_Fr",
            "web-id": "WS_Fr",
            "title": "Playground",
            "speaker_id": [
                "NinaHauser"
            ],
            "description": "Neos Wohnung steht Euch als Spielplatz zur Verfügung. Ballspielen ist nicht erlaubt, aber technisches Spielzeug wird sich sicher finden, das ihr hier ausprobieren könnt.",
            "category": "drumrum",
            "room-id": "WS",
            "startdate": "2019-10-04 11:00",
            "duration": 390
        },
        {
            "-sidetracked": true,
            "id": "M19_WS_Sa",
            "web-id": "WS_Sa",
            "title": "Die Werkstatt",
            "speaker_id": [
                "PeterMaurer",
                "RalfEbert",
                "AlexBelow",
                "FriedrichRuynat",
                "MartinWinter",
                "MaxSeelemann",
                "OrtwinGentz"
            ],
            "description": "Hier helfen unsere Spezialisten den ganzen Tag über den Teilnehmern bei der Lösung konkreter Probleme. Die erfahrenen Entwickler begutachten Eure Projekte, User-Interfaces und inspizieren Code.",
            "room-id": "WS",
            "startdate": "2019-10-05 11:00",
            "duration": 240
        },
        {
            "id": "M19_GS_Sa_01",
            "web-id": "GS_Sa_01",
            "title": "Entwicklung eines barrierefreien Spiels",
            "speaker_id": [
                "AlexsanderAkers"
            ],
            "description": "Bist du eine SpieleentwicklerIn? Hast du dich jemals gefragt, ob dein Spiel von allen gespielt werden kann, auch von Menschen, die Bedienungshilfen nutzen? Das habe ich, als ich angefangen habe, meine Backgammon-App zu entwickeln. Obwohl ich wusste, dass ich wollte, dass mein Spiel barrierefrei ist, war ich mir nicht sicher, wie ich diesen Benutzern das beste Erlebnis bieten konnte. In meinem Vortrag möchte ich mitteilen, was ich über Forschung zu Barrierefreiheit, Design und Engineering für Spiele gelernt habe.",
            "room-id": "TS",
            "startdate": "2019-10-05 11:00",
            "duration": 60,
            "category": "code",
            "links": {}
        },
        {
            "id": "M19_GS_Sa_02",
            "web-id": "GS_Sa_02",
            "title": "Think Codables",
            "speaker_id": [
                "ChristopherBeloch"
            ],
            "description": "Christopher Beloch zeigt euch wie ihr euch mit Codables nicht der API ergeben müsst. Lasst euch eure Model-Struktur nicht von einem Server vorgeben der im schlimmsten Fall nichtmal in eurer Hand liegt. Lernt wie ihr mit relativ einfachen Schritten die (De-)Serialisierung für eure Zwecke anpasst.",
            "room-id": "GS",
            "startdate": "2019-10-05 12:30",
            "duration": 60,
            "category": "code",
            "links": {}
        },
        {
            "id": "M19_GS_Sa_03",
            "web-id": "GS_Sa_03",
            "title": "Die Magie hinter Handoff und Continuity",
            "speaker_id": [
                "AlexanderHeinrich"
            ],
            "description": "Handoff, das Universal Clipboard und der Austausch von WiFi Passwörtern an Bekannte. All das sind beinahe magische Funktionen des Apple Ökosystems und wir benutzen sie täglich. Doch, wie funktioniert die Magie hinter dem ganzen und was können wir von Apple lernen wenn wir uns anschauen was hier eigentlich passiert? Dieser Vortrag beschäftigt sich mit dem Aufbau und dem Nachbau der Funktionen und ist für alle Interessierten geeignet.",
            "room-id": "GS",
            "startdate": "2019-10-05 15:00",
            "duration": 60,
            "category": "code",
            "links": {}
        },
        {
            "id": "M19_GS_Sa_04",
            "web-id": "GS_Sa_04",
            "title": "Willkommen NFC",
            "speaker_id": [
                "BjoernLindner"
            ],
            "description": "Near Field Communication (NFC) ist seit vielen Jahren aus der Industrie nicht mehr weg zu denken und auch in Nicht-Apple Smartphones schon länger angekommen. So langsam findet NFC auch den Weg in die iOS Welt. Anhand von Code Beispielen (CoreNFC und PassKit) werden Möglichkeiten zum Lesen von NFC-Tags, zum Senden via Passes, aber auch Hürden und Herausforderungen besprochen. Auch die Rolle von Apple Pay bei dem Thema wird kurz erläutert.Ziel der Session soll es sein, neue Einsatzmöglichkeiten von NFC in bestehenden Apps zu finden, Beispiele zu sehen, sich aber auch der noch bestehenden Probleme bewußt zu sein.",
            "room-id": "GS",
            "startdate": "2019-10-05 16:30",
            "duration": 60,
            "category": "code",
            "links": {}
        },
        {
            "id": "M19_TS_Sa_01",
            "web-id": "TS_Sa_01",
            "title": "Appgefucked",
            "speaker_id": [
                "KlausRodewig",
                "DominikHauser"
            ],
            "description": "Den Traum von der eigenen App-Firma träumen viele. Für manche geht er in Erfüllung. Manchmal klappt es nachhaltig mit der Geschäftsidee, manchmal nicht. Dieser Vortrag ist ein Erfahrungsbericht über eine App-Firma, die mit großen Ambitionen gestartet, am Ende aber gescheitert ist. Das Ziel des Vortrags ist, potentiellen Gründern Erfahrungen mit an die Hand zu geben, welche Fehler sie vermeiden und worauf sich achten sollten.",
            "room-id": "GS",
            "startdate": "2019-10-05 11:00",
            "duration": 60,
            "category": "marketing",
            "links": {}
        },
        {
            "id": "M19_TS_Sa_02",
            "web-id": "TS_Sa_02",
            "title": "Geld spielt keine Rolle: PR & Mar\u00ADke\u00ADting für kleine Budgets",
            "speaker_id": [
                "RebekkaHoneit"
            ],
            "description": "Zündende Idee gehabt, tolle App gebaut – und keiner hat’s gemerkt? Es kann doch nicht so schwer sein, das Produkt an die Frau oder an den Mann zu bringen… oder?! Die schlechte Nachricht ist: Es gibt keine Abkürzung zum Weltruhm. Die gute: Du kannst (und solltest!) dennoch einiges dafür tun, dass deine App von JournalistInnnen und potenziellen KundInnen wahrgenommen wird. Du musst dafür nicht viel Geld in die Hand, aber dein Gegenüber ernst und dir Zeit nehmen.",
            "room-id": "TS",
            "startdate": "2019-10-05 12:30",
            "duration": 90,
            "category": "marketing",
            "links": {}
        },
        {
            "id": "M19_TS_Sa_03",
            "web-id": "TS_Sa_03",
            "title": "Die heiligen drei Könige: Marketing, Sales, Business",
            "speaker_id": [
                "AndreasZeitler"
            ],
            "description": "Bei der Arbeit mit großen Unternehmen und kleinen Unternehmern, welche maßgeblich technisch orientiert sind, kommen immer wieder die selben Fragen auf: Marketing? Vertrieb? Und dann? Es sucht jeder die für sich passende, zur Unternehmensgröße passende Herangehensweise. Aufgeteilt ist der Workshop in einen Theorie und Praxis-Teil. Die Theorie dient dazu alle Teilnehmenden auf einen gemeinsamen Stand zu bringen und einige praktische Beispiele zu geben, wie einzelne Strategien umgesetzt bzw. evaluiert werden können. Später arbeiten wir in einer Gruppe, oder Gruppen, an einem gemeinsamen Projekt.",
            "room-id": "TS",
            "startdate": "2019-10-05 15:00",
            "duration": 150,
            "category": "workshop",
            "booking": {
                "from": "2019-07-23 12:00",
                "to": "2019-10-02 23:59",
                "seats": 10
            },
            "links": {}
        },
        {
            "id": "M19_NS_Sa_01",
            "web-id": "NS_Sa_01",
            "title": "TDD Code Retreat: DRY & CLEAN",
            "speaker_id": [
                "ChristianTietze",
                "OliverBoehm"
            ],
            "description": "Ein intensiver Praxisworkshop, bei dem man fundamentale Programmiertechniken üben und sich mit verschiedenen Denkansätzen zur Problemlösung bekannt machen kann: Wir werden im pair programming in mehreren Ansätzen das Game of Life implementieren. Dabei steht Test-Driven Development im Vordergrund und wir konzentrieren uns mit jeder Iteration neu auf andere Programmiertechniken und Beschränkungen.\nLaptop oder andere Programmierumgebung mitbringen: man sollte Code ausführen und Tests schreiben können.\nBeide TDD Workshops können unabhängig voneinander besucht werden, der Besuch beider Sessions wird für maximale Lerneffekte jedoch empfohlen.",
            "room-id": "NS",
            "startdate": "2019-10-05 10:30",
            "duration": 210,
            "category": "workshop",
            "booking": {
                "from": "2019-07-23 12:00",
                "to": "2019-10-02 23:59",
                "seats": 20
            },
            "links": {}
        },
        {
            "id": "M19_NS_Sa_02",
            "web-id": "NS_Sa_02",
            "title": "TDD Code Retreat: Test-Driven",
            "speaker_id": [
                "ChristianTietze",
                "OliverBoehm"
            ],
            "description": "Ein intensiver Praxisworkshop, bei dem man fundamentale Programmiertechniken üben und sich mit verschiedenen Denkansätzen zur Problemlösung bekannt machen kann: Wir werden im pair programming in mehreren Ansätzen das Game of Life implementieren. Dabei steht Test-Driven Development im Vordergrund und wir konzentrieren uns mit jeder Iteration neu auf andere Programmiertechniken und Beschränkungen.\nLaptop oder andere Programmierumgebung mitbringen: man sollte Code ausführen und Tests schreiben können.\nBeide TDD Workshops können unabhängig voneinander besucht werden, der Besuch beider Sessions wird für maximale Lerneffekte jedoch empfohlen.",
            "room-id": "NS",
            "startdate": "2019-10-05 15:00",
            "duration": 180,
            "category": "workshop",
            "booking": {
                "from": "2019-07-23 12:00",
                "to": "2019-10-02 23:59",
                "seats": 20
            },
            "links": {}
        },
        {
            "id": "M19_GS_So_01",
            "web-id": "GS_So_01",
            "title": "Cross Platform Entwicklung mit Kotlin",
            "speaker_id": [
                "AlexBelow"
            ],
            "description": "Die Frage der Cross-Platform Entwicklung für iOS und Android kommt immer wieder auf, und die meisten Lösungen sind schlecht.\nSeit neuestem gibt es die Möglichkeit, auch die Sprache Kotlin auf iOS zu verwenden.\nFunktioniert das? Taugt das etwas? Und wie mache ich das? Das und mehr soll dieser Vortrag beleuchten.",
            "room-id": "GS",
            "startdate": "2019-10-06 11:00",
            "duration": 60,
            "category": "code",
            "links": {}
        },
        {
            "id": "M19_GS_So_02",
            "web-id": "GS_So_02",
            "title": "Businesslogik platt\u00ADform\u00ADun\u00ADab\u00ADhängig ent\u00ADwickeln",
            "speaker_id": [
                "MarcoFeltmann"
            ],
            "description": "Marco Feltmann zeigt eine Möglichkeit zur Erstellung geteilter Businesslogik für Menschen, die Templates in C++ einfach nie verstanden haben. Wichtig ist ihm hierbei der Fokus auf die plattformunabhängige Entwicklung.",
            "room-id": "GS",
            "startdate": "2019-10-06 12:30",
            "duration": 60,
            "category": "code",
            "links": {}
        },
        {
            "id": "M19_GS_So_03",
            "web-id": "GS_So_03",
            "title": "CarPlay in Theorie und Praxis",
            "speaker_id": [
                "MickeyLauer"
            ],
            "description": "Dieser Vortrag führt in Apple’s Benutzungsoberfläche für Autos ein. Nach einer Kurzgeschichte und dem aktuellen Stand der Dinge werden die zwei CarPlay-App-Kategorien Audio und Navigation samt verfügbarer APIs vorgestellt. Demos für jede Kategorie werden auf echter CarPlay-fähiger Hardware vorgeführt.",
            "room-id": "GS",
            "startdate": "2019-10-06 15:00",
            "duration": 60,
            "category": "code",
            "links": {}
        },
        {
            "id": "M19_TS_So_01",
            "web-id": "TS_So_01",
            "title": "Surviving with Git: Undoing Things and Recovering",
            "speaker_id": [
                "TobiasGuenther"
            ],
            "description": "In dieser praxisnahen Session schauen wir uns die verschiedenen “Undo”-Features an, die Git zur Verfügung stellt. Das Ziel ist es, Software-Entwicklern mehr Sicherheit in ihrer alltäglichen Arbeit mit Code zu geben: “Fehler können behoben werden” ist eine enorm blutdrucksenkende Erkenntnis! Die Session behandelt nicht nur die Theorie, sondern vor allem die praktischen Kommandos und Tools. Wir werden sowohl Git auf der Kommandozeile als auch in der beliebten Desktop-GUI “Tower” verwenden, um allerhand Fehler in realen Szenarios zu beheben.",
            "room-id": "TS",
            "startdate": "2019-10-06 11:00",
            "duration": 60,
            "category": "tools",
            "links": {}
        },
        {
            "id": "M19_TS_So_02",
            "web-id": "TS_So_02",
            "title": "Wir bauen Git",
            "speaker_id": [
                "UliKusterer"
            ],
            "description": "Wir bauen eine kleine Swift-Versionskontroll-App, die in groben Grundzügen die Funktionalität von Git nachbaut, und einem so ein besseres Verständnis der Eigenheiten von Git liefert.",
            "room-id": "TS",
            "startdate": "2019-10-06 12:30",
            "duration": 60,
            "category": "tools",
            "links": {}
        },
        {
            "id": "M19_NS_So_01",
            "web-id": "NS_So_01",
            "title": "Wir bauen Space Invaders mit Unity 3D",
            "speaker_id": [
                "DanielDoenigus"
            ],
            "description": "Es ist Zeit für Spiele. In diesem Workshop entwickeln wir Space Invaders. Dafür nutzen wir die Spiele-Engine Unity 3D. Daniel wird zunächst anhand von Theorie und kurzer Beispiele eine Einführung in die Entwicklung von Spielen geben. Danach werden einzelne Aufgaben unter den Teilnehmern aufgeteilt und wir entwickeln unser eigenes Spiel. Daniel steht dem Team während dieser Zeit für Fragen zur Verfügung. Das Ergebnis wird ein absoluter Superhit!\nFür die Programmierung sind einfache C#-Kenntnisse nötig. Von jedem Teilnehmer sollte im Vorfeld bereits Unity 3D und eine passende Entwicklungsumgebung (z.B. Visual Studio) installiert und zum gemeinsamen Arbeiten sollte ein SVN-Client vorhanden sein. ",
            "room-id": "NS",
            "startdate": "2019-10-06 11:00",
            "duration": 420,
            "category": "workshop",
            "booking": {
                "from": "2019-07-23 12:00",
                "to": "2019-10-02 23:59",
                "seats": 10
            },
            "links": {}
        },
        {
            "-sidetracked": true,
            "id": "M19_WS_So",
            "web-id": "WS_So",
            "title": "Playground",
            "speaker_id": [
                "NinaHauser"
            ],
            "description": "Neos Wohnung steht Euch als Spielplatz zur Verfügung. Ballspielen ist nicht erlaubt, aber technisches Spielzeug wird sich sicher finden, das ihr hier ausprobieren könnt.",
            "category": "drumrum",
            "room-id": "WS",
            "startdate": "2019-10-06 11:00",
            "duration": 390
        },
        {
            "-sidetracked": true,
            "id": "M19_Party",
            "web-id": "Party",
            "title": "Die Party",
            "speaker_id": "",
            "description": "Die Party findet im Lokalbahnhof, Darmstaedter Landstr. 14, 60594 Frankfurt am Main statt.\nBringt bitte Euren Badge mit, der gilt als Eintrittskarte.\nEssen ist frei, Getränke müssen bezahlt werden.",
            "room-id": "LB",
            "startdate": "2019-10-05 19:00",
            "duration": 360,
            "category": "drumrum"
        },
        {
            "-sidetracked": true,
            "id": "M19_Abend",
            "web-id": "Abend",
            "title": "Offener Abend",
            "speaker_id": "",
            "description": "Der offene Abend findet im Lokalbahnhof, Darmstaedter Landstr. 14, 60594 Frankfurt am Main statt.",
            "room-id": "LB",
            "startdate": "2019-10-06 19:00",
            "duration": 360,
            "category": "drumrum"
        }
    ],
    "rooms": [
        {
            "id": "GS",
            "sort-key": "1",
            "name": "Großer Saal (200)",
            "seats": 375
        },
        {
            "id": "TS",
            "sort-key": "2",
            "name": "Terrassensaal (U49)",
            "seats": 160
        },
        {
            "id": "NS",
            "sort-key": "3",
            "name": "Neuer Saal (103)",
            "seats": 50
        },
        {
            "id": "WS",
            "sort-key": "4",
            "name": "Raum (101)",
            "seats": 24
        },
        {
            "id": "KS",
            "sort-key": "5",
            "name": "Reserve",
            "seats": 0
        },
        {
            "id": "LB",
            "sort-key": "9",
            "name": "Lokalbahnhof",
            "seats": 0
        }
    ]
}"""
 //       return client.get<String>("$endpoint/$id") {

    }


    /**
     * returns list of conference events
     *
     * @param id null
     *
     * @return successful operation
     */
    suspend fun getEvents(
            id: String // PATH
    ): List<Talk> {
        return client.get<List<Talk>>("$endpoint/conferences/$id/events") {
        }
    }

    /**
     * returns list of conference meta data
     *
     * @param id null
     *
     * @return successful operation
     */
    suspend fun getMeta(
            id: String // PATH
    ): MetaData {
        return client.get<MetaData>("$endpoint/conferences/$id/metadata") {
        }
    }

    /**
     * returns list of conference speakers
     *
     * @param id null
     *
     * @return successful operation
     */
    suspend fun getSpeakers(
            id: String // PATH
    ): List<Speaker> {
        return client.get<List<Speaker>>("$endpoint/conferences/$id/speakers") {
        }
    }

    /**
     * submit feedback to talk
     *
     * @param id conferenceId
     * @param sessionId session Id
     * @param body Feedback object that needs to be updated
     *
     * @return OK
     */
    suspend fun updateFeedback(
            id: String, // PATH
            sessionId: String, // PATH
            body: Feedback // BODY
    ): String {
        return client.put<String>("$endpoint/feedback/event/$id/$sessionId") {
            this.body = mutableMapOf<String, Any?>().apply {
                this["body"] = body
            }
        }
    }

    /**
     * Conference styles
     *
     * @param id null
     *
     * @return successful operation
     */
    suspend fun getConferenceStyles(
            id: String // PATH
    ): String {
        return client.get<String>("$endpoint/conferences/$id/styles.css") {
        }
    }

    /**
     * returns keycloak setup
     *
     * @param id null
     *
     * @return successful operation
     */
    suspend fun getKeyCloak(
            id: String // PATH
    ): Keycloak {
        return client.get<Keycloak>("$endpoint/conferences/$id/keycloak.json") {
        }
    }

    private fun HttpRequestBuilder.apiUrl(path: String, userId: String? = null) {
        if (userId != null) {
            header(HttpHeaders.Authorization, "Bearer $userId")
        }
        header(HttpHeaders.CacheControl, "no-cache")
        url {
            takeFrom(endpoint)
            encodedPath = path
        }
    }

}