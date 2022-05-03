import fs2._

// Creation

// Pure is the effect type for pure computations
// Int is the result type
val s: Stream[Pure, Int] = Stream.empty
val s2: Stream[Pure, Int] = Stream(1, 2)
val s3 = Stream.emit(42)
val s4 = Stream.emits(Vector(1,2,3))
val s5 = Stream.emits(List(1,2,3))
val s6 = Stream.unfold(1)(i => if(i % 7 == 0) None else Some((i, i + 5)))

val nats = Stream.iterate(1)(_ + 1)
val answerToEverything = Stream.constant(42)
val range = Stream.range(1, 50)

// Exercise #1
def lettersIter: Stream[Pure, Char] =
  Stream
    .iterate('a')(c => (c + 1).toChar)
    .take(26)

// Exercise #2
def lettersUnfold: Stream[Pure, Char] =
  Stream
      .unfold('a')(c => if(c == 'z' + 1) None else Some((c, (c+1).toChar)))

// Exercise #3
def myIterate[A](initial: A)(next: A => A): Stream[Pure, A] = {
  Stream.unfold(initial)(a => Some((a, next(a))))
}

lettersIter.toList
lettersUnfold.toList
myIterate('a')(c => (c + 1).toChar)
  .take(26)
  .toList

