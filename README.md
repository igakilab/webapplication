##概要
このページでは在室管理システムのサーバプログラムを動作させるまでの手順を示します。
在室管理システムでは、WebサーバのWebサービスにより提供されます。
セットアップの手順は以下のようになります。

1. pleiadesのダウンロードと配置
1. コンピュータの環境設定
1. eclipseへのプロジェクトのインポート
1. ビルドとアプリケーションの実行

また、このプロジェクトに必要なソフトウェアとバージョンを以下に示します。
- pleiades 4.5 Java Full Edition
- tomcat version 7

###pleiadesのダウンロードと配置

#### pleiadesのダウンロード

1. pleiadesのホームページ(<http://mergedoc.osdn.jp/>)にアクセスします。
1. Pleiades All in Oneのダウンロードの中から「Eclipse 4.5 Mars Pleiades All in One」を選択します。
1. 「Java」の列にあるパッケージの中から、動作させるOSに適した「Full Edition」パッケージをダウンロードします。

#### pleiadesの配置

1. ダウンロードしたzipファイルから「pleiades」フォルダをCドライブの直下へ展開します。
1. 「pleiades」フォルダの名前を「pleiades4.5」に変更します。

※変更後のディレクトリ構造は以下のようになります。
```
C:\ -- pleiades4.5 -- eclipse -- ...
                   |- java    -- 6
                   |          |- 7 ...
                   |- tomcat  -- 6
                   |          |- 7 ...
                   | ...
```

### コンピュータの環境設定

1. コンピュータアイコン(PC, マイコンピュータなど)を右クリックして、「プロパティ」→「システムの詳細設定」を選択する。  
   ※「コントロールパネル」→「システム」でもOK
1. 「環境変数」をクリックし、新たな環境変数として下記の2つを追加する
```
変数名: JAVA_HOME  変数値: C:\pleiades4.5\java\8
変数名: JRE_HOME   変数値: C:\pleiades4.5\java\8\jre
```

### eclipseへのプロジェクトのインポート

1. githubからwebapplicationのリポジトリをダウンロード（クローン等）する。  
   ※webapplicationリポジトリの「Projects」のフォルダをダウンロードするだけでも良い。
1. eclipseを起動する。  
   (C:\pleiades4.5\eclipse\eclipse.exeを実行)
1. 「ファイル」→「インポート」の順に選択する。
1. インポート・ソースの選択のリストから「既存プロジェクトをワークスペースへ」を選択し、「次へ」を選択する。
1. ルート・ディレクトリーの選択の「参照」をクリックし、webapplictionリポジトリの「Projects」フォルダを選択する。
1. プロジェクト一覧より「LabMemberManager」プロジェクトのみにチェックを入れ、「完了」をクリックする。

### raspicamのセットアップ  

1. gooogledrive内の研究室マニュアルと http://www.akakagemaru.info/port/windows10-ipaddress.html を参考に、tomcatを動作させるマシンのIPアドレスを固定する。  
1. https://github.com/igakilab/raspicam のREADME.md通りにraspberry piを設定し、raspicamをセットアップする。  

### ビルドとアプリケーションの実行

1. eclipseのパッケージエクスプローラより、「LabMemberManager」プロジェクト直下の「build.xml」を右クリックする。
1. 「実行」→「Antビルド」の順に選択する。（Antビルドが2つある場合は上を選択する）
1. 「C:\pleiades4.5\tomcat\7\start.bat」を実行する。
1. Webブラウザを開き「<http://localhost:8080/LabMemberManager/>」にアクセスする。
