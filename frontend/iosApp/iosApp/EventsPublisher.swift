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
    @Published var events = [Event_]()
    @Published var dates: [Ktor_utilsGMTDate] = [Ktor_utilsGMTDate]()
    var model: EventsModel!

    init() {
        model = EventsModel { (events) in
            self.events = events
            self.dates = self.eventDays(events: events)
        }
        model.getEventsFromNetwork()
    }

    func eventDays (events: [Event_]) -> [Ktor_utilsGMTDate] {

        var foundDates = [Ktor_utilsGMTDate]()
        for event in events {
            let start = event.startTime

            var isNew = true
            for existing in foundDates {
                if start.isSameDate(existing) {
                    isNew = false
                    break
                }
            }
            if isNew {
                foundDates.append(start)
            }
        }

        return foundDates
    }


}
