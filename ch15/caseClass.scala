import Math.{E,PI}


abstract class Expr
  case class Var(name: String) extends Expr
  case class Number(num: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String,
left: Expr, right: Expr) extends Expr
//Classes with such a modifier are called case classes. Using the modifier makes the Scala compiler add some syntactic conveniences to your class.

//First, it adds a factory method with the name of the class.
val v = Var("x")
val aNum = Number(1.0)
val op = BinOp("+", Number(1), v)

//The second syntactic convenience is that all arguments in the parameter list of a case class implicitly get a val prefix, 
println(v.name)
println(aNum.num)

//Third, the compiler adds “natural” implementations of methods toString, hashCode, and equals to your class.
println(v)
println(aNum)
println(op)



def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e))  => e   // Double negation
    case BinOp("+", e, Number(0)) => e   // Adding zero
    case BinOp("*", e, Number(1)) => e   // Multiplying by one
    case _ => expr
}

//Variable or constant?
//Scala uses a simple lexical rule for disambiguation: a simple name starting with a lowercase letter is taken to be a pattern variable; all other references are taken to be constants.
val r1 = E match {
           case PI => "strange math? Pi = "+ PI
           case _ => "OK"
}
println(r1)
//pi is a variable pattern, it will match all inputs, and so no cases following it can be reached:
val pi = Math.PI
val r2 = E match {
			case pi => "strange math? Pi = "+ pi
			//case _ => "OK" //unreachable code
		}
println(r2)

//If you need to, you can still use a lowercase name for a pattern constant, using one of two tricks
//First, if the constant is a field of some object, you can prefix it with a qualifier. For instance, pi is a variable pattern, but this.pi or obj.pi are constants even though they start with lowercase letters
//you can alternatively enclose the variable name in back ticks.
val r3 = E match {
			case `pi` => "strange pi"
			case _ => "OK"
		}

println(r3)

//Constructors are where pattern matching becomes really powerful.




