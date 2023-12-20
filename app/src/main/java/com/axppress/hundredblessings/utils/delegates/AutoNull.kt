package com.axppress.hundredblessings.utils.delegates

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T : Any> Fragment.autoNull(factory: () -> T): ReadOnlyProperty<Fragment, T> =
    object : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {
        private var toAutoNull: T? = null

        override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
            toAutoNull ?: factory().also {
                // if binding is accessed after Lifecycle is DESTROYED, create new instance, but don't cache it
                if (viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
                    viewLifecycleOwner.lifecycle.addObserver(this)
                    toAutoNull = it
                }
            }

        override fun onDestroy(owner: LifecycleOwner) {
            toAutoNull = null
        }
    }
