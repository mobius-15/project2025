　空母と艦載機の運用をモチーフにしてJava,Servletで開発したフライトプランニングシステム。
index.jspでログイン(パスワード：password) 
Command.jspにてCAP,WP数を設定。(任務種別のセレクトボックス内はCAP以外の機能は未実装。WP推奨2か所以上)
Planning.jspにて各ポイントの高度(feet)、速度(knot)、方位(0-360)、区間距離(nm)を設定。Testplanサーブレットでコントロール。
FlightPlanner.jspにて設定した航空機の情報表示、推定消費燃料を計算し反映。
carrierConfig.jspにて母艦(拠点)の情報設定。これ以降CarrierInfoサーブレットでコントロール
MissionReview.jspに遷移し、航空機、母艦それぞれの情報をまとめて表示。
同画面でLoadoutConfig.jspに遷移し装備の設定も可能。
設定後、MissionReview.jspに再遷移して設定した装備を確認。
View MAPをクリックしleaflet地図(MissionMAP.jsp)を表示。
地図上にはPlanning.jspで設定した経路が実線で表示される。
母艦の位置は地図上クリックする事で任意の場所に変更可能。
現状ではターゲットの設定項目も出現するので要注意。（機能はしておらず）
同地図画面からのMissionReviewへの再遷移も可能。
母艦の位置変更した場合、座標に反映される。
