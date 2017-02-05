# mercury-web-parser-java

[ ![Download](https://api.bintray.com/packages/jalaria/generic/mercury-web-parser-java/images/download.svg) ](https://bintray.com/jalaria/generic/mercury-web-parser-java/_latestVersion)

A java Mercury Web Parser api using retrofit and okhttp.

More info <a href="https://mercury.postlight.com/web-parser"/>https://mercury.postlight.com/web-parser/</a>


- How to use it

Add this line to your buildscript repositories section: *(Don't know why I can't add to jcenter)
```gradle
buildscript {
    repositories {        
        maven {url  "http://jalaria.bintray.com/generic"}
    }
}
```

Then on dependencies add:
```gradle
compile 'com.github.jalaria001:mercury-web-parser-java:1.0.0'
```

Using this library is so simple:
```java
Mercury mercury = new Mercury("YOUR MERCURY API KEY");
Response<Article> response = mercury.parseService().getArticle("URL").execute();
if(response.isSuccessful()){
  Article article = response.body();
  ...
}
...
```
