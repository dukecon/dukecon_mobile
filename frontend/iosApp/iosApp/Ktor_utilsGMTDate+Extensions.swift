//
//  Ktor_utilsGMTDate+Extensions.swift
//  iosApp
//
//  Created by Alexander v. Below on 22.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import MultiPlatformLibrary

extension Ktor_utilsGMTDate {
    func isSameDate(_ other: Ktor_utilsGMTDate) -> Bool {
        if self.year == other.year, self.month == other.month, self.dayOfMonth == other.dayOfMonth {
            return true
        }
        return false
    }
}
