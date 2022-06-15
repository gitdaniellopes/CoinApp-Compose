package br.com.coinapp.di

import br.com.coinapp.domain.use_case.get_coin.GetCoinByIdUseCase
import br.com.coinapp.domain.use_case.get_coin.GetCoinByIdUseCaseImpl
import br.com.coinapp.domain.use_case.get_coins.GetCoinsUseCase
import br.com.coinapp.domain.use_case.get_coins.GetCoinsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetCoinsUseCase(useCaseImpl: GetCoinsUseCaseImpl): GetCoinsUseCase

    @Binds
    fun bindGetCoinByIdUseCase(useCaseImpl: GetCoinByIdUseCaseImpl): GetCoinByIdUseCase
}