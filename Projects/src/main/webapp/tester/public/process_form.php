<?php
// フォームが送信されたか確認
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // 送信されたデータを取得（サニタイズ）
    $name = isset($_POST['name']) ? htmlspecialchars(trim($_POST['name']), ENT_QUOTES, 'UTF-8') : '';
    $gender = isset($_POST['gender']) ? htmlspecialchars($_POST['gender'], ENT_QUOTES, 'UTF-8') : '';
    $age = isset($_POST['age']) ? intval($_POST['age']) : '';
    $language = isset($_POST['language']) ? htmlspecialchars($_POST['language'], ENT_QUOTES, 'UTF-8') : '';
    $impression = isset($_POST['impression']) ? htmlspecialchars(trim($_POST['impression']), ENT_QUOTES, 'UTF-8') : '';
    
    // 簡単なバリデーション
    $errors = [];
    
    if (empty($name)) {
        $errors[] = "名前を入力してください。";
    }
    if (empty($gender)) {
        $errors[] = "性別を選択してください。";
    }
    if ($age === '' || $age < 0 || $age > 120) {
        $errors[] = "年齢は0から120の間で入力してください。";
    }
    
    // エラーがない場合の処理
    if (empty($errors)) {
        echo "<h2>送信されたデータ</h2>";
        echo "<p><strong>名前:</strong> " . $name . "</p>";
        echo "<p><strong>性別:</strong> " . $gender . "</p>";
        echo "<p><strong>年齢:</strong> " . $age . "</p>";
        echo "<p><strong>言語:</strong> " . $language . "</p>";
        echo "<p><strong>感想:</strong> " . nl2br($impression) . "</p>";
    } else {
        // エラー表示
        echo "<h2>エラー</h2>";
        foreach ($errors as $error) {
            echo "<p style='color:red;'>$error</p>";
        }
    }
} else {
    echo "<p>フォームが送信されていません。</p>";
}
?>
