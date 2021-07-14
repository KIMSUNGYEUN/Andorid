import 'package:flutter/material.dart';

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
      home: ContainerWidget()
    );
  }
}

class ContainerWidget extends StatelessWidget {
  const ContainerWidget({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Flutter example'),
      ),
      body: ListView(
        children: [
          ListTile(
            title: Text('Title'),
            subtitle: Text('Flutter is very easy fragment'),
            trailing: Icon(Icons.watch),
          ),
          ListTile(
            title: Text('Title'),
            subtitle: Text('Flutter is very easy fragment'),
            trailing: Icon(Icons.watch),
          ),
          ListTile(
            title: Text('Title'),
            subtitle: Text('Flutter is very easy fragment'),
            trailing: Icon(Icons.watch),
            onTap: (){
              print('tap');
            },
          ),
          ListTile(
            title: Text('Title'),
            subtitle: Text('Flutter is very easy fragment'),
            trailing: Icon(Icons.watch),
          )
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: (){
          print('123');
        },
        child: Icon(Icons.stop),
      ),
    );
  }
}
