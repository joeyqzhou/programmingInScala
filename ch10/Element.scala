package programmingInScala
import Element.elem
abstract class Element{
	def contents: Array[String]
	def height: Int = contents.length
	def width: Int = if (height == 0) 0 else contents(0).length 
	def widen(w:Int):Element =
		if (w<width) this
		else {
			val left = elem(' ',(w - width)/2,height)
			val right = elem(' ',w - width - left.width, height)
			left beside_approx this beside_approx right
		}
	def heighten(h:Int): Element =
		if( h<height) this
		else{
			val top = elem(' ', width, (h - height)/2)
			val bot = elem(' ', width, h - height - top.height)
			top above_approx this above_approx bot
		}

    def above_approx(that:Element):Element ={
        elem(this.contents ++ that.contents)
    }	
	def beside_approx(that:Element):Element ={
		 elem(
               for(
                    (line1,line2)<- this.contents zip that.contents
                ) yield line1 + line2
            )
	}
	def above(that:Element):Element ={
		val this1 = this widen that.width
		val that1 = that widen this.width
		elem(this1.contents ++ that1.contents)
	}
	def beside(that:Element):Element ={
		val this1 = this heighten that.height
		val that1 = that heighten this.height
		elem(
			   for(
                    (line1,line2)<- this1.contents zip that1.contents
                ) yield line1 + line2
			)
	    }
	override def toString = contents mkString "\n"
	def demo(){ println("can  be override") }
}


object Element {

		class ArrayElement(conts: Array[String]) extends Element{
			def contents: Array[String] = conts
		//when designing an inheritance hierarchy, you want to ensure that a member cannot be overridden by subclasses. In Scala, as in Java, you do this by adding a final modifier to the member
			final override def demo(){ println("can`t be override") }
		}

		//ensure an entire class not be subclassed
		//final class ArrayElement1 extends Element{} 

		//defining parametric fields
		//This is a shorthand that defines at the same time a parameter and filed with the same name
		class ArrayElement2(
			val contents:Array[String]
		) extends Element

		// LineElement needs to pass an argument to the primary constructor of its superclass. To invoke a super- class constructor, you simply place the argument or arguments you want to pass in parentheses following the name of the superclass
		//Scala requires such a modifier for all members that override a concrete member in a parent class. The modifier is optional if a member implements an abstract member with the same name
		class LineElement(s:String) extends ArrayElement(Array(s)){
			override def width = s.length
			override def height = 1
		}
		class UniformElement(
			ch: Char,
			override val width: Int,
			override val height: Int
		  ) extends Element {
			private val line = ch.toString * width
			def contents = Array.fill(height)(line)
		}

		def elem(contents: Array[String]): Element= new ArrayElement(contents)
		def elem(chr:Char, width:Int,height:Int):Element = new UniformElement(chr,width,height)
		def elem(line:String):Element = new LineElement(line)		
}



//It is possible to add modifiers such as private,protected, or override
class Cat{
	val dangerous = false
}
class Tiger(
	override val dangerous: Boolean,
	private var age: Int
)extends Cat
//Tiger's defintion is a shorthand for following class Tiger2
class Tiger2(para1:Boolean, para2: Int) extends Cat{
	override val dangerous = para1
	private var age = para2
}
//a variable of type Element could refer to an object of type ArrayElement. The name for this phenomenon is poly- morphism, which means “many shapes” or “many forms.” In this case, Element objects can have many forms.













