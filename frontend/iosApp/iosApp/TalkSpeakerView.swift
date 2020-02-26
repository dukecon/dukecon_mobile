//
//  TalkSpeakerView.swift
//  iosApp
//
//  Created by Alexander v. Below on 26.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import SwiftUI

class ImageProvider: ObservableObject {
    @Published var image: UIImage = UIImage(systemName: "person.circle")!
    var url: URL? {
        didSet {
            if let url = url {
                URLSession(configuration: .default).dataTask(with: url) { (data, response, error) in
                    if let data = data, error == nil {
                        guard let image = UIImage(data: data) else {
                            return
                        }
                        DispatchQueue.main.async {
                            self.image = image
                        }
                    }
                }.resume()
            }
        }
    }
}

struct TalkSpeakerView: View {
    var speaker: SpeakerViewModel
    @ObservedObject var imageProvider = ImageProvider()

    init(speaker: SpeakerViewModel) {
        self.speaker = speaker
        if let imageUrl = speaker.imageURL {
            imageProvider.url = imageUrl
        }
    }

    var body: some View {
        HStack {
            Image(uiImage: self.imageProvider.image).resizable().frame(width: 40, height: 40, alignment: .center).clipShape(Circle())
            VStack {
                Text (speaker.name)
                Text (speaker.subtitle)
            }
        }
    }
}

struct TalkSpeakerView_Previews: PreviewProvider {
    static var previews: some View {
        TalkSpeakerView(speaker: SpeakerViewModel(name: "Alexander", subtitle: "Telekom"))
    }
}
