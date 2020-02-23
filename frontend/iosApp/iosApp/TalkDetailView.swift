//
//  TalkDetailView.swift
//  iosApp
//
//  Created by Alexander v. Below on 22.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import SwiftUI

struct TalkDetailView: View {
    var title: String
    var room: String
    var description: String

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
                    Image("sample").resizable().frame(width: 40, height: 40, alignment: .center).clipShape(Circle())
                    VStack {
                        Text ("Alexander")
                        Text ("Telekom")
                    }
                    VStack {
                        Text ("Michal")
                        Text ("Telekom")
                    }
                    Spacer()
                }.font(.body)
                Text(description).font(.body)
                Spacer()
            }.padding()
        }
    }
}

struct TalkDetailView_Previews: PreviewProvider {
    static var previews: some View {
        TalkDetailView(title: "Kotlin", room: "Shanghai", description: "Lorem Ipsum")
    }
}
