# restapi

SpringBootの課題
 
## 簡単な説明

SpringBootでDBの追加・更新・削除・検索を行う
 
## 機能
 
- 機能1（DB接続）
- 機能2（DB検索/全件検索/Like検索/And検索）
- 機能３（DB追加・更新・削除）


## 必要要件
 
- Java SE 8
- Spring Tool Suite
- lombok
 
## 使い方
 
1. 全件検索：$ curl http://localhost:8080/api/items
2. 名前Like検索：$ curl http://localhost:8080/api/items/findByNameLike?keyword=○○ 
4. DB更新：$ curl http://localhost:8080/api/items -i -XPOST -H "Content-Type: application/json" -d "{\"name\":\"〇〇\",\"price\":\"〇〇\"}"
5. DB削除：$ curl http://localhost:8080/api/items/○ -i -XDELETE


## インストール
 
```
$ git clone https://github.com/AyanoNakashima/reatapi
$ git pull master
$ ~do anything~
```
 
## 作者
＠AyanoNakashima 

## ライセンス
不明

</blockquote>
