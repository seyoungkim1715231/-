<?php 

header("Content-Type:text/json; charset=UTF-8");

$serverName = "localhost,1433";  // 서버아이피



$uid ="medisook";    // sql 접속계정

$pwd = "1715231";  // 계정 비밀번호

$connectionInfo = array( "UID"=>$uid,

    "PWD"=>$pwd,

    "Database"=>"seyoung",'CharacterSet'=>'UTF-8');  // 접근할 DB


$conn = sqlsrv_connect( $serverName, $connectionInfo);


if(isset($_POST['TAG1'])||isset($_POST['TAG2'])||isset($_POST['TAG3'])||isset($_POST['DATE1'])){
    
    $id=$_POST['Id'];
    $drug_name=$_POST['DRUG_NAME'];
    $otc=$_POST['OTC'];
    $image=$_POST['IMAGE'];
    $tag1=$_POST['TAG1'];
    $tag2=$_POST['TAG2'];
    $tag3=$_POST['TAG3'];
    $goodbad=$_POST['GOODBAD'];
    $date1=$_POST['DATE1'];
    $date2=$_POST['DATE2'];
    
    if( $conn == false ){

    echo "Unable to connect.</br>";

    die( print_r( sqlsrv_errors(), true));

    }

    else

    {

    
        if(isset($_POST['TAG1'])&&!isset($_POST['TAG2'])&&!isset($_POST['TAG3'])&&!isset($_POST['DATE1'])){ //태그하나만
            $query = "INSERT INTO RECORD (USER_ID, DRUG_NAME, OTC, IMAGE, TAG1,GOODBAD) 
            VALUES(?,?,?,?,?,?)";          
            $params1=array($id,$drug_name,$otc,$image,$tag1,$goodbad);
        }


        else if(isset($_POST['TAG1'])&&isset($_POST['TAG2'])&&!isset($_POST['TAG3'])&&!isset($_POST['DATE1'])){ //태그두개
            $query = "INSERT INTO RECORD (USER_ID, DRUG_NAME, OTC, IMAGE, TAG1,TAG2,GOODBAD) 
            VALUES(?,?,?,?,?,?,?)";          
            $params1=array($id,$drug_name,$otc,$image,$tag1,$tag2,$goodbad);
        }

        else if(isset($_POST['TAG1'])&&isset($_POST['TAG2'])&&isset($_POST['TAG3'])&&!isset($_POST['DATE1'])){ //태그세개
            $query = "INSERT INTO RECORD (USER_ID, DRUG_NAME, OTC, IMAGE, GOODBAD,TAG1,TAG2,TAG3) 
            VALUES(?,?,?,?,?,?,?,?)";          
            $params1=array($id,$drug_name,$otc,$image,$goodbad,$tag1,$tag2,$tag3);
        }

        else if(!isset($_POST['TAG1'])&&!isset($_POST['TAG2'])&&!isset($_POST['TAG3'])&&isset($_POST['DATE1'])){ //태그없이 날짜만
            $query = "INSERT INTO RECORD (USER_ID, DRUG_NAME, OTC, IMAGE, GOODBAD,DATE1,DATE2) 
           VALUES(?,?,?,?,?,?,?)";         
            $params1=array($id,$drug_name,$otc,$image,$goodbad,$date1,$date2);
        }

        else if(isset($_POST['TAG1'])&&!isset($_POST['TAG2'])&&!isset($_POST['TAG3'])&&isset($_POST['DATE1'])){ //태그하나에 날짜
            $query = "INSERT INTO RECORD (USER_ID, DRUG_NAME, OTC, IMAGE, GOODBAD,TAG1,DATE1,DATE2) 
            VALUES(?,?,?,?,?,?,?,?)";         
            $params1=array($id,$drug_name,$otc,$image,$goodbad,$tag1,$date1,$date2);
        }

       else if(isset($_POST['TAG1'])&&isset($_POST['TAG2'])&&!isset($_POST['TAG3'])&&isset($_POST['DATE1'])){ //태그둘에 날짜
            $query = "INSERT INTO RECORD (USER_ID, DRUG_NAME, OTC, IMAGE, GOODBAD,TAG1,TAG2,DATE1,DATE2) 
             VALUES(?,?,?,?,?,?,?,?,?)";          
            $params1=array($id,$drug_name,$otc,$image,$goodbad,$tag1,$tag2,$date1,$date2);
        }

        else if(isset($_POST['TAG1'])&&isset($_POST['TAG2'])&&isset($_POST['TAG3'])&&isset($_POST['DATE1'])){ //태그셋에 날짜
            $query = "INSERT INTO RECORD (USER_ID, DRUG_NAME, OTC, IMAGE, GOODBAD,TAG1,TAG2,TAG3,DATE1,DATE2) 
             VALUES(?,?,?,?,?,?,?,?,?,?)";         
            $params1=array($id,$drug_name,$otc,$image,$goodbad,$tag1,$tag2,$tag3,$date1,$date2);
        }
        

      
      
 
// 쿼리를 실행하여 statement 를 얻어온다

        $stmt = sqlsrv_query($conn, $query,$params1);
        if( $stmt === false ) {
            die( print_r( sqlsrv_errors(), true));
        }
         sqlsrv_free_stmt($stmt);

         sqlsrv_close($conn);
    
    


   }

    
}


?>