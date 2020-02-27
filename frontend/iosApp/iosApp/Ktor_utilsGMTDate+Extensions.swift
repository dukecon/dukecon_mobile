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

    var date: Date? {

        let components = DateComponents(calendar: nil, timeZone: TimeZone(secondsFromGMT: 0), era: nil, year: Int(self.year), month: Int(self.month.ordinal) + 1, day: Int(self.dayOfMonth), hour: Int(self.hours), minute: Int(self.minutes), second: Int(self.seconds), nanosecond: nil, weekday: nil, weekdayOrdinal: nil, quarter: nil, weekOfMonth: nil, weekOfYear: nil, yearForWeekOfYear: nil)

        return Calendar.autoupdatingCurrent.date(from: components)
    }
}
