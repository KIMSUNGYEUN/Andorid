import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
//import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_core/firebase_core.dart';

void main() async{
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: HomePage()
    );
  }
}

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    Widget liveData = StreamBuilder(
      stream: FirebaseFirestore.instance
          .collection('test')
          .doc('test2')
          .snapshots(),
      builder: (context, snapshot){
        if(!snapshot.hasData) return LinearProgressIndicator();
        return Text((snapshot.data as dynamic)['name']);
      },
    );
//(RegExp(r"['](.*?)[']").stringMatch(function.toString()))는 정규 표현식
    //1.데이터에 저장된 정보들을 map 형태로 하나씩 꺼내옴
    //2.꺼내온 데이터는 function 에 저장, 꺼내온 데이터를 정규표현식을 거쳐 실제 이름을 가지고 있는 부분만 뽑아냄
    //3.뽑아낸 데이터는 싱글 따음표를 가지고 있어 replaceAll 로 싱글 따음표를 아얘 없는 데이터로 변경
    //4.function call을 이용하여 function에 있는 함수 실행
    //5.onPressed를 통해 RaisedButton을 실행하여 각각 다른 버튼을 만듬
    //6. toList로 맵 형태로 뽑아온 데이터를 정상적으로 변경해줌
    Widget getData = Center(
      child: Column(
          children: data.map((function) => RaisedButton(
              child: Text((RegExp(r"['](.*?)[']").stringMatch(function.toString())as dynamic).replaceAll ("'", "")),
              onPressed: () => function.call()
          )).toList()
      ),
    );

    return Scaffold(
      appBar: AppBar(
        title: Text('firebase'),
      ),
      body: getData
    );
  }

  List<Function> data = [documentGets, documentSets1, documentSets2, documentRemoves, fieldGets, fieldAdds, fieldRemoves];

  //Document 불러오기
  static documentGets(){
    FirebaseFirestore.instance
        .collection('test')
        .get()
        .then((value){
          for(DocumentSnapshot doc in value.docs){
            print(doc.id);
          }
          print('Done documentGets');
    });
  }
//document 생성하기1
 static documentSets1(){
    FirebaseFirestore.instance
        .collection('test')
        .doc('test3')
        .set({
          'type': 'user info'
        })
        .then((value){
          print('Done documentSets1');
        });
  }
//document 생성하기2
  static documentSets2(){
    FirebaseFirestore.instance
        .collection('test')
        .add({'type': 'user info'})
        .then((value){
      print('Done documentSets2');
    });
  }
//document 삭제
  static documentRemoves(){
    FirebaseFirestore.instance
        .collection('test')
        .doc('test1')
        .delete()
        .then((value){
          print('Done documentRemoves');
    });
  }

//필드 정보 읽어옴
  static fieldGets(){
    FirebaseFirestore.instance
        .collection('test')
        .doc('test2')
        .get()
        .then((value){
          print(value.data()? ['name']);
    });
  }
//필드 추가
  static fieldAdds(){
    FirebaseFirestore.instance
        .collection('test')
        .doc('test2')
        .update({
      'type': 'user info',
    }).then((_){ // value == _ : 사용하지 않는 변수임을 알려줌
      print('Done fieldAdds');
    });
  }
//필드 삭제
  static fieldRemoves(){
    FirebaseFirestore.instance
        .collection('test')
        .doc('test2')
        .update({
      'type': FieldValue.delete()
    }).then((_){
      print('Done fieldRemoves');
    });
  }
}
