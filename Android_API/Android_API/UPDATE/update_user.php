<?php
    require_once "../connectdb.php";

    if(!(empty($_POST['sdt']) || empty($_POST['password']))){
        $sdt = $_POST['sdt'];
        $password = $_POST['password'];
    
        $stmt = $conn->prepare("UPDATE khachhang SET  PassWord=? WHERE SDT=?");
        $stmt->bind_param("ss", $password, $sdt);
        $stmt->execute();
        $stmt->close();
        $conn->close();
        $result = array("status" => "success", "message" => "Update thành công");
    }
    echo json_encode($result, JSON_PRETTY_PRINT);
?>