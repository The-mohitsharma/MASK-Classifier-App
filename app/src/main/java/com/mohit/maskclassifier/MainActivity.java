package com.mohit.maskclassifier;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mohit.maskclassifier.adapters.ProcessedImageAdapter;
import com.mohit.maskclassifier.databinding.ActivityMainBinding;
import com.mohit.maskclassifier.ml.Maskrecognise;
import com.mohit.maskclassifier.utils.ViewAnimation;

import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CameraFragment.CameraListener{

    ActivityMainBinding binding; //  use for activity view binding.
    String[] maskRecognised; //  used for list of mask recognised.
    String[] timeElapsed; //  used for  time elapsed.
    String[] batteryLevel; // battery status for each recognised image.
    Context context;
    private float battery; //  current status of the battery.
    private boolean isRotate=false; //  for switching floating acting button.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());  //  initialised view binding.
        setContentView(binding.getRoot());
        context=this;
        registerReceiver(broadCastReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));  // for setting broadcast receiver for battery changes.
        ViewAnimation.init(binding.fabCamera); // initialised animation in floating action button for camera.
        ViewAnimation.init(binding.fabImage);  // initialised animation in floating action button for image.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED)  // checking if camera permission allowed.
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, 1); // asking user for camera permission.
        binding.fab.setOnClickListener(view -> { // showing and disappearing floating action button for camera and image.
            isRotate=ViewAnimation.rotateFab(view, !isRotate);
            if(isRotate){
                ViewAnimation.showIn(binding.fabImage);
                ViewAnimation.showIn(binding.fabCamera);
            }else{
                ViewAnimation.showOut(binding.fabImage);
                ViewAnimation.showOut(binding.fabCamera);
            }
        });
        binding.fabImage.setOnClickListener(view -> { // only  opening file explorer for images in particular.
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            imageLaunch.launch(intent);
        });
        binding.fabCamera.setOnClickListener(view -> { // for showing camera fragment.
            binding.cameraFragment.setVisibility(View.VISIBLE);
        });

    }


    ActivityResultLauncher<Intent> imageLaunch = registerForActivityResult( // for getting bitmaps from selected  images in file explorer.
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    Log.d("dataintent","null");
                    if (data!=null){
                        Log.d("data","null");
                        if (data.getClipData() != null) {
                            Log.d("dataclip","null");
                            Bitmap[] url=new Bitmap[data.getClipData().getItemCount()];
                            maskRecognised=new String[data.getClipData().getItemCount()];
                            timeElapsed=new String[data.getClipData().getItemCount()];
                            batteryLevel=new String[data.getClipData().getItemCount()];
                            for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                                Uri uri = data.getClipData().getItemAt(i).getUri();
                                try {
                                    System.out.println(uri.getPath());
                                    url[i]= MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            imageRender(url);
                        }else {
                            try {
                                maskRecognised=new String[1];
                                timeElapsed=new String[1];
                                batteryLevel=new String[1];
                                Uri uri = data.getData();
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                                Bitmap[] bitmaps=new Bitmap[]{bitmap};
                                imageRender(bitmaps);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }

                }
            });

    private void imageRender(Bitmap[] urls) { // loop through all selected images recognise and send them to the adapter.
        for (int i = 0; i <urls.length ; i++) {
            Log.d("test", "bitmap "+urls.length);
            recognise(urls[i],i);
        }
        binding.vpData.setAdapter(new ProcessedImageAdapter(urls,maskRecognised, timeElapsed, batteryLevel,getApplicationContext()));

    }

    public void recognise(Bitmap bitmap, int position){ // tensorflow apis for model recognition.
        try {
            Maskrecognise model = Maskrecognise.newInstance(context);

            // Creates inputs for reference.
            long initial_time=System.currentTimeMillis();
            TensorImage image = TensorImage.fromBitmap(bitmap); // the bit map is converted to tensor image.The tensor library
            //comes from tensor flow lite library.

            // Runs model inference and gets result.
            Maskrecognise.Outputs outputs = model.process(image); // The tensor image is getting converted to tensor buffer
            //which is recognised by the model to fetch the liable and probability.
            List<Category> probability = outputs.getProbabilityAsCategoryList();
            String data=getHighestProbability(probability);
            long time_elapsed=System.currentTimeMillis();
            // Releases model resources if no longer used.
            model.close();
            Log.d("data_response",data);
            timeElapsed[position]=String.valueOf(time_elapsed-initial_time);
            maskRecognised[position]= data.split(" ")[1];
            batteryLevel[position]=String.valueOf(battery);
        } catch (IOException e) {
            // TODO Handle the exception
            e.printStackTrace();
        }
    }

    String getHighestProbability(List<Category> probability){ // calculating the highest probability with linear search algorithm.
        float max0=probability.get(0).getScore();
        String max1=probability.get(0).getLabel();
        for (Category category:
                probability) {
            if (max0<category.getScore()){
                max0=category.getScore();
                max1=category.getLabel();
            }
        }
        return max1+" "+max0;
    }


    @Override
    public void onScanComplete(Bitmap bitmap) { // getting result from the camera and sending it to recognition.
        try {
            maskRecognised=new String[1];
            timeElapsed=new String[1];
            batteryLevel=new String[1];
            Bitmap[] bitmaps=new Bitmap[]{bitmap};
            imageRender(bitmaps);
            binding.cameraFragment.setVisibility(View.GONE);
        } catch (Exception e) {
            Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                    .show();
            Log.e("Camera", e.toString());
        }
    }

    private final BroadcastReceiver broadCastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context arg0, Intent intent) { // getting current battery status.
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            battery=(float) (level * 100 / scale);
        }
    };
}