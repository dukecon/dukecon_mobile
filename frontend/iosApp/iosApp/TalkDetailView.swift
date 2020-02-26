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
}

struct TalkDetailViewModel {
    var title: String
    var room: String
    var description: String
    var speakers: [SpeakerViewModel]
}

struct TalkDetailView: View {
    var title: String
    var room: String
    var description: String
    var speakers: [SpeakerViewModel]

    init(viewModel: TalkDetailViewModel) {
        self.title = viewModel.title
        self.room = viewModel.room
        self.description = viewModel.description
        self.speakers = viewModel.speakers
    }

    var body: some View {
        VStack(alignment: .leading) {
            VStack {
                Spacer()
                VStack {
                    HStack {
                        Text(title).font(.callout)
                        Spacer()
                    }
                    HStack {
                        Text([room, "9-10"].joined(separator: ", ")).font(.body)
                        Spacer()
                    }
                }.padding()
            }.frame(width: nil, height: 100, alignment: .top).background(Color.blue)
            VStack(alignment: .leading, spacing: nil) {
                ScrollView {
                    HStack {
                        ForEach(speakers, id:\.name ) {
                            speaker in
                            TalkSpeakerView(speaker: speaker)
                        }
                        Spacer()
                    }.font(.body).padding(.bottom)
                    Text(description).font(.body)
                }
                Spacer()
            }.padding()
        }
    }
}

struct TalkDetailView_Previews: PreviewProvider {
    static var previews: some View {
        let viewModel = TalkDetailViewModel(title: "Kotlin", room: "Foo", description: "Lorem Ipsum", speakers: [SpeakerViewModel(name: "Alexander von Below", subtitle: "Deutsche Telekom AG")])
        return TalkDetailView(viewModel: viewModel)
    }
}
