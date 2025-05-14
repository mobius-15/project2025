　空母と艦載機の運用をモチーフにしてJava,Servletで開発したフライトプランニングシステム。未完成。<br/>
index.jspでログイン(パスワード：password) <br/>
Command.jspにてCAP,WP数を設定。(任務種別のセレクトボックス内はCAP以外の機能は未実装。WP推奨2か所以上)<br/>
Planning.jspにて各ポイントの高度(feet)、速度(knot)、方位(0-360)、区間距離(nm)を設定。Testplanサーブレットでコントロール。<br/>
FlightPlanner.jspにて設定した航空機の情報表示、推定消費燃料を計算し反映。<br/>
carrierConfig.jspにて母艦(拠点)の情報設定。これ以降CarrierInfoサーブレットでコントロール。<br/>
MissionReview.jspに遷移し、航空機、母艦それぞれの情報をまとめて表示。<br/>
同画面でLoadoutConfig.jspに遷移し装備の設定も可能。<br/>
設定後、MissionReview.jspに再遷移して設定した装備を確認。<br/>
View MAPをクリックしleaflet地図(MissionMAP.jsp)を表示。地図上にはPlanning.jspで設定した経路が実線で表示される。<br/>
母艦の位置は地図上クリックする事で任意の場所に変更可能。<br/>
現状ではターゲットの設定項目も出現するので要注意。（機能はしておらず）<br/>
同地図画面からのMissionReviewへの再遷移も可能。母艦の位置変更した場合、座標に反映される。<br/>
本来は、MySQLデータベースに設定した標準大気表(高度6万feetまで)を用いて速度計算(CAS→TAS)に利用するが<br/>
外部公開していないため速度はCASで計算される。そのため所要時間、燃料等は異なる結果となる。<br/>
生成したフライトプラン、機体や母艦の情報もDBに保存する事を前提としている。<br/>
なお各種機能設計、パッケージやクラスの構成にChatGPT(4o)の助力を得た。
