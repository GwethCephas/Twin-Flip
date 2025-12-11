package com.twinflip.di

import com.twinflip.data.local.datasource.ThemeProvider
import com.twinflip.data.repository.CardRepositoryImpl
import com.twinflip.data.repository.ThemeRepositoryImpl
import com.twinflip.domain.repository.CardRepository
import com.twinflip.domain.repository.ThemeRepository
import com.twinflip.domain.usecase.CardsUseCase
import com.twinflip.domain.usecase.ThemesUseCase
import com.twinflip.presentation.game.GameViewModel
import com.twinflip.presentation.theme.ThemeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { ThemeProvider(androidContext()) }

    single<ThemeRepository> { ThemeRepositoryImpl(get()) }

    single<CardRepository> { CardRepositoryImpl(get()) }

    single { ThemesUseCase(get()) }

    single { CardsUseCase(get()) }

    viewModel { ThemeViewModel(get()) }

    viewModel { GameViewModel(get()) }

}