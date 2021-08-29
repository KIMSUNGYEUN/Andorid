import 'package:flutter/material.dart';

class CandWidget extends StatefulWidget{
  VoidCallback? tap;
  String? text;
  int? index;
  double? width;
  bool? answerState;

  CandWidget({this.tap, this.index, this.width, this.text, this.answerState});
  _CandWidgetState createState() => _CandWidgetState();

}

class _CandWidgetState extends State<CandWidget>{
  @override
  Widget build(BuildContext context){
    return Container(
      width: widget.width *0.8,
      height: widget.width * 0.1,
      padding: EdgeInsets.fromLTRB(
        widget.width * 0.048,
        widget.width * 0.024,
        widget.width * 0.048, 
        widget.width * 0.048
        ),
        decoration: BoxDecoration(),
    );
  }
}
