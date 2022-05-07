package com.pamobo.zemogaposts.application.ext

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
fun Fragment.shortSnackBar(@StringRes message: Int) {
    view?.let {
        Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
    }
}

fun Fragment.shortSnackBar( message: String) {
    view?.let {
        Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
    }
}