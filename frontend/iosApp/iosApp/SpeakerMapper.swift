//
//  SpeakerMapper.swift
//  iosApp
//
//  Created by Alexander v. Below on 25.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import DukeconSdk

extension Sequence where Iterator.Element : Speaker {
    var viewModel: [SpeakerViewModel] {
        let result = self.map({ (speaker) -> SpeakerViewModel in
            let name = speaker.name
            let company = speaker.title
            let imageUrl = URL(string:speaker.avatar)
            let url = URL(string: speaker.website)
            let description = speaker.bio
            return SpeakerViewModel(name: name, subtitle: company, imageURL: imageUrl, url:url, description: description)
        })
        return result
    }
}

