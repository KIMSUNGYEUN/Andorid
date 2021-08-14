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
          .doc('test1')
          .snapshots(),
      builder: (context, snapshot){
        if(!snapshot.hasData) return LinearProgressIndicator();
        return Text((snapshot.data as dynamic)['name']);
      },
    );

    return Scaffold(
      appBar: AppBar(
        title: Text('firebase'),
      ),
      body: Center(
        child: Column(
          children: [
            RaisedButton(
              child: Text('documentGets'),
              onPressed: (){
                documentGets();
              },
            )
          ],
        ),
      )
    );
  }

  documentGets(){
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
}
