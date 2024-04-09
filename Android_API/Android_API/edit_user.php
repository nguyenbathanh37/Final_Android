<?php
    require_once "connectdb.php";

    $sql = "SELECT * FROM khachhang WHERE MaKH='".$_GET['makh']."' ";
    $result = $conn->query($sql);
    while($r = $result->fetch_assoc()){
        $user = ["MaKH"=>$r["MaKH"], "SDT"=>$r["SDT"], "DiemTL"=>$r["DiemTL"], "UserName"=>$r["UserName"], "PassWord"=>$r["PassWord"]];
    }
?>
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
    <h2>Sửa Khách Hàng</h2>
    <form action="" method="post">
        <div class="form-group">
            <label for="sdt">Số Điện Thoại:</label>
            <input type="number" class="form-control" id="sdt" value="<?php echo $user["SDT"]?>" name="sdt">
        </div>
        <div class="form-group">
            <label for="dtl">Diểm Tích Lũy:</label>
            <input type="number" class="form-control" id="dtl" value="<?php echo $user["DiemTL"]?>" name="dtl">
        </div>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" value="<?php echo $user["UserName"]?>" name="username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="text" class="form-control" id="password" value="<?php echo $user["PassWord"]?>" name="password">
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
        <button type="reset" class="btn btn-default">Reset</button>
    </form>

    <br>
</div>


</body>
</html>

<?php
    require_once "connectdb.php";

    if(!(empty($_POST['sdt']) || empty($_POST['username']) || empty($_POST['password']))){
        $makh = $_GET['makh'];
        $sdt = $_POST['sdt'];
        $dtl = $_POST['dtl'];
        $username = $_POST['username'];
        $password = $_POST['password'];
    
        $stmt = $conn->prepare("UPDATE khachhang SET SDT=?, UserName=?, PassWord=?, DiemTL=? WHERE MaKH=?");
        $stmt->bind_param("sssss", $sdt, $username, $password, $dtl, $makh);
        $stmt->execute();
        $stmt->close();
        $conn->close();
        header("Location: user_manage.php");
    }
?>