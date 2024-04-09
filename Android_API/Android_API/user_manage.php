<?php
    require_once "connectdb.php";

    $sql = "SELECT * FROM khachhang";
    $result = mysqli_query($conn, $sql);
    if($result->num_rows > 0){
        while($r = $result->fetch_assoc()){
            $data_customers[] = ["MaKH"=>$r["MaKH"], "SDT"=>$r["SDT"], "DiemTL"=>$r["DiemTL"], "UserName"=>$r["UserName"], "PassWord"=>$r["PassWord"]];
        }
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="../Android_API/assets/css/index.css">
</head>
<body>
    <div class="row">
        <div class="col-3">
            <ul class="sidebar">
                <li class="d-flex justify-content-center"><img src="../Android_API/assets/image/logo_coffee.png" alt=""></li>
                <li><a href="index.php">Trang chủ</a></li>
                <li><a href="user_manage.php">Quản lý khách hàng</a></li>
                <li><a href="voucher_manage.php">Quản lý ưu đãi</a></li>
                <li><a href="coffee_manage.php">Quản lý đồ uống</a></li>
            </ul>
        </div>

        <div class="content col-9">
            <table cellpadding="10" cellspacing="10" border="0" style="border-collapse: collapse; margin: auto">

                <tr class="control" style="text-align: left; font-weight: bold; font-size: 20px">
                    <td colspan="5">
                        <a href="add_user.php">Thêm Khách Hàng</a>
                    </td>
                </tr>
                <tr class="header">
                    <td>Mã Khách Hàng</td>
                    <td>SĐT</td>
                    <td>Username</td>
                    <td>Password</td>
                    <td>Điểm Tích Lũy</td>
                </tr>
                <?php
                    foreach($data_customers as $item){
                        echo '<tr class="item">
                                <td>'.$item["MaKH"].'</td>
                                <td>'.$item["SDT"].'</td>
                                <td>'.$item["UserName"].'</td>
                                <td>'.$item["PassWord"].'</td>
                                <td>'.$item["DiemTL"].'</td>
                                <td><a href="edit_user.php?makh='.$item["MaKH"].'">Edit</a> | <a href="delete_user.php?makh='.$item["MaKH"].'" class="delete">Delete</a></td>
                            </tr>';
                    }
                ?>
            </table>
        </div>
    </div>
</body>
</html>