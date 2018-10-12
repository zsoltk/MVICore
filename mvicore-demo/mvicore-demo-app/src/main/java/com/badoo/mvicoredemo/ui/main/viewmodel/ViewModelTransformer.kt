package com.badoo.mvicoredemo.ui.main.viewmodel

import com.badoo.feature1.Feature1
import com.badoo.feature2.Feature2
import com.badoo.mvicoredemo.R

class ViewModelTransformer : (Pair<Feature1.State, Feature2.State>) -> ViewModel {

    override fun invoke(pair: Pair<Feature1.State, Feature2.State>): ViewModel {
        val (state1, state2) = pair

        return ViewModel(
            buttonColors = colors(state1.activeButtonIdx),
            counter = state1.counter,
            imageIsLoading = state2.isLoading,
            imageUrl = state2.imageUrl
        )
    }

    private fun colors(active: Int?): List<Int> = listOf(
        if (active == 0) R.color.pink_800 else R.color.pink_500,
        if (active == 1) R.color.light_blue_800 else R.color.light_blue_500,
        if (active == 2) R.color.lime_800 else R.color.lime_500,
        if (active == 3) R.color.yellow_800 else R.color.yellow_500
    )
}


abstract class BufferTransformer<T : Any> : (T) -> R {
    private var prev: R? = null

    override fun invoke(p1: T): R {
        return transform(p1).also {
            prev = it
        }
    }

    abstract fun transform(p1: T) : R
}

//class TransientTransformer<T : Any> : (T) -> TransientField<T> {
//    private var prev: T? =null
//
//    override fun invoke(current: T): TransientField<T> {
//        if (prev == null) {
//            prev = current
//        }
//
//        return TransientField(prev!!, current).also {
//            prev = current
//        }
//    }
//}
//
//private class StateToViewModelTransformer<T : Any, R : Any>: (T) -> R {
//    var prev: T? = null
//
//    override fun invoke(current: T): R {
//        if (prev == null) {
//            prev = current
//        }
//
//        return ViewModel(
//            x = TransientField(prev.x, current.x),
//            y = TransientField(prev.y, current.y),
//            z = TransientField(prev.z, current.z)
//        ).also { prev = current }
//    }
//}


//private class PairBuffer<T : Any>: (T) -> Pair<T, T> {
//    var prev: T? = null
//
//    override fun invoke(current: T): Pair<T, T> {
//        if (prev == null) {
//            prev = current
//        }
//
//        return (prev!! to current).also { prev = current }
//    }
//
//    companion object {
//
//    }
//}



//fun bla() {
//    val feature: Feature1
//    val view: Consumer<Transient<ViewModel>>
//    val binder = Binder()
//    binder.bind(Observable.wrap(feature) to view using )
//}

//fun <T : Any> Observable<T>.decorateWithTransitions(): Observable<T> = this
//    .startWith(take(1)
//    .scan { prev, current ->
//        current.copy(
//            isInitial = prev.isInitial && current.isInitial,
//            immersiveModeTransition = prev.isImmersive to current.isImmersive,
//            bgColorTransition = when (Math.abs(current.currentMoodIndex - prev.currentMoodIndex)) {
//                1 -> listOf(prev.bgColor to current.bgColor)
//                2 -> listOf(prev.bgColor to current.moods[1].bgColor, current.moods[1].bgColor to current.bgColor)
//                else -> listOf()
//            }
//        )
//    }


