
<?php  
require_once 'initializeConnection.php';  
    class dbFunction {  
        public $username;
		public $emailid;
		public $password;
		public $lastName = "Kebede";
		public $sex = "male";
        function __construct($username, $emailid, $password) {  
            // connecting to database  
			$db = new dbConnect();
			$this->username = username;
			$this->emailid = emailid;
			$this->password = password;
        }  
        public function UserRegister(){  
                $password = md5($password);  
                $qr = mysqli_query("INSERT INTO applicants(firstName, lastName, email, sex, password)
				values('".$username."','".$lastName."','".$emailid."','".$sex."', '".$password."')") or die(mysql_error());
                return $qr;
				
        }  
    }  
?> 