package com.prosegur.starwars.features

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.prosegur.starwars.utils.createLoadingDialog
import com.prosegur.starwars.utils.showAlertMessage
import kotlinx.android.synthetic.main.sw_details_activity.*

/**
 * Created by João Victor Oliveira on 07,Maio,2019
 */


open class BaseActivity : AppCompatActivity(), BaseActivityImp{

    open fun shouldShowShimmer() = false

    override fun showShimmer() {
        if(shouldShowShimmer()){
            shimmer_view_container.startShimmer()
        }
    }

    override fun stopShimmer() {
        if(shouldShowShimmer()){
            shimmer_view_container.stopShimmer()
            shimmer_view_container.visibility = View.GONE
        }
    }

    //progress dialog
    private val progressDialog by lazy { createLoadingDialog() }


    //Show Toast message with listener?
    override fun showToastMessage(message: Int) {
        showAlertMessage(message)
    }

    //Show progress dialog in activity
    override fun showProgressDialog() {
        try{
            progressDialog.show()
        }catch (error: Throwable){ }
    }

    //Stop progress dialog in activity
    override fun stopProgressDialog() {
        try{
            progressDialog.hide()
        }catch (error: Throwable){ }

    }

}