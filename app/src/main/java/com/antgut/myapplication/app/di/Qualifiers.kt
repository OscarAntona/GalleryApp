package com.antgut.myapplication.app.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserCacheQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AlbumCacheQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PhotoCacheQualifier