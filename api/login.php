<?php


if ($_SERVER['REQUEST_METHOD']=='POST') {
  # code...

  $response= array();
  //mendapatkan data
  $username = $_POST['username'];

  $password = $_POST['password'];

require_once('config.php');

//cek npm terdaftar apa belum

$sql ="SELECT * FROM user WHERE username ='$username' AND password ='$password'";
$check = mysqli_fetch_array(mysqli_query($con,$sql));
if (!isset($check)) {
  # code...
   $response['value'] = 0;
$response["message"] = "ups coba lagi";
echo json_encode($response);
}  else {



$res = mysqli_query($con,$sql);
  $result = array();
  while($row = mysqli_fetch_array($res)){
    array_push($result, array('id'=>$row[0], 'nama'=>$row[1],'ttl'=>$row[2],'no_hp'=>$row[3],'alamat'=>$row[4],'foto_profil'=>$row[7],'level'=>$row[8]));
  }
  echo json_encode(array("value"=>1,"result"=>$result));

}

mysqli_close($con);


}
?>