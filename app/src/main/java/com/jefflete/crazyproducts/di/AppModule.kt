package com.jefflete.crazyproducts.di

import com.jefflete.crazyproducts.core.domain.usecase.ProductInteractor
import com.jefflete.crazyproducts.core.domain.usecase.ProductUseCase
import com.jefflete.crazyproducts.detail.DetailViewModel
import com.jefflete.crazyproducts.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ProductUseCase> { ProductInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
