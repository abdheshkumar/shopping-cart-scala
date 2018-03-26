package shopping

class ShoppingBasket {

  val cart = Cart

  def prepareBill(items: List[Item]) = {
    items.foreach(cart.addItem)
    val (totalAmount, totalDiscount) = cart.totalBill
    println(f"Total:£${totalAmount}%2.2f, Discount:£$totalDiscount%2.2f ")
  }
}
