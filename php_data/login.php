<?php 

header("Content-Type:text/json; charset=UTF-8");

$serverName = "localhost,1433";  // 서버아이피



$uid ="medisook";    // sql 접속계정

$pwd = "1715231";  // 계정 비밀번호

$connectionInfo = array( "UID"=>$uid,

    "PWD"=>$pwd,

    "Database"=>"seyoung",'CharacterSet'=>'UTF-8');  // 접근할 DB


$conn = sqlsrv_connect( $serverName, $connectionInfo);


if(isset($_POST['ID'])||isset($_POST['PASSWORD'])){
    
    $ID=$_POST['ID'];
    $PASSWORD=$_POST['PASSWORD'];
 
    
    if( $conn == false ){

    echo "Unable to connect.</br>";

    die( print_r( sqlsrv_errors(), true));

    }

    else

    {
        if($PASSWORD!='null'){
            $query = "SELECT * FROM LOGINUS WHERE USER_ID="."'".$ID."'AND PASSWORD="."'".$PASSWORD."'";
        }
        else if($PASSWORD=='null'){
            $query = "SELECT * FROM LOGINUS WHERE USER_ID="."'".$ID."'";
        }
        
      

 
// 쿼리를 실행하여 statement 를 얻어온다

    $stmt = sqlsrv_query($conn, $query);
 
     //빈 배열 생성

    $row=sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC);

    if ($row != null) {//트루를 읽으면 로그인 성공임. 중복확인에서 트루를 읽는다면 중복아이디임.
        echo "TRUE";
        exit;
     }
     
     //결과가 존재하지 않으면 로그인 실패
    else if($row == null){
        echo "FALSE";
        exit;
     }
 
   

    sqlsrv_free_stmt($stmt);

 

// 데이터베이스 접속을 해제한다

    sqlsrv_close($conn);


   }

    
}

//$drug_name=$_POST['Data'];
//$drug_name='타이레놀';



?>