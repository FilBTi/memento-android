package com.puroblast.memento.app

import android.app.Application
import com.puroblast.memento.di.AppComponent
import com.puroblast.memento.di.DaggerAppComponent

class MementoApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}
