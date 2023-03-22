package com.antgut.myapplication.app.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PostQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserQualifier
