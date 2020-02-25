//
//  SpeakerMapper.swift
//  iosApp
//
//  Created by Alexander v. Below on 25.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import MultiPlatformLibrary

extension Sequence where Iterator.Element : Speaker {
    var viewModel: [SpeakerViewModel] {
        var result = [SpeakerViewModel(name: "Alex", subtitle: "DT")]
        result = self.map({ (speaker) -> SpeakerViewModel in
            let name = speaker.name
            let company = speaker.title
            let url = URL(string:speaker.avatar)
            return SpeakerViewModel(name: name, subtitle: company, imageURL: url)
        })
        return result
    }
}

