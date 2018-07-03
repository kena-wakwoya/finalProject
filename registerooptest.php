<?php
require_once('register_oop.php');
use PHPUnit\Framework\TestCase;
class dbFunctionTest extends TestCase {   
    

    public function testregister():void{
        


       $moking = $this->getMockBuilder(dbFunction::class)
                    ->disableOriginalConstructor()
                    ->disableOriginalClone()
                    ->disableArgumentCloning()
                    ->disallowMockingUnknownTypes()
                    ->getMock();
        $moking->method('UserRegister')->willReturn(true);

              
        $this->assertEquals(true,$moking->UserRegister());


    }
	   
    
}



?>