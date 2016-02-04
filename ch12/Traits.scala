

//A trait encapsulates method and field definitions, which can be reused by mixing them into classes
//used in two ways: 1 widening thin interfaces to rich ones 2 define stackable modifications
trait Philosophical{
	def philosophize(){
		println("I consume memory")
	}
}

//Trait can be mixed in to a class using either the extends or with keywords
class Frog extends Philosophical {
	override def toString = "green"
}

val frog = new Frog
println(frog)
frog.philosophize()


//A trait also defines a type
val phil:Philosophical = frog
println(phil)
phil.philosophize()

//If you want to mix in multiple traits, you add more with clauses
class Animal

class Frog1 extends Animal with Philosophical{
	override def toString = "green"
	override def philosophize(){
		println("It aint easy being" + toString + "!" )
	}
}
val frog1 = new Frog1
println(frog1)
frog1.philosophize()
//Trait can do anything like Class with exceptions
//1 trait can not have parameters,like: trait Point(x:Int, y:Int)
//2 In trait super.toString is dynamically bound


//widening thin interfaces to rich ones
//In Java, methods in an interface must be implemented 
//In scala, traites can contain concrete methods
//simply define a trait with a small num- ber of abstract methods—the thin part of the trait’s interface—and a poten- tially large number of concrete methods, all implemented in terms of the abstract methods.


class Point(val x:Int, val y:Int)
trait Rectangular{
	def topLeft: Point
	def bottomRight: Point
	def left = topLeft.x
	def right = bottomRight.x
	def width = right - left
}

abstract class Component extends Rectangular {
}


class Rectangle(val topLeft:Point, val bottomRight:Point)
	extends Rectangular{
	
}


val rect = new Rectangle( new Point(1,1), new Point(10,10))
println(rect.left + ": " + rect.width)


// use Ordered trait to compare


//To trait or not to trait
//If the behavior will not be reused, then make it a concrete class
//If it might be reused in multiple, unrelated classes, make it a trait
//If you want to inherit from it in Java code, use an abstract class.
//If you plan to distribute it in compiled form, and you expect outside groups to write classes inheriting from it, you might lean towards using an abstract class. 
//If efficiency is very important, lean towards using a class.
//If you still do not know, after considering the above, then start by making it as a trait.
