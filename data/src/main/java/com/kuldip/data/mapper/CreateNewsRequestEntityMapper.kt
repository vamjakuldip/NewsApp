package com.kuldip.data.mapper

import com.kuldip.data.model.CreateNewsRequestEntity
import com.kuldip.domain.model.CreateNewsRequest
import javax.inject.Inject


open class CreateNewsRequestEntityMapper @Inject constructor() : EntityMapper<CreateNewsRequest, CreateNewsRequestEntity> {
    override fun mapFromEntity(entity: CreateNewsRequest): CreateNewsRequestEntity {
        return CreateNewsRequestEntity(pageSize = entity.pageSize, page = entity.page)
    }
}