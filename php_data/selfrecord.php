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


if(isset($_POST['Id'])){
    
    $id=$_POST['Id'];
   
    if( $conn == false ){

    echo "Unable to connect.</br>";

    die( print_r( sqlsrv_errors(), true));

    }

    else

    {

    //echo "connect!!!!!!!!.</br>";
    
    $query = "SELECT* FROM RECORD WHERE ID="."'".$id."%'"; 
   

 
// 쿼리를 실행하여 statement 를 얻어온다

    $stmt = sqlsrv_query($conn, $query);
 
    $arr= array(); //빈 배열 생성
 
    while($row=sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)){
        array_push($arr,array('ID'=>$row['ID'],'DRUG_NAME'=>$row['DRUG_NAME'],'OTC'=>$row['OTC'],
         'IMAGE'=>$row['DRUG_IMAGE'],'TAG1'=>$row['TAG1'],'TAG2'=>$row['TAG2'],
         'TAG3'=>$row['TAG3'],'GOODBAD'=>$row['GOODBAD'],'DATE'=>$row['DATE']));//약 이름, 회사 이름, 이미지, 효능, 주의사항, 성분
    }

   // $obarr =(Object) $arr;

    $json= json_encode(array("record"=>$arr), JSON_UNESCAPED_UNICODE);
    echo $json;
   

    sqlsrv_free_stmt($stmt);

 

// 데이터베이스 접속을 해제한다

    sqlsrv_close($conn);


   }

    
}

//$drug_name=$_POST['Data'];
//$drug_name='타이레놀';



?>

