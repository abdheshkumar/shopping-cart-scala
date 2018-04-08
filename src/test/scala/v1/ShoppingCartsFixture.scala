package v1

trait ShoppingCartsFixture {
  val apple = Product("A","Apple", Price(0.60))
  val orange = Product("O","Orange", Price(0.25))
  val stock = Map("A" -> apple, "O" -> orange)
}