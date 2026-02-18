package com.mobile.finsolve.app.tsm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform