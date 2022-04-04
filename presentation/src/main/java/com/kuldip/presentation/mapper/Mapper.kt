package com.kuldip.presentation.mapper

interface Mapper<V, D> {

    fun mapToView(type: D): V

}