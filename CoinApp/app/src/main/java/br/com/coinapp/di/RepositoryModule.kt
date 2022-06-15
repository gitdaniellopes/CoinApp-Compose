package br.com.coinapp.di

import br.com.coinapp.data.repository.CoinRepositoryImpl
import br.com.coinapp.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCoinRepository(repositoryImpl: CoinRepositoryImpl): CoinRepository
}