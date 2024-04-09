<?php
    require_once "../connectdb.php";
    
    if(!empty($_POST["SDT"]) && !empty($_POST["UserName"]) && !empty($_POST["Password"]) && !empty($_POST["DiaChi"])) {
        $sdt = $_POST['SDT'];
        $username = $_POST['UserName'];
        $password = $_POST['Password'];
        $diachi = $_POST['DiaChi'];

        $sql = "insert into khachhang (SDT ,UserName, PassWord, DiaChi) values('".$sdt."', '".$username."', '".$password."', '".$diachi."')";
        if(mysqli_query($conn, $sql)) {
            echo "success";
        } else echo "Registration Failed";

    }
    else echo "All fields are require";
?>