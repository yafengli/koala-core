/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helloworld
class Person{
    def name
    def age
    def say(){
        printf("%s,%s,%$name,it's ok.\n",$name,$age)
    }
}

def person=new Person("name":"Fuck","age":18)
person.say()