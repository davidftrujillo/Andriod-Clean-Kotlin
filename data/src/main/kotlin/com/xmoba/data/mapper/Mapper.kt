package com.xmoba.data.mapper

import com.xmoba.domain.model.UserLocation

/**
 * Created by david on 7/8/18.
 */
interface Mapper<E, D> {

    fun map(entityObject: E): D

    fun mapList(entityObjects: List<E>?): List<D>? {

        var objects: List<D>? = null

        if (entityObjects?.orEmpty()!!.isNotEmpty()) {

            objects = ArrayList()
            entityObjects.mapTo(objects) { map(it) }
        }

        return objects
    }
}