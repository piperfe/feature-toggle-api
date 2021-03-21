package marsrover

class MarsRover() {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main() {
    println(MarsRover().greeting)
}
