package com.sahonmu.burger87.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    protected val _toastLiveData = MutableLiveData<String>()
    var toastLiveData: LiveData<String> = _toastLiveData

    protected val _alertLiveData = MutableLiveData<String>()
    var alertLiveData: LiveData<String> = _alertLiveData

}
