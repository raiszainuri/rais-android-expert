package com.raisrz.rais_project

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.raisrz.rais_project.core.di.CoreComponent
import com.raisrz.rais_project.core.di.DaggerCoreComponent
import com.raisrz.rais_project.di.AppComponent
import com.raisrz.rais_project.di.DaggerAppComponent

open class MyApplication : Application() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}