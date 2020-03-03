//
//  ContentView.swift
//  iosApp
//
//  Created by Alexander v. Below on 21.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import SwiftUI
import DukeconSdk

struct ContentView: View {
    @EnvironmentObject var eventPublisher: EventsPublisher
    @State private var selection = 0

    var body: some View {
        TabView(selection: $selection){
            NavigationView {
                SpeakerView().navigationBarTitle(Text("action_schedule"))
            }
                .tabItem {
                    VStack {
                        Image(systemName: "calendar")
                        Text("action_schedule")
                    }
            }
            .tag(0)
            Text("Second View")
                .font(.title)
                .tabItem {
                    VStack {
                        Image(systemName: "star.fill")
                        Text("action_favorites")
                    }
            }
            .tag(1)
            NavigationView {
                List(eventPublisher.speakers.viewModel, id: \.name) { (speakerViewModel) in
                    NavigationLink(destination: SpeakerDetailsView(viewModel: SpeakerDetailsViewModel(name: speakerViewModel.name, imageURL:speakerViewModel.imageURL, link: speakerViewModel.url, description: speakerViewModel.description))) {

                        TalkSpeakerView(speaker: speakerViewModel)
                    }
                }.navigationBarTitle(Text("action_speakers"))
            }.tabItem {
                VStack {
                    Image(systemName: "person.fill")
                    Text("action_speakers")
                }
            }
            .tag(2)
            List(eventPublisher.licenses, id: \.self) {
                (license) in
                VStack (alignment: .leading) {
                    Text (license.name)
                    Text (license.owner)
                    Text (license.license)
                }
            }
                .tabItem {
                    VStack {
                        Image(systemName: "info.circle.fill")
                        Text("action_info")
                    }
            }
            .tag(3)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
