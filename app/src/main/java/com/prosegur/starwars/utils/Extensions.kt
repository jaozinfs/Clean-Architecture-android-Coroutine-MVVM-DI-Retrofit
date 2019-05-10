package com.prosegur.starwars.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.net.Uri
import android.widget.ImageView
import com.prosegur.s.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by João Victor Oliveira on 07,Maio,2019
 */


//Show aler with message and listener
fun Activity.showAlertMessage( message: Int) {

    AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { dialog, _ ->
            dialog.dismiss()
        })
        .show()
}


//Create loading progress dialog
inline fun Activity.createLoadingDialog(): AlertDialog {
    val loadingView = this.layoutInflater.inflate(R.layout.layout_dialog_progress_loading, null)

    val progress_dialog = AlertDialog.Builder(this)
        .setCancelable(false)
        .setView(loadingView)
        .create()

    progress_dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    progress_dialog.window!!.setLayout(8, 8)

    return progress_dialog
}

//Load image url and set in imageview with Picasso
fun ImageView.loadImageUrl( url: Uri ){
    Picasso.get()
        .load(url)
        .fit()
        .centerCrop()
        .into(this)
}

fun Date.dateToString() : String{
    val format =  SimpleDateFormat("dd/MM/yyyy")
    return format.format(this)
}