//
//  TalkDetailView.swift
//  iosApp
//
//  Created by Alexander v. Below on 22.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import SwiftUI

struct SpeakerViewModel {
    var name: String
    var subtitle: String
    var imageURL: URL? = nil
    var url: URL?
    var description: String
}

struct TalkDetailViewModel {
    var title: String
    var room: String
    var timeDisplay: String
    var description: String
    var speakers: [SpeakerViewModel]
}

struct TalkDetailView: View {
    var title: String
    var room: String
    var description: String
    var speakers: [SpeakerViewModel]
    var viewModel: TalkDetailViewModel

    init(viewModel: TalkDetailViewModel) {
        self.viewModel = viewModel
        self.title = viewModel.title
        self.room = viewModel.room
        self.description = viewModel.description
        self.speakers = viewModel.speakers
    }

    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Text([room, viewModel.timeDisplay].joined(separator: ", ")).font(.body)
                Spacer()
            }.padding().frame(width: nil, height: 100, alignment: .top).background(Color.blue)
            VStack(alignment: .leading, spacing: nil) {
                ScrollView {
                    HStack {
                        ForEach(speakers, id:\.name ) {
                            speaker in
                            NavigationLink(destination: SpeakerDetailsView(viewModel: SpeakerDetailsViewModel(name: speaker.name, imageURL:speaker.imageURL, link: speaker.url, description: speaker.description))) {
                                TalkSpeakerView(speaker: speaker)
                            }.buttonStyle(PlainButtonStyle())
                        }
                        Spacer()
                    }.font(.body).padding(.bottom)
                    Text(description).font(.body)
                }
                Spacer()
            }.padding()
        }.navigationBarTitle(title)
    }
}

struct TalkDetailView_Previews: PreviewProvider {
    static var previews: some View {
        let viewModel = TalkDetailViewModel(title: "Kotlin", room: "Foo", timeDisplay: "9-10", description: "Lorem Ipsum", speakers: [SpeakerViewModel(name: "Alexander von Below", subtitle: "Deutsche Telekom AG", description: "Lorem Ipsum")])
        return TalkDetailView(viewModel: viewModel)
    }
}
