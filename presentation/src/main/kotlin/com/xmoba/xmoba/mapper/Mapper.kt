package com.xmoba.xmoba.mapper

/**
 * Created by david on 7/8/18.
 */
interface Mapper<D, V> {

    fun map(domainObject: D): V

    fun mapList(domainObjects: List<D>?): List<V>? {

        var objects: List<V>? = null

        if (domainObjects?.orEmpty()!!.isNotEmpty()) {

            objects = ArrayList()
            domainObjects.mapTo(objects) { map(it) }
        }

        return objects
    }
}