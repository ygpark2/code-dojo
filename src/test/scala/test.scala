
import org.scalatest.funsuite.AnyFunSuite
import com.slice.Solution
import scala.io.Source


class TestSolution extends AnyFunSuite {

    test("run") {
        
        val listOfLine = Source.fromResource("sample.txt").getLines()

         // List(" _ ", "| |", " _ ")
         // List(" _ ", "|_|", " _ ")
        // println(listOfLine)
        assert( Solution.getAllCharList(listOfLine)(0)(0) == (List(' ', ' ', ' '),0) )
        assert( Solution.getAllCharList(listOfLine)(0)(1) == (List(' ', '_', ' '),1) )
    }
}

