package com.kuldip.data.mapper

interface EntityMapper<E, D> {

    fun mapFromEntity(entity: E): D

}