<?php
    require_once "../connectdb.php";

    $sql = "SELECT * FROM sanpham";
    $result = mysqli_query($conn, $sql);
    if($result->num_rows > 0){
        while($r = $result->fetch_assoc()){
            $data_products[] = ["MaSP"=>$r["MaSP"], "HinhAnh"=>$r["HinhAnh"], "GiaSP"=>$r["GiaSP"], "TenSP"=>$r["TenSP"], "MoTa"=>$r["MoTa"], "MaLSP"=>$r["MaLSP"]];
        }
    }
    echo json_encode($data_products, JSON_PRETTY_PRINT);
?>