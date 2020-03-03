//
//  EventMapper.swift
//  iosApp
//
//  Created by Alexander v. Below on 26.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import DukeconSdk

extension DomainEvent {
    var talkDetailViewModel: TalkDetailViewModel {
        var timeDisplay = String()

        let formatter = DateFormatter()
        formatter.dateStyle = .none
        formatter.timeStyle = .short

        if let startDate = self.startTime.date {
            timeDisplay = formatter.string(from: startDate)
            if let endDate = self.endTime.date {
                timeDisplay.append(" - \(formatter.string(from: endDate))")
            }
        }

        return TalkDetailViewModel(title: self.title, room: self.room.localizedName, timeDisplay: timeDisplay, description: self.eventDescription, speakers: self.speakers.viewModel)
    }
}
