package v1

object Main extends App {

  val apple = Product("A", "Apple", Price(0.60))
  val orange = Product("O", "Orange", Price(0.25))

  val store = Map(apple.sku -> apple, orange.sku -> orange)
  val discountOnApples = BuyNCheaper(apple, 2, Price(0.60))
  val discountOnOranges = BuyNCheaper(orange, 3, Price(0.50))
  val priceCalculator = CardCheckout(StockFinder(store), List(discountOnApples, discountOnOranges))
  println(priceCalculator.calculatePayment("A", "O", "O", "A", "A").format)
}
