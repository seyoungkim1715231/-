<?php 

header("Content-Type:text/json; charset=UTF-8");

$serverName = "localhost,1433";  // 서버아이피



$uid ="medisook";    // sql 접속계정

$pwd = "1715231";  // 계정 비밀번호

$connectionInfo = array( "UID"=>$uid,

    "PWD"=>$pwd,

    "Database"=>"seyoung",'CharacterSet'=>'UTF-8');  // 접근할 DB


$conn = sqlsrv_connect( $serverName, $connectionInfo);


if(isset($_POST['ID'])){
    $id=$_POST['ID'];
    if( $conn == false ){

    echo "Unable to connect.</br>";

    die( print_r( sqlsrv_errors(), true));

}

else

{

    //echo "connect!!!!!!!!.</br>";
    
    $query = "SELECT* FROM RECORD WHERE USER_ID ="."'".$id."'"; 

 
// 쿼리를 실행하여 statement 를 얻어온다

    $stmt = sqlsrv_query($conn, $query);
 
    $arr= array(); //빈 배열 생성
 
    while($row=sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)){
        array_push($arr,array('USER_ID'=>$row['USER_ID'],'DRUG_NAME'=>$row['DRUG_NAME'],
        'OTC'=>$row['OTC'],
        'IMAGE'=>$row['IMAGE'],
        'TAG1'=>$row['TAG1'],
        'TAG2'=>$row['TAG2'],
        'TAG3'=>$row['TAG3'],
        'GOODBAD'=>$row['GOODBAD'],
        'DATE1'=>$row['DATE1'],
        'DATE2'=>$row['DATE2']));
    }


   // $obarr =(Object) $arr;

    $json= json_encode(array("mypage"=>$arr), JSON_UNESCAPED_UNICODE);
    echo $json;
   

    sqlsrv_free_stmt($stmt);

 

// 데이터베이스 접속을 해제한다

    sqlsrv_close($conn);


   }

    
}

//$drug_name=$_POST['Data'];
//$drug_name='타이레놀';



?>

