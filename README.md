
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
![carbon](https://user-images.githubusercontent.com/85448730/208246944-65784c92-5193-44b4-8f09-5e35f92a8397.png)


**Second**, I import the essential libraries Import libraries - It can include everything needed to build an app, including source code, resource files, and an Android manifest  like;

![carbon-1](https://user-images.githubusercontent.com/85448730/208246947-4ab5b4da-a8a7-48e5-82ee-dbb1208da826.png)



**Third** I import the tf lite library to support the (Tensor image & label category)


![carbon-1 2](https://user-images.githubusercontent.com/85448730/208246950-519f138e-5c89-49b5-9818-6b865df1434c.png)


Here I define Activity binding to use for activity view binding it generates a binding class for each XML layout file in that module. An instance of a critical class contains direct references to all views with an ID in the corresponding layout.
Here I initiated a string for bird recognise; time elapsed; battery level for each recognised image.;


![carbon-1 3](https://user-images.githubusercontent.com/85448730/208246951-77bb8a30-0faa-43b6-bf64-59ecc8bad569.png)


![Picture 1](https://user-images.githubusercontent.com/85448730/208182882-64c4163d-66e5-4f86-b3dd-909a7e6aac51.png)



## DEPLOYMENT

**Broadcast Receivers can send or receive messages from other applications or from the system itself. These messages can be events or intents.**


![Picture 2](https://user-images.githubusercontent.com/85448730/208183134-b01b32c7-b861-4b16-b10b-9508af5467ea.png)


registerReceiver(broadCastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));  // for setting broadcast receiver for battery changes.

**View animation to initialised animation in floating action button for 
Camera and image and viewer;**



![carbon-1 4](https://user-images.githubusercontent.com/85448730/208246952-cc451f26-2070-450a-95cf-c36b99da6232.png)


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



![carbon-1 5](https://user-images.githubusercontent.com/85448730/208246954-3d20f727-5a4d-4131-a953-967fbf3bd122.png)


  Here Activity will be launch means image launch and getting bitmaps 
From selected images in file explorer.


**Bitmap**
It is a class that represents a 2d coordinate system. The coordinate system moves to the right on the x-axis, and to the bottom on the y-axis. Each point in the coordinate system is called a pixel. A pixel is formed of bits, and bits represent the colour of this pixel. A pixel can be formed of 8, 16 or 24 bits ... The x-axis represents the width, and the y-axis represents the height.
**Why convert images into a bitmap?**



if the image size is too large, it could produce an out-of-memory error. And it is not efficient to load bitmaps larger than the Image View canvas, (unless the Image View supplies custom functionalities such as pinch-to-zoom).
When the image is too large, or you wish to control its size for better memory optimisation, it must be decoded first into a Bitmap with reasonable metrics:



**After this process image rendering will start for all the selected image will go through a loop too, Recognise the selected image and send them to the adapter.
![carbon-1 6](https://user-images.githubusercontent.com/85448730/208246955-37cee800-0a12-4ae7-bb80-7c10e9610420.png)


## Support And TensorFlow Trained Model

### Model.tflite;


<img width="978" alt="Screenshot 2022-12-17 at 4 43 47 PM" src="https://user-images.githubusercontent.com/85448730/208238997-c9905458-ff1f-45a6-9b1a-00ab1a6306ff.png">


## How Mask app is Taking care of Byte Buffer in the application.

I am using Tensor Image, tensor image in Java takes care of byte buffer class internally in lower and higher version.

2. Yes, i trained model using the sigmoid function in CNN model and quantified loss with cross entropy to compare and update the difference between actual and predicted value gradually decreasing the difference. After this I used metadata to tell the model what kind of input it would receive in android ( this is why I was able to use tensor image) while converting it to tflite. Then just included the model with the bird app Android code and it worked as tensorflow engineers use same algorithm.



## Model Traning part and Convert into TensorFlow Lite Model.

-<img width="1439" alt="Screenshot 2022-12-17 at 5 11 42 PM" src="https://user-images.githubusercontent.com/85448730/208240141-7d70a582-6a7f-4aa2-ab65-98b161486433.png">
<img width="1439" alt="Screenshot 2022-12-17 at 5 12 17 PM" src="https://user-images.githubusercontent.com/85448730/208240145-844825ac-e501-40e0-9d47-ed5f2afd28d1.png">
<img width="1439" alt="Screenshot 2022-12-17 at 5 12 29 PM" src="https://user-images.githubusercontent.com/85448730/208240148-3154db1e-175e-47b6-b9db-c2b6df3c7e5c.png">




## Optimizations

1. **Add TensorFlow Lite to the Android.**
Right-click on the package name in my case it is com. your package name or click on File, then New > Other > TensorFlow Lite Model. Select the model location where you have downloaded the custom-trained BirdsModel.flite earlier
Note that the tooling will automatically configure the module’s dependencies for you using ML Model binding and all requirements will be added to your Android module’s build. Gradle file.



 **In the end, you’ll see the following. The BirdModel.flite file has been successfully imported, and it displays high-level model information like input and output and some sample code to get you started.
After this, I used TensorFlow APIs for trained model recognition.

**Create inputs for references;



![carbon-1 7](https://user-images.githubusercontent.com/85448730/208246968-2e52bc34-24b9-44e8-8172-c1dcbaf7a6c3.png)





![carbon-1 8](https://user-images.githubusercontent.com/85448730/208246971-f6282b86-112b-4d59-9d80-842a7af9e943.png)


![carbon-1 9](https://user-images.githubusercontent.com/85448730/208246975-7e0fc39f-a75a-484b-9075-3c642750d85f.png)
   


Here I use the linear search algorithm to calculate the highest 
Probability with that algorithm, which means we find it will be in floating decimal points or it will be in a string type.
 String getHighestProbability(List<Category> probability) { // calculating the highest probability with linear search algorithm.
   ![carbon-1 10](https://user-images.githubusercontent.com/85448730/208246976-b7776195-bb09-47e2-9e93-065b1b03dc6f.png)
   
   

Then we override the process and get a result from the 
Camera and send it to recognition.
Using try and catch the exception.

{
   Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
           .show();
   Log.e("Camera", e.toString());
}
   
   

Here I find the current battery level status while processing the multiple images for recognition in one go.
![carbon-1 11](https://user-images.githubusercontent.com/85448730/208246978-a40b9a4e-2d46-4307-b104-6989a1a11287.png)
   
   
   

Here I saving all the file with array data as txt formate using try and catch io exception.
This means when I will take multiple images from file explorer and send them to the adapter to recognise all the selected images then it will execute first all the images according to their time elapsed and then converting into the .txt file.

   
   
![carbon-1 12](https://user-images.githubusercontent.com/85448730/208246981-4d1b971d-e03f-4d93-881e-5d921050723b.png)


                                                 

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

