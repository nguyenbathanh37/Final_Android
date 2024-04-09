<?php
    require_once "../connectdb.php";

    if(!empty($_POST["UserName"])){
        $username = $_POST["UserName"];

        $sql = "SELECT * FROM hoadon WHERE UserName = '$username'";
        $result = mysqli_query($conn, $sql);
        if($result->num_rows > 0){
            while($r = $result->fetch_assoc()){
                $data_orders[] = ["MaHD"=>$r["MaHD"], "Ngay"=>$r["Ngay"], "TongTien"=>$r["TongTien"], "GiamGia"=>$r["GiamGia"], "DiemTL"=>$r["DiemTL"], "ChiPhiKhac"=>$r["ChiPhiKhac"], "UserName"=>$r["UserName"], "SoLuong"=>$r["SoLuong"]];
            }
        }
    }
    echo json_encode($data_orders, JSON_PRETTY_PRINT);
?>