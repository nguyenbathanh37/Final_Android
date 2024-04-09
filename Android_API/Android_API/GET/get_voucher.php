<?php
    require_once "../connectdb.php";

    $sql = "SELECT * FROM uudai";
    $result = mysqli_query($conn, $sql);
    $data_products = array();
    if($result->num_rows > 0){
        while($r = $result->fetch_assoc()){
            $data_products[] = ["status"=>"success","MaUD"=>$r["MaUD"], "Ten"=>$r["TenUD"], "GiamGia"=>$r["GiamGia"]];
        }
    }
    echo json_encode($data_products, JSON_PRETTY_PRINT);
?>
