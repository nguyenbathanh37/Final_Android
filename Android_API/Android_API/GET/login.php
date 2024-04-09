<?php

if(!empty($_POST["UserName"]) && !empty($_POST["Password"])) {
    $dbCon = mysqli_connect("localhost", "root", "", "coffeeshop");
    $username = $_POST['UserName'];
    $password = $_POST['Password'];
    $result = array();
    if($dbCon) {
        $sql = "select * from khachhang where UserName = '".$username."'";
        $res = mysqli_query($dbCon, $sql);
        if(mysqli_num_rows($res) !=0) {
            $row = mysqli_fetch_assoc($res);
            if($username == $row['UserName'] && $password == $row['PassWord']) {
                $result = array("status" => "success", "message" => "Login successfully", "username"=>$row['UserName'], "password" => $row['PassWord']);
            } else if($password != $row['PassWord']) {
                echo $result = array("status" => "0", "message" => "Sai mật khẩu vui lòng thử lại");
            }
        }else echo $result = array("status" => "failed -1", "message" => "Retry with correct email and password");
    } else echo $result = array("status" => "failed", "message" => "Database connection failed");
} else echo $result = array("status" => "failed", "message" => "All fields are require");

echo json_encode($result, JSON_PRETTY_PRINT);

