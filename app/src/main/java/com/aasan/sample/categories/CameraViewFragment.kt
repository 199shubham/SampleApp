package com.aasan.sample.categories

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.aasan.sample.MainActivity
import com.aasan.sample.R
import com.aasan.sample.base.*
import com.aasan.sample.databinding.CameraFragmentBinding
import com.aasan.sample.databinding.FragmentFirstCategoryBinding
import com.aasan.sample.splash.SplashLandingViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors


private const val ARG_PARAM1 = "param1"

private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class CameraViewFragment : BaseFragment<SplashLandingViewModel>(),AppNavigator.CameraViewNavigator,
    CameraCallback,FileSelectionCallback {

    private var param1 :String? = null

    private var param2 :String? = null

    override var layoutId: Int = R.layout.camera_fragment

    val viewModel :SplashLandingViewModel by viewModels()

    val profileBinding :CameraFragmentBinding by lazy {
        binding as CameraFragmentBinding
    }

    lateinit var cameraClick: CameraClick

    private var imagePreview: Preview? = null

    private var imageCapture: ImageCapture? = null

    private val cameraExecutor = Executors.newSingleThreadExecutor()

    private lateinit var outputDirectory: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    companion object{
        private const val STOCK_ID = "stockId"
        fun newInstance(stockSymbol:Int?)=
            CameraViewFragment().apply {
                arguments = Bundle().apply {
                    putInt(STOCK_ID,stockSymbol?:0)
                }
            }



        private const val TAG = "CameraXGFG"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val FILENAME = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val PHOTO_EXTENSION = ".jpg"
        private const val REQUEST_CODE_PERMISSIONS = 10
      //  private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)

        fun createFile(baseFolder: File, format: String, extension: String) =
            File(baseFolder, SimpleDateFormat(format, Locale.US)
                .format(System.currentTimeMillis()) + extension)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)

        viewModel.navigators = this

        profileBinding.vm = viewModel


        (requireActivity() as MainActivity).pictureCallback = this
        (requireActivity() as MainActivity).fileSelectionCallback = this
        (requireActivity() as MainActivity).handleCamerasPermissions()
       // outputDirectory = getOutputDirectory()

        return profileBinding?.root
    }

    private fun getOutputDirectory(): File {
        val mediaDir = requireActivity().externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else requireActivity().filesDir
    }

    override fun onCameraViewClick() {

            // Get a stable reference of the
            // modifiable image capture use case
            val imageCapture = imageCapture ?: return

            // Create time-stamped output file to hold the image
            val photoFile = File(
                requireActivity().filesDir ,".jpg"
            )
        val savedUri = Uri.fromFile(photoFile)
        profileBinding.imgView.visibility = View.VISIBLE
        profileBinding.imgView.setImageURI(savedUri)
        profileBinding.viewFinder.visibility = View.GONE


           // Create output options object which contains file + metadata
            val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

            // Set up image capture listener,
            // which is triggered after photo has
            // been taken
           /* imageCapture.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(requireActivity()),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onError(exc: ImageCaptureException) {
                        Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                    }

                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                        val savedUri = Uri.fromFile(photoFile)

                        // set the saved uri to the image view
                        profileBinding.imgView.visibility = View.VISIBLE
                        profileBinding.imgView.setImageURI(savedUri)
                        profileBinding.viewFinder.visibility = View.GONE

                        val msg = "Photo capture succeeded: $savedUri"
                        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
                        Log.d(TAG, msg)
                    }
                })*/
    }

    override fun onCameraImageReceived() {

    }

    override fun onCameraPermissionAllowed() {
        System.out.println("CallBack")

        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireActivity())

        cameraProviderFuture.addListener(Runnable {

            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(profileBinding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireActivity()))
    }




    override fun onFileSelected(uri: Uri?) {
       System.out.println("URI" + uri)
    }

}