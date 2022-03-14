# Andorid

## 안드로이드 개념적인 부분
- ### 안드로이드 4대 컴포넌트
![](https://images.velog.io/images/baduckie6231/post/a027a54e-0fc4-496a-8173-d9be74645ea7/image.png)
  - 액티비티(Activity)
  
  - 서비스(Service)
  
  - 브로드캐스트 리시버(BroadCast Receiver)
  
  - 콘텐트 프로바이더(Content Provider)

  각각의 컴포넌트는 인텐트를 통해 상호작용

- ### Context란?
![](https://images.velog.io/images/baduckie6231/post/5ba41cea-0963-4f81-8bd3-8c6cb8c16695/image.png)
  - 애플리케이션(객체)의 현재 상태의 맥락을 의미함
  - 컨텍스트는 새로 생성된 객체가 지금 어떤 일이 일어나고 있는지 알 수 있도록 함
  - 액티비티와 애플리케이션에 대한 정보를 얻기 위해 사용함
  - Context에는 애플리케이션 context와 액티비티 context가 존재함

 - #### 애플리케이션 컨텍스트(Application Context)란?
    - 싱글턴 인스턴스이며 액티비티에서 getApplicationContext()를 통해 접근 가능함
   - 애플리케이션 라이프 사이클과 연결되어 있음
   - 현재의 컨텍스트와 분리된 라이프사이클을 가진 컨텍스트가 필요하거나 액티비티의 범위를 넘어서  컨텍스트를 전달할 때 사용

- #### 액티비티 컨텍스트(Activity Context)란?
  - 액티비티에서 사용 가능
  - 액티비티의 라이프사이클과 연결되어 있음
  - 액티비티의 범위 내에서 컨텍스트를 전달 또는 라이프사이클이 현재의 컨텍스트에 붙은 컨텍스트가 필요할 때 사용

- ### 생명주기란?
![](https://images.velog.io/images/baduckie6231/post/9d6e4ddb-2f2c-4c8f-962f-286805213000/image.png)
상태 변화가 있을 때마다 화면에 보이는 액티비티의 생명 주기 메서드를 호출해서 상태 변화를 알려줌

> onCreate : Activity가 생성할 때 실행되는 것으로, 필수적으로 필요한 콜백. Activity가 생성되면 동작함. 안드로이드 전체 생명 주기에서 단 한 번만 실행

> onStart : onCreate(), onRestart() 이후에 Activity가 시작되면 이 콜백을 호출. 프로그램의 ForeGround와 상호작용 가능. 사용자에게 보이기 직전에 실행되는 콜백

> onResume : Activity가 재개된 상태에 들어가면 ForeGround에 표시, onResume() 콜백을 호출함. 어떤 이벤트가 발생하여 앱에서 포커스가 떠날 때까지 이 상태에 머무름. 
포커스가 떠나는 상태라면 (전화가 오거나, 사용자가 다른 활동으로 이동하는 등) Resume() 콜백을 호출함. 포커스가 떠난 후 다시 돌아오면 onResume() 콜백을 다시 호출함


> onPause : Activity가 어떤 이벤트가 발생하여 앱에서 포커스가 떠날 때 콜백을 호출함
onResume()과 onPause()는 한 묶음으로 보면 됨

> onStop :  Activity가 더 이상 사용자에게 표시가 되지 않으면 중단상태로 들어감. 그때 onStop() 콜백을 호출함
예를 들어서 새로 시작된 앱이 화면 전체를 차지하는 경우에 onStop() 콜백을 호출함.
주로 리소스를 해제하거나 조정해야 할 때 주로 사용됨
onStop() 상태로 들어갔다가 활동이 다시 시작되면 onRestart() 콜백을 호출해줌

> onDestroy : Activity가 소멸할 때 불러오는 콜백임. onDestory()는 생명주기가 종료된다고 보면 됨.
어지간하면 쓸 일이 없는 콜백 함수라고 보면 됨

- ### 의존성 주입이란?
  - 의존관계를 외부에서 결정하고 주입하는 것

- ### 의존성 주입을 사용하는 이유
  - Unit Test 용이
  - 코드의 재활용성을 높여줌
  - 객체간의 의존성을 줄이거나 없엘 수 있음
  - 객체 간의 결합도가 낮아지고 유연한 코드 작성 가능

- ### XML이란?
  - AndroidManifest.xml, Layout 파일, Resource 파일 등 다양한 곳에서 사용
  - XML을 이용해 데이터를 쉽게 표현
  - Android OS가 XML을 파싱하여 데이터를 구조화함

- ### Activity vs Fragment 비교
![](https://images.velog.io/images/baduckie6231/post/110fba8c-42d9-49d2-80b8-a21896c17762/image.png)
  - 공통점
둘 다 View를 표현해줌

  - 차이점
Activity : 가장 바닥 역할을 함
Fragment : 액티비티에 붙음

- ### Proguard란?
  - 코드 난독화 툴

- ### 리플렉션이란?
  - 런타임에 프로그램의 클래스를 조사하기 위해서 사용되는 기술

- ### 컴파일 타임, 런 타임 이란?
  - 컴파일 타임 : 작성된 소스코드를 기계어코드로 변환 되어 실행가능한 프로그램으로 만드는 과정
  - 런 타임 :  컴파일된 프로그램이 사용자에 의해 실행되어 동작할 때

- ### 컴파일, 빌드 란?
  - 컴파일은 작성된 소스코드를 기계어코드로 변환
  - 빌드는 컴파일한 후 실행파일로 만드는 과정
