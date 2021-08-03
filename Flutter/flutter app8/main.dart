import 'package:flutter/material.dart';

import 'first.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
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

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('HomePage'),
      ),
      body: Center(
        child: Column(
          children: [
            RaisedButton(
              child: Text('FirstPage'),
              onPressed: (){
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => FirstPage())
                );
              },
            ),
          ],
          
        ),
      ),
    );
  }
}
