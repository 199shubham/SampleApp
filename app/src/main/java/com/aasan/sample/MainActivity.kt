package com.aasan.sample


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.aasan.sample.base.*
import com.aasan.sample.databinding.ActivityMainBinding

import com.globalmed.corelib.repository.SharedPreferenceRepositoryImplementation

import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity :  PermissionHandlerActivity(),CameraClick{
    private val header = MutableLiveData<Header>()
    private lateinit var activityMainBinding: ActivityMainBinding
    val viewModel: BaseViewModel by viewModels()
    var navController: NavController? = null
    lateinit var pictureCallback: CameraCallback
    lateinit var fileSelectionCallback: FileSelectionCallback
    private var imagePreview: Preview? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerPermissionHandlers()
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        activityMainBinding.appBar.outlineProvider = null

        activityMainBinding.frmProgress.setOnClickListener {  }
        viewModel.repository = SharedPreferenceRepositoryImplementation(application)

        setContentView(activityMainBinding.root)
     }

    fun showProgress(_shouldShowProgress: Boolean, _progressText: String?) {
        header.value?.updatedProgressOnly(_shouldShowProgress, _progressText)?.let {
            header.postValue(it)
        }
    }

    fun updateHeader(_header: Header) {
        header.postValue(_header)
        if(_header.currentFragment != 0)
            viewModel.updateDestination(_header.currentFragment)
    }

    override fun onBackPressed() {
        if(navController == null || navController?.graph?.startDestinationId == R.id.splashLandingFragment){
            finish()
        }else{
            if(!navController!!.popBackStack())
                finish()
        }

    }

    fun handleCameraPermissions() {
        requestDynamicPermission("Permission", "Need to access your phone camera",
            object : DynamicPermissionActionHandler {
                @SuppressLint("MissingPermission")
                override fun onPermissionsApproved() {
                    if (this@MainActivity::pictureCallback.isInitialized)
                        pictureCallback.onCameraPermissionAllowed()
                }

                override fun onPermissionsRejected() {
                }
            }, mutableListOf(
                Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
        )
    }

    fun handleCamerasPermissions() {
        requestDynamicPermission("Permission", "Need to access your phone camera",
            object : DynamicPermissionActionHandler {
                @SuppressLint("MissingPermission")
                override fun onPermissionsApproved() {
                    if (this@MainActivity::pictureCallback.isInitialized)
                        startCamera()
                }

                override fun onPermissionsRejected() {
                }
            }, mutableListOf(
                Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
        )
    }

    private val fileSelectorActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            if(this::fileSelectionCallback.isInitialized)
                fileSelectionCallback.onFileSelected(result.data?.data)
        }
    }

    override fun onCameraClickImage() {
        handleCamerasPermissions()
    }

    fun startCamera() {
        pictureCallback.onCameraPermissionAllowed()

    }


}
