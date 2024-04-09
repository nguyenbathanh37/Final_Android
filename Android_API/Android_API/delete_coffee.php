<?php
    require_once "connectdb.php";

    $sql = "DELETE FROM sanpham WHERE MaSP='".$_GET['masp']."' ";

    if ($conn->query($sql) === TRUE) {
        header("Location: coffee_manage.php");
    } else {
        echo "Error deleting record: " . $conn->error;
    }

    $conn->close();
?>