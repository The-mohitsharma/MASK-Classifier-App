
# MASK-Classifier-App

This Repository Contains the Projects Developed in Andriod(java).
The primary purpose of this project is to take a tensor flow model from the teachable machine and trained it with different classes and interfaces and export all classes and download them as a TensorFlow model. 
 After exporting I trained a model with that exported dataset and convert it into a Tensor flow lite model and run it into the mobile application.

so here I will take a tensor flow model from [the Teachable machine platform] then convert it into a tf life model and download a model. tflite and import it into the android studio.



## Tech Stack

**LANGUAGE:** JAVA.

**LIBRARIES:** Tensorflow and Tensorflow lite

**Platform:** Andriod studio.



## FLOWCHART OF THE Implementation.

- ![Unknown](https://user-images.githubusercontent.com/85448730/208239432-a0206a08-59b3-4ba1-bfc6-44641e82dcb3.png)



## Acknowledgements and Installation

 **First** I initialised the package name.
package com.mohit.maskclassifier;


**Second**, I import the essential libraries Import libraries - It can include everything needed to build an app, including source code, resource files, and an Android manifest  like;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.mohit.maskclassifier.adapters.ProcessedImageAdapter;
import com.mohit.maskclassifier.databinding.ActivityMainBinding;
import com.mohit.maskclassifier.ml.Maskrecognise;
import com.mohit.maskclassifier.utils.ViewAnimation;



**Third** I import the tf lite library to support the (Tensor image & label category)


import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;


Here I define Activity binding to use for activity view binding it generates a binding class for each XML layout file in that module. An instance of a critical class contains direct references to all views with an ID in the corresponding layout.
Here I initiated a string for bird recognise; time elapsed; battery level for each recognised image.;


String[] maskRecognised; //  used for list of mask recognised.
String[] timeElapsed; //  used for  time elapsed.
String[] batteryLevel; // battery status for each recognised image.


![Picture 1](https://user-images.githubusercontent.com/85448730/208182882-64c4163d-66e5-4f86-b3dd-909a7e6aac51.png)



## DEPLOYMENT

**Broadcast Receivers can send or receive messages from other applications or from the system itself. These messages can be events or intents.**


![Picture 2](https://user-images.githubusercontent.com/85448730/208183134-b01b32c7-b861-4b16-b10b-9508af5467ea.png)


registerReceiver(broadCastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));  // for setting broadcast receiver for battery changes.

**View animation to initialised animation in floating action button for 
Camera and image and viewer;**



**ViewAnimation.init(binding.fabCamera); // initialised animation in floating action button for camera.
ViewAnimation.init(binding.fabImage);// initialised animation in floating action button for image.
ViewAnimation.init(binding.fabFileViewer);**


Creating a condition for checking;



**If 
{camera permission is allowed or not through the package manager if the camera permission is allowed then it will show in [Binding in image, camera and viewer]**

**Else 
{
It will show out in [Binding in the image, camera, viewer]**




**Here, I use  setOnClickListener;**
for binding in the image it means setonclicklistener will work 
Only opening file explorer for a particular image then it will launch the (intent).

**Here, I use  setOnClickListener;**
for binding in the camera for showing the camera fragment.



**Here, I use  setOnClickListener;**
for binding in the viewer for receiving a string and 
Put a for loop to recognise the length of the bird, the time elapsed, and battery level.



{
   receiveString = receiveString + "Mask Recognised: " + birdRecognised[i] + "\n";
   receiveString = receiveString + "Time Elapsed: " + timeElapsed[i] + "\n";
   receiveString = receiveString + "Battery Level: " + batteryLevel[i] + "\n";
}


  Here Activity will be launch means image launch and getting bitmaps 
From selected images in file explorer.


**Bitmap**
It is a class that represents a 2d coordinate system. The coordinate system moves to the right on the x-axis, and to the bottom on the y-axis. Each point in the coordinate system is called a pixel. A pixel is formed of bits, and bits represent the colour of this pixel. A pixel can be formed of 8, 16 or 24 bits ... The x-axis represents the width, and the y-axis represents the height.
**Why convert images into a bitmap?**



if the image size is too large, it could produce an out-of-memory error. And it is not efficient to load bitmaps larger than the Image View canvas, (unless the Image View supplies custom functionalities such as pinch-to-zoom).
When the image is too large, or you wish to control its size for better memory optimisation, it must be decoded first into a Bitmap with reasonable metrics:



**After this process image rendering will start for all the selected image will go through a loop too, Recognise the selected image and send them to the adapter.
private void imageRender(Bitmap[] urls)
for (int i = 0; i < urls.length; i++) {
   recognise(urls[i], i);
}



## Support And TensorFlow Trained Model

### Model.tflite;


<img width="978" alt="Screenshot 2022-12-17 at 4 43 47 PM" src="https://user-images.githubusercontent.com/85448730/208238997-c9905458-ff1f-45a6-9b1a-00ab1a6306ff.png">


## How Mask app is Taking care of Byte Buffer in the application.

I am using Tensor Image, tensor image in Java takes care of byte buffer class internally in lower and higher version.

2. Yes, i trained model using the sigmoid function in CNN model and quantified loss with cross entropy to compare and update the difference between actual and predicted value gradually decreasing the difference. After this I used metadata to tell the model what kind of input it would receive in android ( this is why I was able to use tensor image) while converting it to tflite. Then just included the model with the bird app Android code and it worked as tensorflow engineers use same algorithm.

## Optimizations

1. **Add TensorFlow Lite to the Android.**
Right-click on the package name in my case it is com. your package name or click on File, then New > Other > TensorFlow Lite Model. Select the model location where you have downloaded the custom-trained BirdsModel.flite earlier
Note that the tooling will automatically configure the module’s dependencies for you using ML Model binding and all requirements will be added to your Android module’s build. Gradle file.



 **In the end, you’ll see the following. The BirdModel.flite file has been successfully imported, and it displays high-level model information like input and output and some sample code to get you started.
After this, I used TensorFlow APIs for trained model recognition.
**Create inputs for references;



Maskrecognise model = Maskrecognise.newInstance(context);

**// Creates inputs for reference.**
long initial_time=System.currentTimeMillis();
TensorImage image = TensorImage.fromBitmap(bitmap); // the bit map is converted to tensor image.The tensor library
//comes from tensor flow lite library.





**// Runs model inference and gets result.**
Maskrecognise.Outputs outputs = model.process(image); // The tensor image is getting converted to tensor buffer
//which is recognised by the model to fetch the liable and probability.
List<Category> probability = outputs.getProbabilityAsCategoryList();
String data=getHighestProbability(probability);
long time_elapsed=System.currentTimeMillis();



Releases the trained model resources if it is no longer used.
**// Releases model resources if no longer used.
model.close();
Log.d("data_response", data);
   


Here I use the linear search algorithm to calculate the highest 
Probability with that algorithm, which means we find it will be in floating decimal points or it will be in a string type.
 String getHighestProbability(List<Category> probability) { // calculating the highest probability with linear search algorithm.
   float max0 = probability.get(0).getScore();
   String max1 = probability.get(0).getLabel();
   
   

Then we override the process and get a result from the 
Camera and send it to recognition.
Using try and catch the exception.

{
   Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
           .show();
   Log.e("Camera", e.toString());
}
   
   

Here I find the current battery level status while processing the multiple images for recognition in one go.
{ // getting current battery status.
   int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
   int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
   battery = (float) (level * 100 / scale);
}
   
   
   

Here I saving all the file with array data as txt formate using try and catch io exception.
This means when I will take multiple images from file explorer and send them to the adapter to recognise all the selected images then it will execute first all the images according to their time elapsed and then converting into the .txt file.

   
   
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


                                                 

After arranging all the images according to their time elapsed then it will show a data to be printed in txt file.

final View DialogView = factory.inflate(R.layout.file_opner, null);
final AlertDialog DialogViewDialog = new AlertDialog.Builder(context).create();



## OUTPUT

![WhatsApp Image 2022-12-17 at 4 19 52 PM](https://user-images.githubusercontent.com/85448730/208239222-106ab653-4508-4214-89f4-2b0c33a6e17c.jpeg)
![WhatsApp Image 2022-12-17 at 4 19 53 PM](https://user-images.githubusercontent.com/85448730/208239224-ab3d74a0-7ebc-4ce0-b33a-82c93b1c16a9.jpeg)
![WhatsApp Image 2022-12-17 at 4 19 52 PM (1)](https://user-images.githubusercontent.com/85448730/208239227-e80df5ca-a4d4-46e8-a93a-5ede2cdf817f.jpeg)
![WhatsApp Image 2022-12-17 at 4 19 53 PM (1)](https://user-images.githubusercontent.com/85448730/208239230-573d3472-dc35-46b3-b1b2-5f25653bb6c7.jpeg)
![WhatsApp Image 2022-12-17 at 4 19 54 PM (1)](https://user-images.githubusercontent.com/85448730/208239232-2cf3a348-0cfc-4175-bb7b-794d5fb05ba4.jpeg)
![WhatsApp Image 2022-12-17 at 4 24 30 PM](https://user-images.githubusercontent.com/85448730/208239233-2fa59c57-d362-4691-a13f-6fbec09321ba.jpeg)
![WhatsApp Image 2022-12-17 at 4 19 54 PM](https://user-images.githubusercontent.com/85448730/208239234-d38cb01d-65d0-44b7-a913-9fee0904d4c0.jpeg)
![WhatsApp Image 2022-12-17 at 4 19 55 PM](https://user-images.githubusercontent.com/85448730/208239235-134d34ba-4c6d-40b6-a88b-44b5cf87a5d3.jpeg)
![WhatsApp Image 2022-12-17 at 4 24 31 PM](https://user-images.githubusercontent.com/85448730/208239238-bdbefed8-f69f-4676-8ce1-7dbf140fd08f.jpeg)

