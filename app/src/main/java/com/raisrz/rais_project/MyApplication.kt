package com.raisrz.rais_project

import androidx.multidex.MultiDexApplication
import com.raisrz.rais_project.core.di.CoreComponent
import com.raisrz.rais_project.core.di.DaggerCoreComponent
import com.raisrz.rais_project.di.AppComponent
import com.raisrz.rais_project.di.DaggerAppComponent

open class MyApplication : MultiDexApplication() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}