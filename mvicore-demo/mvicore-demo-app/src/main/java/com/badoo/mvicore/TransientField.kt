package com.badoo.mvicore

import io.reactivex.Observable
import io.reactivex.ObservableSource

//
//class TransientField<out T : Any>(val prev: T, val current: T) {
//}
//
//fun <T : Any> TransientField<T>.onChange(f: (T) -> Unit) {
//    if (prev != current) f(current)
//}
//
//fun <T : Any> TransientField<T>.onNoChange(f: (T) -> Unit) {
//    if (prev == current) f(current)
//}
//
//
//fun TransientField<Boolean>.onSwitchedToTrue(f: () -> Unit) {
//    if (!prev && current) f()
//}
//
//fun TransientField<Boolean>.onSwitchedToFalse(f: () -> Unit) {
//    if (prev && !current) f()
//}
//
//fun TransientField<Boolean>.onStaysTrue(f: () -> Unit) {
//    if (prev && current) f()
//}
//
//fun TransientField<Boolean>.onStaysFalse(f: () -> Unit) {
//    if (!prev && !current) f()
//}

typealias Transient<T> = Pair<T, T>

class PairBuffer<T : Any>: (T) -> Pair<T?, T> {
    var prev: T? = null

    override fun invoke(current: T): Pair<T?, T> {
        if (prev == null) {
            prev = current
        }

        return (prev to current).also { prev = current }
    }

    companion object {

    }
}

fun <T : Any> lastTwoElementsOf(observableSource: ObservableSource<T>): Observable<Pair<T?, T>> {
    val observable = Observable.wrap(observableSource)

    return observable
        .startWith(observable.take(1))
        .buffer(2, 1)
        .map { it[0] to it[1] }
}

fun <T : Any, R> Pair<T?, T>.onFieldChanged(fieldSelector: T.() -> R, f: (R) -> Unit) {
    val prevValue = first?.fieldSelector()
    val currentValue = second.fieldSelector()
    if (prevValue != currentValue) {
        f(currentValue)
    }
}

fun <T : Any> Pair<T?, T>.onStaysTrue(fieldSelector: T.() -> Boolean, f: (Boolean) -> Unit) {
    val prevValue = first?.fieldSelector()
    val currentValue = second.fieldSelector()
    if (prevValue != null && prevValue && currentValue) {
        f(currentValue)
    }
}

fun <T : Any> Pair<T?, T>.onStaysFalse(fieldSelector: T.() -> Boolean, f: (Boolean) -> Unit) {
    val prevValue = first?.fieldSelector()
    val currentValue = second.fieldSelector()
    if (prevValue != null && !prevValue && !currentValue) {
        f(currentValue)
    }
}

fun <T : Any> Pair<T?, T>.onChangedToTrue(fieldSelector: T.() -> Boolean, f: (Boolean) -> Unit) {
    val prevValue = first?.fieldSelector()
    val currentValue = second.fieldSelector()
    if ((prevValue == null || !prevValue) && currentValue) {
        f(currentValue)
    }
}

fun <T : Any> Pair<T?, T>.onChangedToFalse(fieldSelector: T.() -> Boolean, f: (Boolean) -> Unit) {
    val prevValue = first?.fieldSelector()
    val currentValue = second.fieldSelector()
    if ((prevValue == null || prevValue) && !currentValue) {
        f(currentValue)
    }
}
