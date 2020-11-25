package testSuite;

import game.MasterMindTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import solvers.RandomSolverTest;
// by FYICenter.com

import answer.AnswerTest;



// specify a runner class: Suite.class
@RunWith(Suite.class)

// specify an array of test classes
@Suite.SuiteClasses({
  AnswerTest.class,
  MasterMindTest.class,
  RandomSolverTest.class,
  }
)

// the actual class is empty
public class AllTests {
}
