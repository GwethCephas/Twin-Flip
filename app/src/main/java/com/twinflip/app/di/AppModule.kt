package com.twinflip.app.di

import com.twinflip.core.data.datasource.ThemeProvider
import com.twinflip.core.data.persistence.datastore.ThemeDatastore
import com.twinflip.core.data.repository.CardRepositoryImpl
import com.twinflip.feature_themes.data.repository.ThemeRepositoryImpl
import com.twinflip.core.domain.repository.CardRepository
import com.twinflip.feature_themes.domain.repository.ThemeRepository
import com.twinflip.core.domain.usecase.CardsUseCase
import com.twinflip.feature_themes.domain.usecase.ThemesUseCase
import com.twinflip.core.presentation.game.GameViewModel
import com.twinflip.feature_themes.presentation.theme.ThemeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { ThemeProvider(androidContext()) }

    single { ThemeDatastore(androidContext()) }

    single<ThemeRepository> { ThemeRepositoryImpl(get(), get()) }

    single<CardRepository> { CardRepositoryImpl(get()) }

    single { ThemesUseCase(get()) }

    single { CardsUseCase(get()) }

    viewModel { ThemeViewModel(get()) }

    viewModel { GameViewModel(get()) }



}