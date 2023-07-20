package com.aasan.sample.base

import android.net.Uri

interface FileSelectionCallback {
    fun onFileSelected(uri: Uri?)
}