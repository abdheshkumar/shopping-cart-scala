package shopping


object ShoppingApp {

  val items = List(
    Item("apple"), Item("apple"),
    Item("orange"), Item("apple"))

  items.groupBy(_.name)

  def main(args: Array[String]): Unit = {
    val shoppingOrders = new ShoppingBasket
    shoppingOrders.prepareBill(items)
  }

}
