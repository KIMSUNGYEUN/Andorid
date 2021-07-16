import 'package:flutter/material.dart';
import 'dart:async';
void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'StopWatch',
      theme: ThemeData(

        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: HomePage(),
    );
  }
}

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  List<String> lapTimes = []; //랩 타임을 기록할 변수
  Stopwatch watch = Stopwatch(); // 지속적으로 시간이 지나가는 변수
  String elapsedTime = '00:00:00:000'; // 경과시간을 기록하는 변수
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('StopWatch'),
        centerTitle: true,
      ),
      body: Center(
        child: Column(
          children: [
            Container(
              child: Text(
                  '00:00:00:0000',
                style: TextStyle(
                  fontSize: 25
                ),
              ),
              padding: EdgeInsets.all(20),
            ),
            Container(
              width: 100,
              height: 200,
              child: ListView(
                children: [
                  Text('Lab3'),
                  Text('01:00:00:0000'),
                  Text('Lab2'),
                  Text('01:00:00:0000'),
                  Text('Lab1'),
                  Text('01:00:00:0000'),
                ],
              ),
            ),
            Container(
              width: 200,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  FloatingActionButton(
                    onPressed: () {  },
                    child: !watch.isRunning ? Icon(Icons.play_arrow) : Icon(Icons.stop)
                  ),
                  FloatingActionButton(
                    onPressed: () {  },
                    child: !watch.isRunning ? Text('Reset') : Text('Lab')
                  ),
                ],
              ),
            )
          ],
        ),
      ),
    );
  }


  transTime(int milliseconds){
    int seconds = (milliseconds / 1000).truncate();
    int minutes = (seconds / 60).truncate();
    int hours = (minutes / 60).truncate();


    String secondsStr = (seconds % 60).toString().padLeft(2, '0');
    String minutesStr = (minutes % 60).toString().padLeft(2, '0');
    String hoursStr = (hours % 60).toString().padLeft(2, '0');

    return "$hoursStr:$minutesStr:$secondsStr:$milliseconds";
  }
  
  updateTime(Timer timer){
    if(watch.isRunning){
      setState(() {
        elapsedTime = transTime(watch.elapsedMilliseconds);
      });
    }
  }
}
