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
                HStack {
                    ForEach(speakers, id:\.name ) {
                        speaker in
                        // speakerImage.resizable().frame(width: 40, height: 40, alignment: .center).clipShape(Circle())
                        VStack {
                            Text (speaker.name)
                            Text (speaker.subtitle)
                        }
                    }
                    Spacer()
                }.font(.body).padding(.bottom)
                Text(description).font(.body)
                Spacer()
            }.padding()
        }
    }
}

struct TalkDetailView_Previews: PreviewProvider {
    static var previews: some View {
        TalkDetailView(title: "Kotlin", room: "Shanghai", description: "Lorem Ipsum", speakers:[SpeakerViewModel(name: "Alexander von Below", subtitle: "Deutsche Telekom AG")])
    }
}
