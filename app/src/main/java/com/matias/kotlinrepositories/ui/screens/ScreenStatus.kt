package com.matias.kotlinrepositories.ui.screens

enum class ScreenStatus {
    IDLE,
    LOADING,
    NO_INTERNET,
    ERROR;

    @Suppress("unused") fun isLoading(): Boolean = this == LOADING
}
