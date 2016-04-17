package com.makingiants.caty.model.cache

interface Cache {
    @Suppress("UNCHECKED_CAST")
    fun <U> get(name: String, default: U): U

    fun <U> put(name: String, value: U)
}