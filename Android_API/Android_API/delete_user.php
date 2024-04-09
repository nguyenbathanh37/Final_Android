<?php
    require_once "connectdb.php";

    $sql = "DELETE FROM khachhang WHERE MaKH='".$_GET['makh']."' ";

    if ($conn->query($sql) === TRUE) {
        header("Location: user_manage.php");
    } else {
        echo "Error deleting record: " . $conn->error;
    }

    $conn->close();
?>