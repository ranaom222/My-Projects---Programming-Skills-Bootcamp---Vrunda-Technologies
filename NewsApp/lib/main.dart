import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
      ),
      home: const NewsFeed(),
    );
  }
}

class NewsFeed extends StatefulWidget{
  const NewsFeed({super.key});

  @override
  _NewsListPageState createState() => _NewsListPageState();
}

class _NewsListPageState extends State<NewsFeed>{
  final String apiUrl = "https://newsapi.org/v2/top-headlines?country=in&apiKey=API_KEY_HERE";
  List<dynamic> _articles = [];

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    fetchArticles();
  }

  void fetchArticles() async {
    var result = await http.get(Uri.parse(apiUrl)); // api call for fetching the news

    // changing the state of the private articles list
    setState(() {
      _articles = jsonDecode(result.body)['articles'];
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Ishan's News App"),
      ),
      body: RefreshIndicator(
        onRefresh: () async {
          await Future.delayed(const Duration(seconds:1));
        },
        child: ListView.builder(
          itemCount: _articles.length,
          itemBuilder: (context, index) {
            return InkWell(
              onTap: () {
                if(_articles != null){
                  Navigator.push(context, MaterialPageRoute(builder: (context) => NewsDetailPage(article: _articles[index])));
                }
              },
              child: Card(
                child: Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Image.network(
                          _articles[index]['urlToImage'] ?? "https://storage.googleapis.com/cms-storage-bucket/6e19fee6b47b36ca613f.png",
                          height: 200,
                          width: double.infinity,
                          fit: BoxFit.cover
                      ),
                      const SizedBox(height:10),
                      Text(
                        _articles[index]['title'].toString(),
                        style: const TextStyle(
                            fontSize: 20,
                            fontWeight: FontWeight.bold
                        ),
                      ),
                      const SizedBox(height: 10),
                      Text(
                        _articles[index]['description'].toString(),
                        maxLines: 3,
                        overflow: TextOverflow.ellipsis,
                      )
                    ],
                  ),
                ),
              ),
            );
          },
        ),
      ),
    );
  }
}

class NewsDetailPage extends StatelessWidget{
  final dynamic article;

  // constructor to set the title of the news blog
  NewsDetailPage({super.key, required this.article});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(article['title'].toString()),
      ),
      body:  Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Image.network(
            article['urlToImage'] ?? "https://storage.googleapis.com/cms-storage-bucket/6e19fee6b47b36ca613f.png",
            height: 200,
            width: double.infinity,
            fit: BoxFit.cover,
          ),
          const SizedBox(height:10),
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: Text(
              article['title'].toString(),
              style: const TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.bold,
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: Text(
              article['description'].toString(),
              style: const TextStyle(
                fontSize: 18,
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: Text(
              article['content'].toString(),
              style: const TextStyle(
                fontSize: 18,
              ),
            ),
          ),
        ],
      ),
    );
  }
}



