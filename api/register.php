<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
  # code...

  $response= array();
  //mendapatkan data
  $nama = $_POST['nama'];
  $ttl= $_POST['ttl'];
  $alamat = $_POST['alamat'];
 $no_hp = $_POST['no_hp'];
  $username = $_POST['username'];
  $password = $_POST['password'];
 





require_once('config.php');
//cek npm terdaftar apa belum

$sql ="SELECT * FROM user WHERE username= '$username'";

$check = mysqli_fetch_array(mysqli_query($con,$sql));
if (isset($check)) {
  # code...
  $response["value"] = 0;
  $response["message"] = "oops! username sudah terdaftar";
  echo json_encode($response);
}  else {

$sql ="INSERT INTO user (nama,ttl,no_hp,alamat,username, password,level) VALUES ('$nama','$ttl','$no_hp','$alamat','$username', '$password','user')";
if (mysqli_query($con,$sql)) {
  # code...
$response['value'] = 1;
$response["message"]= "sukses mendaftar silahkan login";
echo json_encode($response);

} else {

  $response['value'] = 0;
  $response["message"] ="ups coba lagi";
  echo json_encode($response);
}


}
mysqli_close($con);
}else
{

$response['value'] = 0;
$response["message"] = "ups coba lagi";
echo json_encode($response);

}
?>