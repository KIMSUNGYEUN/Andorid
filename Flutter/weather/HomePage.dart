import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);
  @override
  _HomePageState createState() => _HomePageState();
}


class _HomePageState extends State<HomePage> {
 late bool gps;

  void permission()async{
    await Geolocator.requestPermission();
    var value = await Geolocator.checkPermission();
    setState(() {
      if(value == LocationPermission.always || value == LocationPermission.whileInUse) gps =true;
      else gps = false;
    });
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    permission();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      floatingActionButton: FloatingActionButton(
        onPressed: (){
          print(gps);
        },
      ),
    );
  }

}
