//
//  ContentView.swift
//  iosApp
//
//  Created by Alexander v. Below on 21.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import SwiftUI
import MultiPlatformLibrary

struct ContentView: View {
    @State private var selection = 0
    @ObservedObject var publisher = EventsPublisher()

    var body: some View {
        TabView(selection: $selection){
                List(publisher.events, id: \.eventId) { event in
                    VStack (alignment: .leading) {
                        Text(event.title).font(.headline)
                    Text(event.eventDescription).font(.body)
                    }
                }.font(.title)
                .tabItem {
                    VStack {
                        Image("ic_schedule")
                        Text("action_schedule")
                    }
                }
                .tag(0)
            Text("Second View")
                .font(.title)
                .tabItem {
                    VStack {
                        Image("second")
                        Text("Second")
                    }
                }
                .tag(1)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
