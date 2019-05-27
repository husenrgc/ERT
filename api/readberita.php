<?php
require_once('config.php');
if($_SERVER['REQUEST_METHOD']=='GET') {
  $sql = "SELECT * FROM berita ORDER BY id DESC";
  $res = mysqli_query($con,$sql);
  $results = array();
  while($row = mysqli_fetch_array($res)){
    array_push($results, array('kd_berita'=>$row[1],'nama_user'=>$row[3],'isiberita'=>$row[4],'judul'=>$row[5],'sumber'=>$row[6], 'tanggal'=>$row[7]));
  }
  echo json_encode(array("value"=>1,"results"=>$results));
  mysqli_close($con);
}