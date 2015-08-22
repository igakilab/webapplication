## MultiplePrinterの呼び出し方

JavaScriptの例を以下に示します。

```
MultiplePrinter.login(name, {
  callback: function(data){
    $("#loginReply").text(data);
  }
});
```

このコードはMultiplePrinterのloginメソッドを呼び出して、その返却値をHTMLのid名loginReplyの箇所に表示するコードです。  
MultiplePrinterはdwrによって、JavaScriptオブジェクトのように呼び出すことができます。  
callback: の後には、呼び出したメソッドが終了した際に実行される関数を指定し、その関数の引数で呼び出したメソッドの返却値を受け取ります。  

```
クラス名.メソッド名(引数, {
  callback: function(メソッドの返却値を受け取る変数名){
    呼び出したメソッドが終了した場合に行う処理
  }
});
```
