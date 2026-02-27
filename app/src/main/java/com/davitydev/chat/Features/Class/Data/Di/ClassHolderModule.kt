package com.davitydev.chat.Features.Class.Data.Di

import com.davitydev.chat.Features.Class.Data.Repositories.ClassRepositoryImp
import com.davitydev.chat.Features.Class.Domain.Repository.Class_repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ClassHolderModule {

    @Binds
    abstract fun bindClassRepository(
        classRepositoryImp: ClassRepositoryImp
    ): Class_repository

}