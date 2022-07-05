package com.nuclominus.diffadapter.sample.di

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
abstract class ResourceModule {

    @Binds
    abstract fun bindResourcesProvider(
        resourceProviderImp: ResourceProviderImp
    ): ResourceProvider
}

interface ResourceProvider {
    fun getAppContext(): Context
    fun getResources(): Resources
    fun getString(@StringRes resId: Int): String
}

class ResourceProviderImp @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceProvider {

    override fun getAppContext(): Context {
        return context
    }

    override fun getResources(): Resources {
        return context.resources
    }

    override fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }
}