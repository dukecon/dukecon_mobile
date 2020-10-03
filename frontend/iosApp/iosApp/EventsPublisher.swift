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
    @Published var events = [Event]()
    @Published var dates = [GMTDate]()
    @Published var speakers = [Speaker]()
    @Published var licenses = [Library]()
    @Published var favorites = [Event]()

    private var model: EventsModel!

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
                    return library.targetHost == TargetHost.ios || library.targetHost == TargetHost.common
                })
            }

/*
            self.model.saveFavorite(favorite: DomainFavorite(id:"123" selected:true version:1 ), viewUpdate: { (favorites) in
                print(favorites)
            })

            self.model.searchEventOrSpeaker(query: "Michal", viewUpdate: {(searchResult) in
                print(searchResult)
                
            })
 */
        }
        model.getEventsFromNetwork()

    }

    func updateEvents(day: Int32) {
        self.model.getEvents(day: day) { (events) in
            self.events = events
        }
        self.model.getFavorites(day: day) { (favorites) in
            self.favorites = favorites
        }
    }
}
