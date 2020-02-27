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
    @Published var dates = [Ktor_utilsGMTDate]()
    @Published var speakers = [Speaker]()
    @Published var libraries = [Library]()

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
            if let firstDate = self.dates.first {
                self.day = firstDate.dayOfMonth
                self.updateEvents(day: self.day)
            }
            self.model.getSpeakers { (speakers) in
                self.speakers = speakers
            }
            self.model.getLicenses{ (licences) in
                self.libraries = licences
            }
        }
        model.getEventsFromNetwork()
    }

    func updateEvents(day: Int32) {
        self.model.getEvents(day: day) { (events) in
            self.events = events
        }
    }
}
