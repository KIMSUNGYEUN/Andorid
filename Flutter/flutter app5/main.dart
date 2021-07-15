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
      home: Layout()
    );
  }
}

class Layout extends StatelessWidget {
  const Layout({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Widget imageSection = Image.network(
      'https://lh3.googleusercontent.com/-gjFXIRp-J28/YO5z_JkxifI/AAAAAAAABdg/wMB450q3F6EBsKQ943Cg-YG4U2ygjFIugCLcBGAsYHQ/image.png',
      height: 240,
      width: 600,
      fit: BoxFit.cover,
    );

    Widget titleSection = Container(
      padding: EdgeInsets.all(32),
      child: Row(
        children: [
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text('Oeschinen Lake Campground',
                  style: TextStyle(
                      fontWeight: FontWeight.bold
                  ),
                ),
                Text('Kandersteg, Switzerland',
                  style: TextStyle(
                      color: Colors.grey[500]
                  ),
                ),
              ],
            ),
          ),
          Icon(Icons.star,
            color: Colors.red,
          ),
          Text('41'),
        ],
      ),
    );

    Widget buttonSection = Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: [
        Column(
          children: [
            Icon(Icons.call, color: Colors.blue,),
            Text('CALL', style: TextStyle(color: Colors.blue),)
          ],
        ),
        Column(
          children: [
            Icon(Icons.near_me, color: Colors.blue,),
            Text('ROUTE', style: TextStyle(color: Colors.blue),)
          ],
        ),
        Column(
          children: [
            Icon(Icons.share, color: Colors.blue,),
            Text('SHARE', style: TextStyle(color: Colors.blue),)
          ],
        ),
      ],
    );

    Widget textSection = Container(
      padding: EdgeInsets.all(32),
      child: Text('This cat cafe has a variety of cats, you can actually touch and feed them, and the cats in the store are very docile and arrogant.'
          'And the food and drinks in this cafe are very tasty, so it is a very crowded shop.',),
    );

    return Scaffold(
      appBar:  AppBar(
        title: Text('Flutter example'),
      ),
      body: ListView(
        children: [
          imageSection,
          titleSection,
          buttonSection,
          textSection
        ],
      ),
    );
  }
}
