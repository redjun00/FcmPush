# push-service

####개요
fcm을 사용하여 app 실행 시 token을 받아와서 서버에 저장을 요청하고, push 메시지가 오면 확인 할 수 있는 app.

####도구
minSdkVersion 14
gradle - 2.4<br/>
firebase-messaging:9.6.1<br/>
okhttp:3.2.0

####url 요청
#####fcm으로 push 메시지 요청
curl --header "Content-Type:application/json" \
 --header "Authorization:key=AAAAmSwlYYY:APA91bEQnHqo4fm94ThHXYdOAYODffDQ0YARyjosoYUeUmBI7VFRz1In_2GBlmRFwSM7KRmFtNmzfur4rCv87htNALvvVieiEehdU_X0bNvo-ShHRghi6ghazlgDFJncNo4uYPtUdDUD" \
 --request POST \
 --data '{"to":"/topics/news", "data":{"message":"hello"}}' \
 https://fcm.googleapis.com/fcm/send
