package v1

import org.scalatest.{FlatSpec, Matchers}

class CardCheckoutSpec extends FlatSpec with Matchers with ShoppingCartsFixture {
  val discountOnApples = BuyNCheaper(apple, 2, Price(0.60))
  val discountOnOranges = BuyNCheaper(orange, 3, Price(0.50))

  "A shopping card checkout, with no offers" should "add up the values of the items in a cart" in {
    val itemFinder = StockFinder(stock)
    val cardCheckout = CardCheckout(itemFinder)
    cardCheckout.calculatePayment("A") shouldBe Price(0.60)
    cardCheckout.calculatePayment("O") shouldBe Price(0.25)
    cardCheckout.calculatePayment("A", "O") shouldBe Price(0.85)
    cardCheckout.calculatePayment("A", "A", "O", "A") shouldBe Price(2.05)
  }

  "A shopping card checkout with two offers" should "add up the values of the items, including the discounts added by the offer calculator" in {
    val itemFinder = StockFinder(stock)
    val cardCheckout = CardCheckout(itemFinder, List(discountOnApples, discountOnOranges))
    cardCheckout.calculatePayment("A") shouldBe Price(0.60)
    cardCheckout.calculatePayment("O") shouldBe Price(0.25)
    cardCheckout.calculatePayment("A", "O") shouldBe Price(0.85)
    cardCheckout.calculatePayment("A", "A", "O", "A") shouldBe Price(1.45)
    cardCheckout.calculatePayment("A", "A", "O", "A", "A", "O") shouldBe Price(1.70)
    cardCheckout.calculatePayment("A", "A", "A", "O", "O", "A", "A", "A", "A", "O") shouldBe Price(2.90)
  }
}