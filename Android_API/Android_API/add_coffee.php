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
    <h2>Thêm Sản Phẩm</h2>
    <form action="" method="post">
        <div class="form-group">
            <label for="anhsp">Link Ảnh:</label>
            <input type="text" class="form-control" id="anhsp" placeholder="Nhập link ảnh sản phẩm" name="anhsp">
        </div>
        <div class="form-group">
            <label for="gia">Giá:</label>
            <input type="number" class="form-control" id="gia" placeholder="Nhập giá sản phẩm" name="gia">
        </div>
        <div class="form-group">
            <label for="tensp">Tên Sản Phẩm:</label>
            <input type="text" class="form-control" id="tensp" placeholder="Nhập tên sản phẩm" name="tensp">
        </div>
        <div class="form-group">
            <label for="mota">Mô Tả:</label>
            <input type="text" class="form-control" id="mota" placeholder="Nhập mô tả" name="mota">
        </div>
        <div class="form-group">
            <label for="malsp">Mã Loại Sản Phẩm:</label>
            <input type="text" class="form-control" id="malsp" placeholder="Nhập mã loại sản phẩm" name="malsp">
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

    if(!(empty($_POST['anhsp']) || empty($_POST['gia']) || empty($_POST['tensp']) || empty($_POST['mota']) || empty($_POST['malsp']))){
        $anhsp = $_POST['anhsp'];
        $gia = $_POST['gia'];
        $tensp = $_POST['tensp'];
        $mota = $_POST['mota'];
        $malsp = $_POST['malsp'];

    
        $stmt = $conn->prepare("INSERT INTO sanpham (HinhAnh, GiaSP, TenSP, MoTa, MaLSP) VALUES (?, ?, ?, ?, ?)");
        $stmt->bind_param("sssss",$anhsp, $gia, $tensp, $mota, $malsp);
        $stmt->execute();
        $stmt->close();
        $conn->close();
        header("Location: coffee_manage.php");
    }
?>