package tensorflowtest

import jep.Jep
import numpy.NumPy
import org.scalatest.{FlatSpec, Matchers}
import py.py
import utils.EnvIndependentUtils._
import scalapy.tensorflow.TensorFlow

/**
  * Created by Ganesh on 4/5/2017.
  */
class TensorFlowFirstTest extends FlatSpec with Matchers {


  "201 step Linear Regression" should " optimially run with tensors  " in {
    implicit val jep:Jep=setup
    // prep for tensorflow
    val sys = py.module("sys")
    sys.argv = Array("jep")
    sys.path.insert(0, "./src/main/python")
    println(sys.version)
    val np = py.module("numpy").as[NumPy]
    val tf = py.module("tensorflow").as[TensorFlow]
    val xData = np.random.rand(100).astype(np.float32)
    val yData = (xData * 0.1) + 0.3

    val W = tf.Variable(tf.random_uniform(Seq(1), -1, 1))
    val b = tf.Variable(tf.zeros(Seq(1)))
    val y = (W * xData) + b

    val loss = tf.reduce_mean(tf.square(y - yData))
    val optimizer = tf.train.GradientDescentOptimizer(0.5)
    val train = optimizer.minimize(loss)

    val init = tf.global_variables_initializer()

    val sess = tf.Session()
    sess.run(init)

    (0 to 200).foreach { step =>
      sess.run(train)
      if (step % 20 == 0) {
        println(s"$step ${sess.run(W).head} ${sess.run(b).head}")
      }
    }
  }


}
