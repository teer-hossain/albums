package com.exercise.album.ext


import com.exercise.album.support.schedulers.BaseSchedulerProvider
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Extension function to subscribe on the background thread and observe on the main thread  for a [Single]
 * */
fun <T> Single<T>.performOnBackOutOnMain(scheduler: BaseSchedulerProvider): Single<T> {
    return this.subscribeOn(scheduler.io())
        .observeOn(scheduler.ui())
}

/**
 * Extension function to subscribe on the background thread and observe on the main thread for a [Observable]
 * */
fun <T> Observable<T>.performOnBackOutOnMain(scheduler: BaseSchedulerProvider): Observable<T> {
    return this.subscribeOn(scheduler.io())
        .observeOn(scheduler.ui())
}

/**
 * Extension function to add a Disposable to a CompositeDisposable
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}