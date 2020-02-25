//
//  TalkView.swift
//  iosApp
//
//  Created by Alexander v. Below on 22.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import SwiftUI

struct TalkView: View {
    var title: String
    var speakers: String
    var room: String

    var body: some View {
        VStack (alignment: .leading) {
            Text(title).font(.headline)
            Text(speakers).font(.body)
            Spacer()
            Text(room).font(.body)
        }
    }
}

struct TalkView_Previews: PreviewProvider {
    static var previews: some View {
        TalkView(title: "Kotlin Multiplatform", speakers: "Alexander von Below, Michal Harakal", room: "Rom")
    }
}
