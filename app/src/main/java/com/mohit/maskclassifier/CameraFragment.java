package com.mohit.maskclassifier;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.util.concurrent.ListenableFuture;
import com.mohit.maskclassifier.databinding.FragmentCameraBinding;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class CameraFragment extends Fragment {


    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private ImageCapture imageCapture;
    Context context;
    Bitmap bitmap;
    FragmentCameraBinding binding;
    private CameraListener cameraDataHandler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentCameraBinding.inflate(inflater, container, false);
        View root=binding.getRoot();

        cameraProviderFuture = ProcessCameraProvider.getInstance(requireActivity()); // define cameraX process provider.
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                startCamerax(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

        }, getExecutor());
        binding.tvScan.setOnClickListener(view -> scan());

        return root;
    }
    private void scan() {
        imageCapture.takePicture(getExecutor(), new ImageCapture.OnImageCapturedCallback() {
            @Override
            public void onCaptureSuccess(@NonNull ImageProxy imageProxy) {
                super.onCaptureSuccess(imageProxy);
                @SuppressLint("UnsafeOptInUsageError") Image mediaImage = imageProxy.getImage();
                if (mediaImage != null) {
                    Log.d("data","my_image");
                    Bitmap bitmap=getBitmap(imageProxy);
                    binding.ivData.setImageBitmap(bitmap);
                    cameraDataHandler.onScanComplete(bitmap);
                }
            }
        });
    }

    private Bitmap getBitmap(ImageProxy image) {   // converting real time camera data into bitmap image.
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        buffer.rewind();
        byte[] bytes = new byte[buffer.capacity()];
        buffer.get(bytes);
        byte[] clonedBytes = bytes.clone();
        return BitmapFactory.decodeByteArray(clonedBytes, 0, clonedBytes.length);
    }


    private Executor getExecutor() {
        return ContextCompat.getMainExecutor(requireActivity());}    // for implementing threads.

    public void startCamerax(ProcessCameraProvider cameraProvider) {     // initializing camera hardware with camera properties we need like back lens,
        // image capture and image preview.

        cameraProvider.unbindAll();
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
        Preview preview = new Preview.Builder().build();
        preview.setSurfaceProvider(binding.preView.getSurfaceProvider());
        imageCapture = new ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY).build();
        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);
    }

    @Override     // for initializing camera interface after attaching to main activity.
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CameraFragment.CameraListener)
            cameraDataHandler=(CameraFragment.CameraListener) context;
    }

    public interface CameraListener{    // to listen when camera scanning is complete.
        void onScanComplete(Bitmap bitmap);
    }
}