package com.jefflete.crazyproducts.core.di

import androidx.room.Room
import com.jefflete.crazyproducts.core.data.ProductRepository
import com.jefflete.crazyproducts.core.data.source.local.LocalDataSource
import com.jefflete.crazyproducts.core.data.source.local.room.ProductDatabase
import com.jefflete.crazyproducts.core.data.source.remote.RemoteDataSource
import com.jefflete.crazyproducts.core.data.source.remote.network.ApiService
import com.jefflete.crazyproducts.core.domain.repository.IProductRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory { get<ProductDatabase>().productDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("jeffleteCrazyProduct".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(), ProductDatabase::class.java, "Product.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {
        val hostname = "dummyjson.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/OUpveBKO8mSJ7HgAb7i5XJszxSA9ZPYsrMnEQjQtFpQ=")
            .add(hostname, "sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=")
            .add(hostname, "sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create()).client(get()).build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IProductRepository> {
        ProductRepository(
            get(), get()
        )
    }
}