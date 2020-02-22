//
//  Ktor_utilsGMTDateTests.swift
//  iosAppTests
//
//  Created by Alexander v. Below on 22.02.20.
//  Copyright © 2020 Alexander von Below. All rights reserved.
//

import XCTest
import MultiPlatformLibrary

class Ktor_utilsGMTDateTests: XCTestCase {

    override func setUp() {
        //let now = Date()
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testExample() {
        let ktorDate = DateFormatKt.parseDate("2020-03-17T12:40:00")
        XCTAssertNotNil(ktorDate)
    }


}
