package com.sid.cartrackcodingchallenge

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

open abstract class BaseActivity : AppCompatActivity() {

    /**
     * hide keyboard from activity
     * @param activity
     */
    fun hideKeyboard(activity: Activity, view: View) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * snack bar to display errors
     */
    fun showSnackBar(coordinatorLayout: CoordinatorLayout, message: String) {
        val snack = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG)
        val view = snack.view
        view.setBackgroundColor(ContextCompat.getColor(this, R.color.text_color_error))
        val snackbarTextView = view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        snackbarTextView.setTextColor(ContextCompat.getColor(this, R.color.text_color_white))
        snack.show()
    }

}