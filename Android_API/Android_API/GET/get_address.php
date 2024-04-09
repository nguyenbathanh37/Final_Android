<?php
require_once "../connectdb.php";

if(!empty($_POST["UserName"])){
    $name = $_POST["UserName"];
    $sql = "SELECT DiaChi FROM khachhang WHERE UserName = '$name'";

    $result = mysqli_query($conn, $sql);

    if($result->num_rows > 0){
        while($r = $result->fetch_assoc()){
            $data_products[] = ["message" =>"success", "DiaChi"=>$r["DiaChi"]];
        }
    }
}
echo json_encode($data_products, JSON_PRETTY_PRINT);
?>