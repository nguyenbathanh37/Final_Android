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
    <h2>Thêm Voucher</h2>
    <form action="" method="post">
        <div class="form-group">
            <label for="tenvc">Tên Voucher:</label>
            <input type="text" class="form-control" id="tenvc" placeholder="Nhập tên Voucher" name="tenvc">
        </div>
        <div class="form-group">
            <label for="hsd">Hạn Sử Dụng:</label>
            <input type="date" class="form-control" id="hsd" name="hsd">
        </div>
        <div class="form-group">
            <label for="gg">Giảm Giá:</label>
            <input type="number" class="form-control" id="gg" placeholder="Nhập số tiền giảm giá" name="gg">
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

    if(!(empty($_POST['tenvc']) || empty($_POST['hsd']) || empty($_POST['gg']))){
        $tenvc = $_POST['tenvc'];
        $hsd = $_POST['hsd'];
        $gg = $_POST['gg'];
    
        $stmt = $conn->prepare("INSERT INTO uudai (TenUD, HanSD, GiamGia) VALUES (?, ?, ?)");
        $stmt->bind_param("sss", $tenvc, $hsd, $gg);
        $stmt->execute();
        $stmt->close();
        $conn->close();
        header("Location: voucher_manage.php");
    }
?>