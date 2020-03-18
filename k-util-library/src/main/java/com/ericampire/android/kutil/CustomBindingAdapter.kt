package com.ericampire.android.kutil


import android.R
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

sealed class Result<out T : Any> {
    class Success<out T: Any>(val data: T) : Result<T> ()
    class Error(val exception: Exception) : Result<Nothing>()
    object InProgress : Result<Nothing>()
}

@BindingAdapter(value = ["loadingState"])
fun ProgressBar.refreshing(result: Result<*>?) {
    isVisible = when(result) {
        is Result.Success -> false
        is Result.Error -> false
        else -> true
    }
}

@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(mAdapter: RecyclerView.Adapter<*>) {
    run {
        setHasFixedSize(true)
        adapter = mAdapter
    }
}

@BindingAdapter(value = ["setSpinnerData"])
fun Spinner.bindSpinnerAdapter(data: List<*>?) {
    if (data == null) return
    ArrayAdapter(context, R.layout.simple_spinner_item, data).run {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter = this
    }
}