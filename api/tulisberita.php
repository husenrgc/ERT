<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
  # code...

  $response= array();
  //mendapatkan data
  $kd_berita = uniqid();
  $username = $_POST['username'];
  $nama_user= $_POST['nama_user'];
  $isiberita = $_POST['isiberita'];
  $judul = $_POST['judul'];
  $sumber = $_POST['sumber'];
  $tanggal = $_POST['tanggal'];

  // $waktu = $_POST['waktu'];
  





require_once('config.php');




$sql ="INSERT INTO berita (kd_berita,username,nama_user,isiberita,judul,sumber,tanggal) VALUES ( '$kd_berita' ,'$username','$nama_user','$isiberita', '$judul','$sumber' ,'$tanggal')";
if (mysqli_query($con,$sql)) {
  # code...
$response['value'] = 1;
$response["message"]= "Berita Berhasil diupload";
echo json_encode($response);

} else {

  $response['value'] = 0;
  $response["message"] ="ups coba lagi";
  echo json_encode($response);
}

mysqli_close($con);
}else
{

$response['value'] = 0;
$response["message"] = "ups coba lagi";
echo json_encode($response);

}
?>