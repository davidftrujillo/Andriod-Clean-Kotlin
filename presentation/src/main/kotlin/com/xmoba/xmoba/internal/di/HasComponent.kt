package com.xmoba.xmoba.internal.di

/**
 * Created by david on 6/8/18.
 *
 * Interface representing a contract for clients that contains a component for dependency injection
 */
interface HasComponent<C> {

    fun getComponent(): C
}