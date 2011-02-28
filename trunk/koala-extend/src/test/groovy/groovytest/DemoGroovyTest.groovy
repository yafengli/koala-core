package groovytest

import hellogroovy.HelloWorld
import hellogroovy.TestGroovy
import org.junit.Test

/**
 * Created by IntelliJ IDEA.
 * User: phoenixup
 * Date: 10-12-3
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
class DemoGroovyTest {
    @Test
    void testHello() {
        def hw = new HelloWorld(name: 'hello', age: 11, address: 'fuck')
        def tg = new TestGroovy(name: 'Test', age: 22, id: 'Groovy')
        hw.sayHello()
        tg.sayTest()
        def path1 = String.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        def path2 = Hello.class.getProtectionDomain().getCodeSource().getLocation().getFile()
        println "${path1},${path2}"
    }
}
