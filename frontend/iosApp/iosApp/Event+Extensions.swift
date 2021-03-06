//
//  Event+Extensions.swift
//  iosApp
//
//  Created by Alexander v. Below on 22.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import DukeconSdk

// These look like they might be good candiates for the common
// Kotlin library. These are tasks required on both platforms

extension Event {
    var speakerList : String {
        let stringmap = self.speakers.map { (speaker) -> String in
            return speaker.name
        }
        return stringmap.joined(separator: ", ")
    }
}

extension Location {
    var localizedName : String {
        let languageCode = RepositoryFactoryKt.getLocale()
        if let localizedName = self.names[languageCode] {
            return localizedName
        } else if let key = names.keys.first, let name = names[key] {
            return name
        } else {
            return ""
        }
    }
}
