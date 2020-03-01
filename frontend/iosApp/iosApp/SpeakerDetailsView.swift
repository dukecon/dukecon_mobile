//
//  SpeakerDetailsView.swift
//  iosApp
//
//  Created by Alexander v. Below on 26.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import SwiftUI

struct SpeakerDetailsViewModel {
    var name: String
    var imageURL: URL?
    var link: URL?
    var description: String
}

struct SpeakerDetailsView: View {
    var viewModel: SpeakerDetailsViewModel
    @ObservedObject var imageProvider = ImageProvider()

    init(viewModel: SpeakerDetailsViewModel) {
        self.viewModel = viewModel
        if let imageUrl = viewModel.imageURL {
            imageProvider.url = imageUrl
        }

    }
    var body: some View {
        VStack(alignment: .leading) {
            Image(uiImage: self.imageProvider.image).resizable() .aspectRatio(contentMode: .fit).frame(maxWidth: .infinity, alignment: .center)
            Button(action: {
                UIApplication.shared.open(self.viewModel.link!)
            }) {
                Text(viewModel.link?.absoluteString ?? "Weird")
            }
            Text(viewModel.description)
            Spacer()
        }.padding(.leading).navigationBarTitle(viewModel.name)
    }
}

struct SpeakerDetailsView_Previews: PreviewProvider {
    static var previews: some View {
        SpeakerDetailsView(viewModel: SpeakerDetailsViewModel(name: "Alexander von Below", link:URL(string: "https://example.com"), description: "Lorem Ipsum"))
    }
}
