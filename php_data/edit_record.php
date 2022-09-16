<?php 

header("Content-Type:text/json; charset=UTF-8");

$serverName = "localhost,1433";  // 서버아이피



$uid ="medisook";    // sql 접속계정

$pwd = "1715231";  // 계정 비밀번호

$connectionInfo = array( "UID"=>$uid,

    "PWD"=>$pwd,

    "Database"=>"seyoung",'CharacterSet'=>'UTF-8');  // 접근할 DB


$conn = sqlsrv_connect( $serverName, $connectionInfo);

//Data=검색어, Except=제외성분, Efcy=효능, Who=대상 


if(isset($_POST['ID']) && isset($_POST['DRUG_NAME'])){
    
    $id=$_POST['ID'];
    $drug=$_POST['DRUG_NAME'];
    
    if( $conn == false ){

    echo "Unable to connect.</br>";

    die( print_r( sqlsrv_errors(), true));

    }

    else

    {

    //echo "connect!!!!!!!!.</br>";
    $query = "DELETE FROM RECORD WHERE USER_ID="."'".$id."'"."AND DRUG_NAME="."'".$drug."'";


    $stmt = sqlsrv_query($conn, $query);
    if( $stmt === false ) {
        die( print_r( sqlsrv_errors(), true));
   }

    //echo $id;

      
      
 
// 쿼리를 실행하여 statement 를 얻어온다

    //sqlsrv_query($conn, $query);

    sqlsrv_free_stmt($stmt);
    sqlsrv_close($conn);
    


   }

    
}


?>