//
//  EventsPublisher.swift
//  iosApp
//
//  Created by Alexander v. Below on 21.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import Foundation
import DukeconSdk

class EventsPublisher: ObservableObject {
    @Published var events = [DomainEvent]()
    @Published var dates = [Ktor_utilsGMTDate]()
    @Published var speakers = [DomainSpeaker]()
    @Published var licenses = [DomainLibrary]()
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
                if let firstDate = self.dates.first {
                    self.day = firstDate.dayOfMonth
                    self.updateEvents(day: self.day)
                }
            }
            self.model.getSpeakers { (speakers) in
                self.speakers = speakers
            }
            self.model.getLicenses { (libraries) in
                self.licenses = libraries.filter({ (library) -> Bool in
                    return library.targetHost == DomainTargetHost.ios || library.targetHost == DomainTargetHost.common
                })
            }
            self.model.getFavorites(day: 18) { (favorites) in
                print(favorites)
            }
            /*
            self.model.saveFavorite(favorite: DomainFavorite(id:"123" selected:true version:1 ), viewUpdate: { (favorites) in
                print(favorites)
                
            })
 */
        }
        model.getEventsFromNetwork()

    }

    func updateEvents(day: Int32) {
        self.model.getEvents(day: day) { (events) in
            self.events = events
        }
    }
}
