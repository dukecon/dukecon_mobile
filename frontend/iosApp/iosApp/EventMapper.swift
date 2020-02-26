//
//  EventMapper.swift
//  iosApp
//
//  Created by Alexander v. Below on 26.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import MultiPlatformLibrary

extension Event {
    var talkDetailViewModel: TalkDetailViewModel {
        return TalkDetailViewModel(title: self.title, room: self.room.localizedName, description: self.eventDescription, speakers: self.speakers.viewModel)
    }
}
