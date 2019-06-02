package com.exercise.album.ext


import com.exercise.album.support.schedulers.BaseSchedulerProvider
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Extension function to subscribe on the background thread and observe on the main thread for a [Completable]
 * */
fun Completable.performOnBackOutOnMain(scheduler: BaseSchedulerProvider): Completable {
    return this.subscribeOn(scheduler.io())
        .observeOn(scheduler.ui())
}

/**
 * Extension function to subscribe on the background thread and observe on the main thread for a [Flowable]
 * */
fun <T> Flowable<T>.performOnBackOutOnMain(scheduler: BaseSchedulerProvider): Flowable<T> {
    return this.subscribeOn(scheduler.io())
        .observeOn(scheduler.ui())
}

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

/**
 * Extension function to subscribe on the background thread for a Flowable
 * */
fun <T> Flowable<T>.performOnBack(scheduler: BaseSchedulerProvider): Flowable<T> {
    return this.subscribeOn(scheduler.io())
}

/**
 * Extension function to subscribe on the background thread for a Completable
 * */
fun Completable.performOnBack(scheduler: BaseSchedulerProvider): Completable {
    return this.subscribeOn(scheduler.io())
}

/**
 * Extension function to subscribe on the background thread for a Observable
 * */
fun <T> Observable<T>.performOnBack(scheduler: BaseSchedulerProvider): Observable<T> {
    return this.subscribeOn(scheduler.io())
}