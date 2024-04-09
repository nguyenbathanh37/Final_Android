<?php
    require_once "../connectdb.php";

    if(!empty($_POST["UserName"])){
        $username = $_POST["UserName"];

        $sql = "SELECT * FROM khachhang WHERE UserName = '$username'";
        $result = mysqli_query($conn, $sql);
        if($result->num_rows > 0){
            while($r = $result->fetch_assoc()){
                $data_users[] = ["MaKH"=>$r["MaKH"], "SDT"=>$r["SDT"], "UserName"=>$r["UserName"], "PassWord"=>$r["PassWord"], "DiemTL"=>$r["DiemTL"], "DiaChi"=>$r["DiaChi"]];
            }
        }
    }
    echo json_encode($data_users, JSON_PRETTY_PRINT);
?>