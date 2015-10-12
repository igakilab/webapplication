# webapprication
サーバサイドのウェブアプリケーション

##LabMemberManager
Marshmallow-Warriorsの作っているゼミ在室管理システム

##Applidegozaru
サーバプログラムの起動方法  

1. tomcatを指定の場所にコピーする  
   (webappsのフォルダが、「C:\pleiades4.5\tomcat\7」の場所に置かれるように）
2. 「project」フォルダにある「LabMemberManager」をeclipseにインポートする。
3. プロジェクトにはいっているbuild.xmlを実行する(右クリック→実行→1 Antビルド)。
4. tomcatの「bin」フォルダのstartup.batを実行する。
5. http://localhost:8080/LabMemberManager/index.htmlにアクセスする。

注釈
- デモ用として下記の2つのページを用意しています。
  - http://localhost:8080/LabMemberManager/stateview.html
  - http://localhost:8080/LabMemberManager/idview.html
- テスト用として下記の3つのページを用意しています。
  - http://localhost:8080/LabMemberManager/test/lmmtest.html
  - http://localhost:8080/LabMemberManager/test/personalData.html
  - http://localhost:8080/LabMemberManager/test/records.html
- 各ページへのアクセスがうまくいかない（404エラー）などが出る場合は下記の方法を試して下さい。
  - リポジトリの「bats」フォルダにある「restart.bat」をtomcatの「bin」フォルダにコピーする。
  - コマンドプロンプトを開き、「> restart.bat LabMemberManager」を実行する。
