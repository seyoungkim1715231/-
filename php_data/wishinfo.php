<?php 

header("Content-Type:text/json; charset=UTF-8");

$serverName = "localhost,1433";  // 서버아이피



$uid ="medisook";    // sql 접속계정

$pwd = "1715231";  // 계정 비밀번호

$connectionInfo = array( "UID"=>$uid,

    "PWD"=>$pwd,

    "Database"=>"seyoung",'CharacterSet'=>'UTF-8');  // 접근할 DB


$conn = sqlsrv_connect( $serverName, $connectionInfo);


if(isset($_POST['Data'])){
    $drug_name=$_POST['Data'];
    if( $conn == false ){

    echo "Unable to connect.</br>";

    die( print_r( sqlsrv_errors(), true));

}

else

{

    //echo "connect!!!!!!!!.</br>";
    
    $query = "SELECT* FROM TOTALINFO WHERE DRUG_NAME LIKE"."'%".$drug_name."%'"; 

 
// 쿼리를 실행하여 statement 를 얻어온다

    $stmt = sqlsrv_query($conn, $query);
 
    $arr= array(); //빈 배열 생성
 
    while($row=sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)){
        array_push($arr,array('ENTP_NAME'=>$row['ENTP_NAME'],'DRUG_NAME'=>$row['DRUG_NAME'],
        'DRUG_CODE'=>$row['DRUG_CODE'],
        'CLASS_NAME'=>$row['CLASS_NAME'],//신경안정제 뭐이런 약 분류
        'IMAGE'=>$row['DRUG_IMAGE'],
        'QNT'=>$row['QNT'],//주요 성분 갯수
        'OTC'=>$row['OTC'],//일반/전문
        'CHART'=>$row['CHART'],//약 묘사
        'EFCY'=>$row['EFCY'],//효능
        'USEMETHOD'=>$row['USEMETHOD'],
        'QESITM'=>$row['QESITM'],//주의사항
        'DEPOSIT'=>$row['DEPOSIT'],//보관방법
        'VALID_TERM'=>$row['VALID_TERM'],//복용기간
        'TOTAL_CONTENT'=>$row['TOTAL_CONTENT'],//총량
        'MAIN_INGR'=>$row['MAIN_INGR'],
        'INGR_NAME'=>$row['INGR_NAME'],
        'IMBU_COUNT'=>$row['IMBU_COUNT'],
        'NOIN_COUNT'=>$row['NOIN_COUNT'],
        'KID_COUNT'=>$row['KID_COUNT'],
        'WARNING'=>$row['WARNING']));
    }


   // $obarr =(Object) $arr;

    $json= json_encode(array("drug"=>$arr), JSON_UNESCAPED_UNICODE);
    echo $json;
   

    sqlsrv_free_stmt($stmt);

 

// 데이터베이스 접속을 해제한다

    sqlsrv_close($conn);


   }

    
}

//$drug_name=$_POST['Data'];
//$drug_name='타이레놀';



?>

