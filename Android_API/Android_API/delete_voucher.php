<?php
    require_once "connectdb.php";

    $sql = "DELETE FROM uudai WHERE MaUD='".$_GET['maud']."' ";

    if ($conn->query($sql) === TRUE) {
        header("Location: voucher_manage.php");
    } else {
        echo "Error deleting record: " . $conn->error;
    }

    $conn->close();
?>