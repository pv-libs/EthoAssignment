package com.assingment.etho.utils


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.assingment.etho.base.BaseViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun CoroutineScope.safeLaunch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return launch(context + coroutineExceptionHandler, start, block)
}

fun LifecycleOwner.safeLaunch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    showToastOnError: Boolean = true,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return lifecycleScope.safeLaunch(context, start) {
        try {
            block()
        } catch (e: Exception) {
            Timber.e(e)
            if (showToastOnError && e !is CancellationException) {
                showToast(e)
            }
            throw e
        }
    }
}

private fun LifecycleOwner.showToast(throwable: Throwable) {
    val context = when (this) {
        is Context -> this
        is Fragment -> context
        else -> null
    }
    context?.showToast(throwable)
}

/**
 * Launches and runs the given block when the [Lifecycle] controlling this
 * [LifecycleCoroutineScope] is at least in [Lifecycle.State.CREATED] state.
 *
 * The returned [Job] will be cancelled when the [Lifecycle] is destroyed.
 * @see Lifecycle.whenCreated
 * @see Lifecycle.coroutineScope
 */
fun LifecycleOwner.safeLaunchWhenCreated(
    showToastOnError: Boolean = true, block: suspend CoroutineScope.() -> Unit
) = safeLaunch {
    lifecycle.whenStateAtLeast(Lifecycle.State.CREATED) {
        try {
            block()
        } catch (e: Exception) {
            if (showToastOnError) {
                showToast(e)
            }
            throw e
        }
    }
}

/**
 * Launches and runs the given block when the [Lifecycle] controlling this
 * [LifecycleCoroutineScope] is at least in [Lifecycle.State.STARTED] state.
 *
 * The returned [Job] will be cancelled when the [Lifecycle] is destroyed.
 * @see Lifecycle.whenStarted
 * @see Lifecycle.coroutineScope
 */

fun LifecycleOwner.safeLaunchWhenStarted(
    showToastOnError: Boolean = true, block: suspend CoroutineScope.() -> Unit
) = safeLaunch {
    lifecycle.whenStateAtLeast(Lifecycle.State.STARTED) {
        try {
            block()
        } catch (e: Exception) {
            if (showToastOnError) {
                showToast(e)
            }
            throw e
        }
    }
}

/**
 * Launches and runs the given block when the [Lifecycle] controlling this
 * [LifecycleCoroutineScope] is at least in [Lifecycle.State.RESUMED] state.
 *
 * The returned [Job] will be cancelled when the [Lifecycle] is destroyed.
 * @see Lifecycle.whenResumed
 * @see Lifecycle.coroutineScope
 */
fun LifecycleOwner.safeLaunchWhenResumed(
    showToastOnError: Boolean = true,
    block: suspend CoroutineScope.() -> Unit
) = safeLaunch {
    lifecycle.whenStateAtLeast(Lifecycle.State.RESUMED) {
        try {
            block()
        } catch (e: Exception) {
            if (showToastOnError) {
                showToast(e)
            }
            throw e
        }
    }
}


fun BaseViewModel.safeLaunch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    showToastOnError: Boolean = true,
    block: suspend CoroutineScope.() -> Unit
) = viewModelScope.safeLaunch(context, start) {
    try {
        block()
    } catch (e: Exception) {
        if (showToastOnError) {
            // showToastErrorMessage(e)
        }
        throw e
    }
}