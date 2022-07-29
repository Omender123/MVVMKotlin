package com.example.mvvmtask.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mvvmtask.Model.CoinListResModel
import com.example.mvvmtask.R
import com.example.mvvmtask.Resporities.CoinListRepository
import com.example.mymftcustomer.network.APIFactory
import com.example.mymftcustomer.utils.Global
import com.example.mymftcustomer.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CoinListViewModel(application: Application): AndroidViewModel(application) {
    var TAG = "CoinListViewModel"
    private val mContext get() = getApplication<Application>().applicationContext
    private val parentJob = Job()
    private val coroutineContext : CoroutineContext = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    private val coinListRepo = CoinListRepository(APIFactory.makeServiceAPi())
    private val liveDataCoinListObserver = SingleLiveEvent<CoinListResModel>()
    private val liveDataProgressBarObserver = SingleLiveEvent<Boolean>()
    private val liveDataMessageObserver = SingleLiveEvent<String>()

    val getProgressObserver
        get () :LiveData<Boolean>
        = liveDataProgressBarObserver
    val getMessageObserver
        get() : LiveData<String>
        = liveDataMessageObserver
    val getCoinListObserver
        get () : LiveData<CoinListResModel>
        = liveDataCoinListObserver


    fun getcoinList(){
        if(!Global.hasInternet(mContext)){
            liveDataMessageObserver.postValue(mContext.resources.getString(R.string.pls_check_your_internet_connection))
        }

        scope.launch {
            liveDataProgressBarObserver.postValue(true)
            var response = coinListRepo.getcoinlist()
            Log.d(TAG, "getAddAddress: $response")
            response?.let {
                if(it.msg == "Success"){
                    liveDataCoinListObserver.postValue(it)
                    liveDataMessageObserver.postValue(it.msg)
                }
                liveDataMessageObserver.postValue(it.msg)
            }?:liveDataMessageObserver.postValue(response?.msg)
            liveDataProgressBarObserver.postValue(false)
        }

    }
}