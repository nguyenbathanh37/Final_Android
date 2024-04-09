<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<style>
    body{
        padding-top: 50px;
    }
    table{

        text-align: center;
    }
    tr.item{
        border-top: 1px solid #5e5e5e;
        border-bottom: 1px solid #5e5e5e;
    }

    tr.item:hover{
        background-color: #d9edf7;
    }

    tr.item td{
        min-width: 150px;
    }

    tr.header{
        font-weight: bold;
    }

    a{
        text-decoration: none;
    }
    a:hover{
        color: deeppink;
        font-weight: bold;
    }
</style>


<div class="container" style="width: 600px">
    <h2>Thêm Khách Hàng</h2>
    <form action="" method="post">
        <div class="form-group">
            <label for="sdt">Số Điện Thoại:</label>
            <input type="number" class="form-control" id="sdt" placeholder="Nhập số điện thoại khách hàng" name="sdt">
        </div>
        <div class="form-group">
            <label for="dtl">Diểm Tích Lũy:</label>
            <input type="number" class="form-control" id="dtl" placeholder="Nhập Điểm Tích Lũy" name="dtl">
        </div>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" placeholder="Nhập Username" name="username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="text" class="form-control" id="password" placeholder="Nhập Password" name="password">
        </div>
        <button type="submit" class="btn btn-primary">Add</button>
        <button type="reset" class="btn btn-default">Reset</button>
    </form>

    <br>
</div>


</body>
</html>

<?php
    require_once "connectdb.php";

    if(!(empty($_POST['sdt']) || empty($_POST['username']) || empty($_POST['password']))){
        $sdt = $_POST['sdt'];
        $dtl = $_POST['dtl'];
        $username = $_POST['username'];
        $password = $_POST['password'];
    
        $stmt = $conn->prepare("INSERT INTO khachhang (SDT, UserName, PassWord, DiemTL) VALUES (?, ?, ?, ?)");
        $stmt->bind_param("ssss", $sdt, $username, $password, $dtl);
        $stmt->execute();
        $stmt->close();
        $conn->close();
        header("Location: user_manage.php");
    }
?>