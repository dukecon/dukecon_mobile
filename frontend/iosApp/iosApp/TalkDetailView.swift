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

class ImageProvider: ObservableObject {
    @Published var image: UIImage = UIImage(named: "sample")!
    var url: URL? {
        didSet {
            if let url = url {
                URLSession(configuration: .default).dataTask(with: url) { (data, response, error) in
                    if let data = data, error == nil {
                        guard let image = UIImage(data: data) else {
                            return
                        }
                        self.image = image
                    }
                }.resume()
            }
        }
    }
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

    @ObservedObject var imageProvider = ImageProvider()

    init(viewModel: TalkDetailViewModel) {
        self.title = viewModel.title
        self.room = viewModel.room
        self.description = viewModel.description
        self.speakers = viewModel.speakers

        if let speaker = speakers.first, let imageUrl = speaker.imageURL {
            imageProvider.url = imageUrl
        }
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
                HStack {
                    ForEach(speakers, id:\.name ) {
                        speaker in
                        HStack {
                            Image(uiImage: self.imageProvider.image).resizable().frame(width: 40, height: 40, alignment: .center).clipShape(Circle())
                            VStack {
                                Text (speaker.name)
                                Text (speaker.subtitle)
                            }
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
        let viewModel = TalkDetailViewModel(title: "Kotlin", room: "Foo", description: "Lorem Ipsum", speakers: [SpeakerViewModel(name: "Alexander von Below", subtitle: "Deutsche Telekom AG")])
        return TalkDetailView(viewModel: viewModel)
    }
}
