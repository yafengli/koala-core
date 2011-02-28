package hellogroovy

/**
 * Created by IntelliJ IDEA.
 * User: phoenixup
 * Date: 10-12-3
 * Time: 下午2:37
 * To change this template use File | Settings | File Templates.
 */
class TestGroovy {
    def name
    def age
    def id

    def sayTest() {
        println "${this.age},${this.name},${this.id}"
    }
}
