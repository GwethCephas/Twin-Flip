package com.twinflip.app.di

import com.twinflip.core.data.datasource.ThemeProvider
import com.twinflip.core.data.persistence.datastore.ThemeDatastore
import com.twinflip.core.data.repository.CardRepositoryImpl
import com.twinflip.core.data.repository.ThemeRepositoryImpl
import com.twinflip.core.domain.game.GameEngine
import com.twinflip.core.domain.repository.CardRepository
import com.twinflip.core.domain.repository.ThemeRepository
import com.twinflip.core.domain.usecase.CardsUseCase
import com.twinflip.core.domain.usecase.ThemesUseCase
import com.twinflip.feature_multiplayer.presentation.MultiplayerViewModel
import com.twinflip.feature_singleplayer.presentation.game.GameViewModel
import com.twinflip.feature_themes.presentation.ThemeViewModel
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

    single { GameEngine(get()) }

    viewModel { ThemeViewModel(get()) }

    viewModel { GameViewModel(get()) }

    viewModel { MultiplayerViewModel(get()) }



}