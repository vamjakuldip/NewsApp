package com.kuldip.cache.mapper

interface CacheMapper<C, E> {

    fun mapFromCached(cache: C): E

    fun mapToCached(entity: E): C

}