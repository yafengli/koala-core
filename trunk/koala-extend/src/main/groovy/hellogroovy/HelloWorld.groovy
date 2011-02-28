package hellogroovy;

class HelloWorld {
    def name
    def age
    def address

    def sayHello() {
        println "@msg:${name},${age},${address}"
    }

    public static void main(String[] args) {
        def maps = [a: 'A', boolean: 'B', c: 'C']
        def hw = new HelloWorld(name: 'Fuck', age: 12, address: 'No.1')
        hw.sayHello()
        maps.each { key, value ->
            println "$key : $value"
        }
    }
}


