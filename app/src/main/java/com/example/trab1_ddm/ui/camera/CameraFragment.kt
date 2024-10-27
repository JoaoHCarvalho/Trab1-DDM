package com.example.trab1_ddm.ui.camera

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import com.example.trab1_ddm.R
import com.example.trab1_ddm.dao.UserDAO
import java.io.File

class CameraFragment : Fragment() {

    private lateinit var previewView: PreviewView
    private lateinit var captureButton: Button
    private lateinit var saveButton: Button
    private lateinit var clearButton: Button
    private lateinit var photoView: ImageView
    private var imageCapture: ImageCapture? = null
    private var currentBitmap: Bitmap? = null
    private lateinit var cameraExecutor: ExecutorService
//    private lateinit var displayedImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cam, container, false)
//        val viewAnother = inflater.inflate(R.layout.nav_header_main, container, false)
        previewView = view.findViewById(R.id.previewCamera)
        captureButton = view.findViewById(R.id.buttonTakePhoto)
        saveButton = view.findViewById(R.id.buttonSavePhoto)
        clearButton = view.findViewById(R.id.buttonClearPhoto)
        photoView = view.findViewById(R.id.photoView)
//        displayedImageView = viewAnother.findViewById(R.id.imageView)


        cameraExecutor = Executors.newSingleThreadExecutor()


        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), 10)
        }


        captureButton.setOnClickListener { takePhoto() }
        saveButton.setOnClickListener { savePhoto() }
        clearButton.setOnClickListener { clearPhoto() }

        return view
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            imageCapture = ImageCapture.Builder().build()
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Log.e("CameraXApp", "Erro ao iniciar a câmera", exc)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val photoFile = requireActivity().externalMediaDirs.firstOrNull()?.let {
            File(it, "${System.currentTimeMillis()}.jpg")
        }

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile!!).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e("CameraXApp", "Erro ao salvar imagem: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    Log.d("CameraXApp", "Imagem salva: ${photoFile.absolutePath}")

                    val bitmap = BitmapFactory.decodeFile(photoFile.absolutePath)
                    currentBitmap = rotateBitmapIfNeeded(bitmap)
                    photoView.setImageBitmap(currentBitmap)
                    photoView.visibility = View.VISIBLE
                    previewView.visibility = View.GONE
                    saveButton.visibility = View.VISIBLE
                }
            }
        )
    }

    private fun rotateBitmapIfNeeded(bitmap: Bitmap): Bitmap {
        val matrix = Matrix().apply { postRotate(-90f) }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    private fun savePhoto() {
        val bitmap = currentBitmap ?: return
        val userDAO = UserDAO(requireContext())

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "Photo_${System.currentTimeMillis()}.jpg")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/CameraXApp")
        }

        val uri = requireActivity().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        uri?.let {
            requireActivity().contentResolver.openOutputStream(it)?.use { stream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                Toast.makeText(requireContext(), "Imagem salva!", Toast.LENGTH_SHORT).show()

                // Salvar caminho da imagem no banco de dados
                val imagePath = getRealPathFromURI(it)
                val userId = 1 // Substitua pelo ID do usuário desejado
                val usuario = userDAO.getUsuarioById(userId)
                usuario?.imagem = imagePath
                if (usuario != null) {
                    userDAO.updateUsuario(usuario)
                }
            }
        }
    }

    private fun getRealPathFromURI(uri: Uri): String? {
        var path: String? = null
        val cursor = requireActivity().contentResolver.query(uri, null, null, null, null)
        cursor?.let {
            if (cursor.moveToFirst()) {
                val index = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                path = cursor.getString(index)
            }
            cursor.close()
        }
        return path
    }


    private fun clearPhoto() {
        photoView.setImageBitmap(null)
        photoView.visibility = View.GONE
        previewView.visibility = View.VISIBLE
        saveButton.visibility = View.GONE
        currentBitmap = null
    }

    private fun allPermissionsGranted() = ContextCompat.checkSelfPermission(
        requireContext(), Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

    override fun onDestroyView() {
        super.onDestroyView()
        cameraExecutor.shutdown()
    }
}
