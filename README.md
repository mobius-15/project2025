　空母と艦載機の運用をモチーフにしてJava,Servletで開発したフライトプランニングシステム。<br/>
フライトシミュレーションソフトをプレイ出来る性能を有さないPCでも距離や経路、燃料を算出し、視覚化できる事を目指して開発した。<br/>
各種機能設計、パッケージやクラスの構成にChatGPT(4o)の助力を得た。<br/>
index.jspでログイン(パスワード：password) <br/>
Command.jspにてCAP,WP数を設定。(任務種別のセレクトボックス内はCAP以外の機能は未実装。WP推奨2か所以上)<br/>
Planning.jspにて各ポイントの高度(feet)、速度(knot)、方位(0-360)、区間距離(nm)を設定。Testplanサーブレットでコントロール。<br/>
CAP（哨戒）のため旋回を行う地点を設定可能。（WP数/2の中点を推奨）燃料消費量は上昇フェーズなどによって変動(AircraftLogic,FuelLogicにより)<br/>
FlightPlanner.jspに遷移して設定した航空機の情報表示、推定消費燃料を計算し反映。<br/>
carrierConfig.jspに遷移して母艦(拠点)の情報設定。これ以降CarrierInfoサーブレットでコントロール。<br/>
MissionReview.jspに遷移し、航空機、母艦それぞれの情報をまとめて表示。
セッションスコープを保持するため、統合した情報を格納できるMissionContextを用意している。<br/>
同画面でLoadoutConfig.jspに遷移し装備の設定も可能。（装備設定用のLoadoutServletを用いる）<br/>
設定後、MissionReview.jspに再遷移して設定した装備を確認。<br/>
View MAPをクリックしleaflet地図(MissionMAP.jsp)を表示。地図上にはPlanning.jspで設定した経路が実線で表示される。<br/>
地図内の経路描画、アラート等はJavaScriptで記述。
母艦の位置は地図上クリックする事で任意の場所に変更可能。<br/>
現状ではターゲットの設定項目も出現するので要注意。（機能はしておらず）<br/>
同地図画面からのMissionReviewへの再遷移も可能。母艦の位置変更した場合、座標に反映される。<br/>
本来は、MySQLデータベースに設定した標準大気表(高度6万feetまで)を用いて速度計算(CAS→TAS)に利用するが<br/>
外部公開していないため速度はCASで計算される。そのため所要時間、燃料等は異なる結果となる。<br/>
生成したフライトプラン、機体や母艦の情報もDBに保存する事を前提としている。(SaveServletを用意)<br/>

テスト時：index(CAP,6)-WP1(200ft,200kt,5nm,180°),-Carrier(68 Nimitz,lat:19.181,lon:134.139,speed:30kt,disp:100500)
                      WP2(5000ft,280kt,10nm,90°),
                      WP3(25000ft,330kt,90nm,110°),
                      WP4(28000ft,280kt,10nm,150°),
                      WP5(25000ft,330kt,90nm,290°),
                      WP6(2500ft,260kt,15nm,320°)
