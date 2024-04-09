<?php
    require_once "../connectdb.php";

    if(!empty($_POST["Ngay"]) && !empty($_POST["TongTien"]) && !empty($_POST["UserName"]) && !empty($_POST["SoLuong"])) {
        $date = $_POST['Ngay'];
        $total = $_POST['TongTien'];
        $username = $_POST['UserName'];
        $quantity = $_POST['SoLuong'];
        $sql = "insert into hoadon(Ngay, TongTien, UserName, SoLuong) values('".$date."', '".$total."', '".$username."', '".$quantity."')";
        
        if(mysqli_query($conn, $sql)) {
            echo "success";
        } else echo "Registration Failed";

    }
    else echo "All fields are require";
?>