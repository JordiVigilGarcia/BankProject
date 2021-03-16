package com.android.data.di
import org.koin.dsl.module
import com.android.data.di.providers.*

val dataModule = module {
    single { provideOkHttpClient(get()) }
    single { provideMockInterceptor(get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
    single { provideTransactionApi(get()) }
    single { provideBankDatabase(get()) }
    single { provideTransactionRepository(get(), get(), get()) }
}