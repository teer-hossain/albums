package com.exercise.album.support.schedulers

import androidx.annotation.NonNull
import io.reactivex.Scheduler

/**
 * Allow providing different types of [Scheduler]s.
 */
interface BaseSchedulerProvider {

    @NonNull
    fun computation(): Scheduler

    @NonNull
    fun io(): Scheduler

    @NonNull
    fun ui(): Scheduler
}