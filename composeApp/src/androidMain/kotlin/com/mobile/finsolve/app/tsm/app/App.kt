package com.mobile.finsolve.app.tsm.app

import android.app.Application
import com.mobile.finsolve.app.tsm.data.store.TSMPreferencesHolder

class App: Application() {

    override fun onCreate() {
        //TODO move to content provider
        TSMPreferencesHolder.init(
            context = this
        )
        super.onCreate()
    }

}