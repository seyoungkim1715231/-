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


if(isset($_POST['Efcy1'])||isset($_POST['Except1'])||isset($_POST['Who1'])){
    
    $efcy1=$_POST['Efcy1'];
    $efcy2=$_POST['Efcy2'];
    $efcy3=$_POST['Efcy3'];
    $exc1=$_POST['Except1'];
    $exc2=$_POST['Except2'];
    $exc3=$_POST['Except3'];
    $who1=$_POST['Who1'];
    $who2=$_POST['Who2'];
    $who3=$_POST['Who3'];
   
    if($who1=='흡연 주의'){
        $who1='흡연';
    }
    else if($who2=='흡연 주의'){
        $who2='흡연';
    }
    else if($who3=='흡연 주의'){
        $who3='흡연';
    }

    if($who1=='음주 주의'){
        $who1='음주';
    }
    else if($who2=='음주 주의'){
        $who2='음주';
    }
    else if($who3=='음주 주의'){
        $who3='음주';
    }

    if($who1=='공복 주의'){
        $who1='공복';
    }
    else if($who2=='공복 주의'){
        $who2='공복';
    }
    else if($who3=='공복 주의'){
        $who3='공복';
    }

    if($who1=='졸음 주의'){
        $who1='졸음';
    }
    else if($who2=='졸음 주의'){
        $who2='졸음';
    }
    else if($who3=='졸음 주의'){
        $who3='졸음';
    }

    if($who1=='임부 주의'){
        $who1='임부';
    }
    else if($who2=='임부 주의'){
        $who2='임부';
    }
    else if($who3=='임부주의'){
        $who3='임부';
    }

    if($who1=='노인 주의'){
        $who1='노인';
    }
    else if($who2=='노인 주의'){
        $who2='노인';
    }
    else if($who3=='노인 주의'){
        $who3='노인';
    }

    if($who1=='어린이 주의'){
        $who1='소아';
    }
    else if($who2=='어린이 주의'){
        $who2='소아';
    }
    else if($who3=='어린이 주의'){
        $who3='소아';
    }

    




    
    if( $conn == false ){

    echo "Unable to connect.</br>";

    die( print_r( sqlsrv_errors(), true));

    }

    else

    {

        //$query = "SELECT* FROM TOTALINFO WHERE EFCY LIKE"."'%".$efcy1."%'";
        //$query = "SELECT* FROM TOTALINFO WHERE EFCY LIKE"."'%".$efcy1."%'"."OR EFCY LIKE"."'%".$efcy2."%'"."OR EFCY LIKE"."'%".$efcy3."%'";
        // 1. 증상만 들어올 경우. 
        // 2. 제외성분도 같이 들어올 경우 
        // 3. 대상만 같이 들어올 경우 
        // 4. 제외성분과 대상이 같이 들어올 경우 
    
        if($exc1=='슬라임'&&$who1=='슬라임'){ //증상민 들어올 경우 
            $query = "SELECT* FROM TOTALINFO WHERE EFCY LIKE"."'%".$efcy1."%'"."OR EFCY LIKE"."'%".$efcy2."%'"."OR EFCY LIKE"."'%".$efcy3."%'";
        
        }
        else if($exc1!='슬라임'&&$who1=='슬라임'){ //제외성분도 같이 들어올 경우 


            $query = "SELECT* FROM TOTALINFO WHERE (EFCY LIKE"."'%".$efcy1."%'"."OR EFCY LIKE"."'%".$efcy2."%'"."OR EFCY LIKE"."'%".$efcy3."%')"
            ."AND (MAIN_INGR NOT LIKE"."'%".$exc1."%'"."AND MAIN_INGR NOT LIKE"."'%".$exc2."%'"."AND MAIN_INGR NOT LIKE"."'%".$exc3."%')";
      
        }

        else if($exc1=='슬라임'&&$who1!='슬라임'){ //대상만 같이 들어올 경우 
          
            $query = "SELECT* FROM TOTALINFO WHERE (EFCY LIKE"."'%".$efcy1."%'"."OR EFCY LIKE"."'%".$efcy2."%'"."OR EFCY LIKE"."'%".$efcy3."%')"
            ."AND (WARNING NOT LIKE"."'%".$who1."%'"."AND WARNING NOT LIKE"."'%".$who2."%'"."AND WARNING NOT LIKE"."'%".$who3."%')";
     
        }

        else if($exc1!='슬라임'&&$who1!='슬라임'){ //제외성분, 대상 같이 들어올 경우 

            $query = "SELECT* FROM TOTALINFO WHERE (EFCY LIKE"."'%".$efcy1."%'"."OR EFCY LIKE"."'%".$efcy2."%'"."OR EFCY LIKE"."'%".$efcy3."%')"
            ."AND (MAIN_INGR NOT LIKE"."'%".$exc1."%'"."AND MAIN_INGR NOT LIKE"."'%".$exc2."%'"."AND MAIN_INGR NOT LIKE"."'%".$exc3."%')"
            ."AND (WARNING NOT LIKE"."'%".$who1."%'"."AND WARNING NOT LIKE"."'%".$who2."%'"."AND WARNING NOT LIKE"."'%".$who3."%')";
     
        }


    


      

 
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

