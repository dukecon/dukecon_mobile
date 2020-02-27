//
//  Ktor_utilsGMTDateTests.swift
//  iosAppTests
//
//  Created by Alexander v. Below on 22.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import XCTest
@testable import MultiPlatformLibrary
@testable import iosApp

class Ktor_utilsGMTDateTests: XCTestCase {

    override func setUp() {
        //let now = Date()
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testExample() {

        let originalDate = Date()

        let formatter = ISO8601DateFormatter()
        let dateString = formatter.string(from: originalDate)
//
        let ktorDate = DateFormatKt.parseDate(dateString)
        XCTAssertNotNil(ktorDate)

        let date = ktorDate.date
        XCTAssertNotNil(date)

        let r1 = floor(date!.timeIntervalSinceReferenceDate)
        let r2 = floor(originalDate.timeIntervalSinceReferenceDate.rounded())

        XCTAssertEqual(r1, r2)
    }


}
