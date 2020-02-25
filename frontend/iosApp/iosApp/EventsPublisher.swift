//
//  EventsPublisher.swift
//  iosApp
//
//  Created by Alexander v. Below on 21.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import Foundation
import MultiPlatformLibrary

class EventsPublisher: ObservableObject {
    @Published var events = [Event]()
    @Published var dates: [Ktor_utilsGMTDate] = [Ktor_utilsGMTDate]()
    var model: EventsModel!

    var day: Int32 = 0 {
        didSet {
            self.updateEvents(day: day)
        }
    }

    init() {
        model = EventsModel { (events) in
            self.model.getConferenceDays { (dates) in
                self.dates = dates
            }
            self.updateEvents(day: self.day)
        }
        model.getEventsFromNetwork()
    }

    func updateEvents(day: Int32) {
        self.model.getEvents(day: day) { (events) in
            self.events = events
        }
    }
}
