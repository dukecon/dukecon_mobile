//
//  ScheduleView.swift
//  iosApp
//
//  Created by Alexander v. Below on 29.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import SwiftUI
import DukeconSdk
struct ScheduleView: View {
    @EnvironmentObject var eventPublisher: EventsPublisher

    var body: some View {
        VStack {
            HStack {
                ForEach(eventPublisher.dates, id:\.timestamp ) { day in
                    Button(action: {
                        self.eventPublisher.day = day.dayOfMonth
                    }) {
                        Text(day.dayOfWeek.value)
                    }
                }
            }

            List(eventPublisher.events, id: \.eventId) { event in
                VStack (alignment: .leading) {
                    NavigationLink(destination: TalkDetailView(viewModel: event.talkDetailViewModel)) {
                        TalkView(title: event.title, speakers: event.speakerList, room: event.room.localizedName)
                    }
                }
            }.font(.title)
        }
    }
}

struct SpeakerView_Previews: PreviewProvider {
    static var previews: some View {
        ScheduleView()
    }
}
