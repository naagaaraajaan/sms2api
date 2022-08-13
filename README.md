# sms2api

//Update the Public URL/API end point which accept the POST request. For intranet the end point should be able to reach from the mobile
val request = Request.Builder()
    .url("http://192.168.1.4:5000/login") // Replace the Actual URL to which SMS need to be forwarded
    .method("POST", body)
    .addHeader("Content-Type", "application/json")
    .build()

For testing i have used , Flask server , below are the setup method

Pre condition:
Python should be installed.

python -m venv vent

cd venv/Scripts
activate

pip install flask

copy app.py from Flask_server folder

then run 

python -m flask run

or

python -m flask run --host=192.168.1.4  // to run with specific IP

![image](https://user-images.githubusercontent.com/36821959/184478507-d3c173c3-9653-4001-804d-df34644c1b29.png)

Message received will be shown in the server like below

![image](https://user-images.githubusercontent.com/36821959/184494253-50e55b83-69c8-4c9c-a4ba-0e2074d7e084.png)

