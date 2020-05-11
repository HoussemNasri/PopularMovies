package com.example.popularmovies.data.mapper

/**
 * @param F : from type
 * @param T : to type
 * */
interface Mapper<F, T> {
    fun transform(f: F): T
}