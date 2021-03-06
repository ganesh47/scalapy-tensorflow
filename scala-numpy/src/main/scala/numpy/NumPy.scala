package numpy

import jep.Jep
import py._

class NumPy(o: Object)(implicit jep: Jep) extends ObjectFascade(o) {
  def asarray[T](s: Seq[T])(implicit writer: ObjectWriter[T], reader: ObjectReader[T]): NDArray[T] = {
    dynamic.asarray(s).as[NDArray[T]]
  }

  def shape[T](s: NDArray[T])(implicit writer: ObjectWriter[T], reader: ObjectReader[T]): Seq[Int] = native

  def array[T](s: Seq[T])(implicit writer: ObjectWriter[T], reader: ObjectReader[T]): NDArray[T] = native

  def dot[T](a: NDArray[T], b: NDArray[T])(implicit writer: ObjectWriter[T], reader: ObjectReader[T]): NDArray[T] = native

  def vdot[T](a: NDArray[T], b: NDArray[T])(implicit writer: ObjectWriter[T], reader: ObjectReader[T]) = dynamic.vdot(a, b)

  def inner[T](a: NDArray[T], b: NDArray[T])(implicit writer: ObjectWriter[T], reader: ObjectReader[T]) :NDArray[T] = native
  def outer[T](a: NDArray[T], b: NDArray[T])(implicit writer: ObjectWriter[T], reader: ObjectReader[T]) :NDArray[T] = native
  def matmul[T](a: NDArray[T], b: NDArray[T])(implicit writer: ObjectWriter[T], reader: ObjectReader[T]) :NDArray[T] = native


  def resize[T](s: Seq[T], shape: (Int, Int))(implicit writer: ObjectWriter[T], reader: ObjectReader[T]): NDArray[NDArray[Double]] = {
    dynamic.resize(s, shape).as[NDArray[NDArray[Double]]]
  }

  def ones(size: Int): NDArray[Double] = {
    dynamic.ones(size).as[NDArray[Double]]
  }

  def random: NumPyRandom = dynamic.random.as[NumPyRandom]

  def nan: Object = dynamic.nan.as[Object]

  def float32: NumPyType = dynamic.float32.as[NumPyType]

  def clip[T](value: NDArray[T], low: NDArray[T], high: NDArray[T])(implicit writer: ObjectWriter[T], reader: ObjectReader[T]): NDArray[T] = {
    dynamic.clip(value, low, high).as[NDArray[T]]
  }
}
