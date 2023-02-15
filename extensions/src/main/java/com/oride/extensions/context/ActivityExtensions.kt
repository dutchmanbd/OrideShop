package com.oride.extensions.context

import android.app.Activity
import android.util.Log
import com.afollestad.materialdialogs.MaterialDialog
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.oride.extensions.R


fun Activity.checkPermissions(
    permissions: List<String>,
    permissionListener: (areAllGranted: Boolean) -> Unit
) {
    Dexter.withContext(this)
        .withPermissions(
            permissions
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                if (report.isAnyPermissionPermanentlyDenied) {
                    MaterialDialog(this@checkPermissions).show {
                        title(R.string.permanently_denied)
                        message(R.string.please_enable_permission)
                        positiveButton {
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            val uri: Uri = Uri.fromParts("package", packageName, null)
                            intent.data = uri
                            startActivity(intent)
                        }
                    }
                } else {
                    permissionListener(report.areAllPermissionsGranted())
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: List<PermissionRequest?>?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }
        }).check()
}


fun Activity.checkPermissions(
    vararg permissions: String,
    permissionListener: (areAllGranted: Boolean) -> Unit
) {

    Dexter.withContext(this)
        .withPermissions(
            *permissions
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                Log.d("Permissions", "onPermissionsChecked: ${report.areAllPermissionsGranted()}")
                permissionListener(report.areAllPermissionsGranted())
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: List<PermissionRequest?>?,
                token: PermissionToken?
            ) {
                Log.d("Permissions", "onPermissionsChecked: onPermissionRationaleShouldBeShown")
                token?.continuePermissionRequest()
            }
        }).check()
}


fun Activity.checkPermission(
    permission: String,
    permissionListener: (areAllGranted: Boolean) -> Unit
) {
    Dexter.withContext(this)
        .withPermission(
            permission
        ).withListener(object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                permissionListener(true)
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                val permanentlyDenied = p0?.isPermanentlyDenied ?: false
                if (permanentlyDenied) {
                    MaterialDialog(this@checkPermission).show {
                        title(R.string.permanently_denied)
                        message(R.string.please_enable_permission)
                        positiveButton {
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            val uri: Uri = Uri.fromParts("package", packageName, null)
                            intent.data = uri
                            startActivity(intent)
                        }
                    }
                }
//                permissionListener(false)
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?,
                p1: PermissionToken?
            ) {
                p1?.continuePermissionRequest()
            }

        }).check()
}