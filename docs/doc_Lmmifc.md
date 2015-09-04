# Lmmインタフェースの使い方

###インクルードの方法

LmmIfcとLmmutilのメソッドを使用できるようにするには、利用するHTML文書のhead部分に以下のタグを挿入する必要があります。

`<script type="text/javascript" src="js/Lmmifc.js"></script>`  
`<script type="text/javascript" src="js/Lmmutil.js"></script>`

上記のコードはLmmifc.jsとLmmutil.jsがjsフォルダに入っている場合で、HTML文書の保存場所にあわせて変更する必要があります。


###返却されるオブジェクトについて

LabMemberManagerへメンバーの情報や記録を問い合わせると、MemberInfoBean型などのオブジェクトにデータが格納されて返却されます。

- MemberInfoBean  
  メンバーの名前や状態の情報を格納したオブジェクトです。  
  プロパティの一覧  
  - name(メンバーの名前, 文字列)
  - stateCode(状態コード, 整数)
  - stateStr(状態名, 文字列)
  - updateDate(最終更新日時, 文字列)

- ActionRecordBean  
  1回の記録(アクションレコード)の情報を格納したオブジェクトです。
  プロパティの一覧  
  - timeStamp(記録日時, 文字列)
  - name(メンバーの名前, 文字列)
  - stateCode(状態コード, 整数)
  - stateStr(状態名, 文字列)


###コールバック関数について

LabMemberManagerとの通信は非同期で行われるため、取得などの操作が終わるまでjavascript等の処理を中断することはできません。このため、返却値を直接受け取るのではなく、操作が終了したあとに呼び出されるコールバック関数により返却値を受け取る必要があります。コールバック関数は例を下記に示します。

```
function func(reply){
	//ここに処理を記述
}
```

操作の返却値はコールバック関数の第一引数に返却されます。これにより、操作の返却値を受け取ることができます。Lmmifc.loginメソッドを利用して、メンバーの名前"sample"のログイン情報を登録し、そのメソッドが返す返却値を画面にアラートとして表示するコードを、例として下記に示します。

```
Lmmifc.login("sample", function(reply){
	alert(reply);
});
```

Lmmifc.loginメソッドについては下記のメソッドを説明を参照して下さい。


###メソッドの説明

####Lmmifc

- Lmmifc.login(name, cbf);  
  [name: メンバー名, cbf: コールバック関数]  
  nameに指定されたメンバー名がログインしたというアクションをLabMemberManagerに登録します。コールバック関数への返却値は文字列型のメッセージです。コールバック関数は省略できます。

- Lmmifc.logout(name, cbf);  
  [name: メンバー名, cbf: コールバック関数]  
  nameに指定されたメンバー名がログアウトしたというアクションをLabmemberManagerに登録します。コールバック関数への返却値は文字列型のメッセージです。コールバック関数は省略できます。
  
- Lmmifc.getMemberInfo(name, cbf);  
  [name: メンバー名, cbf: コールバック関数]  
  nameに指定されたメンバー名の情報オブジェクトを取得します。取得した情報はMemberInfoBean型のオブジェクトとしてコールバック関数へ渡されます。

- Lmmifc.getMemberInfoList(cbf);  
  [cbf: コールバック関数]  
  LabMemberManagerに現在登録されているメンバーの情報を配列として取得します。取得された情報はMemberInfoBean型の配列としてコールバック関数へ渡されます。

- Lmmifc.getActionRecord(cbf);  
  [cbf: コールバック関数]
  現在LabMemberManagerに登録されているすべての記録(アクションレコード)を取得します。取得した記録はActionRecordBean型オブジェクトの配列としてコールバック関数へ渡されます。

- Lmmifc.getMemberActionRecord(name, cbf);  
  [name: メンバー名, cbf: コールバック関数]
  nameに指定されたメンバー名の記録(アクションレコード)を取得します。取得した記録はActionRecordBean型オブジェクトの配列としてコールバック関数へ渡されます。

####Lmmutil

- Lmmutil.addActionRecordListToTable(jq\_elem, ary\_obj);  
  [jq\_elem: テーブルのjQuery要素, ary\_obj: ActionRecordBean型オブジェクトの配列]  
  ActionRecordBean型のオブジェクトの情報をjq\_elemに指定されたテーブルに行として追加します。

- Lmmutil.addMemberInfoToTable(jq\_elem,  info);  
  [jq\_elem: テーブルのjQuery要素, info: MemberInfoBean型オブジェクト]  
  MemberInfoBean型オブジェクトの情報をjq\_elemに指定されたテーブルに行として追加します。infoに指定するのは配列でも配列でなくてもOKです。
  
