package com.github.shohboz.imagepicker.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.github.shohboz.imagepicker.R
import com.github.shohboz.imagepicker.constant.ImageProvider
import com.github.shohboz.imagepicker.listener.DismissListener
import com.github.shohboz.imagepicker.listener.ResultListener

/**
 * Show Dialog
 *
 * @author Dhaval Patel
 * @version 1.0
 * @since 04 January 2018
 */
internal object DialogHelper {

    /**
     * Show Image Provide Picker Dialog. This will streamline the code to pick/capture image
     *
     */
    fun showChooseAppDialog(
        context: Context,
        listener: ResultListener<ImageProvider>,
        dismissListener: DismissListener?,
        resId:Int
    ) {
        val layoutInflater = LayoutInflater.from(context)
        val customView = layoutInflater.inflate(resId, null)

        val dialog = AlertDialog.Builder(context)
            .setView(customView)
            .setOnCancelListener {
                listener.onResult(null)
            }
            .setOnDismissListener {
                dismissListener?.onDismiss()
            }
            .show()

        // Handle Camera option click
        customView.findViewById<View>(R.id.lytCameraPick).setOnClickListener {
            listener.onResult(ImageProvider.CAMERA)
            dialog.dismiss()
        }

        // Handle Gallery option click
        customView.findViewById<View>(R.id.lytGalleryPick).setOnClickListener {
            listener.onResult(ImageProvider.GALLERY)
            dialog.dismiss()
        }

        customView.findViewById<View>(R.id.btn_cancel).setOnClickListener {
            listener.onResult(null)
            dialog.dismiss()
        }
    }
}
