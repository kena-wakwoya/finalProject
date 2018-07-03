<?php  
    class dbConnect {  
        function __construct() {  
            $host = "127.0.0.1:3306";
            $user_name = "root";
            $user_password="";
            $db_name="sira"; 
            $conn = mysqli_connect($host, $user_name, $user_password, $db_name);
            mysqli_select_db(DB_DATABSE, $conn);  
            if(!$conn)// testing the connection  
            {  
                die ("Cannot connect to the database");  
            }
            return $conn;
        }
        public function Close(){
            mysqli_close();
        }
    }
?>