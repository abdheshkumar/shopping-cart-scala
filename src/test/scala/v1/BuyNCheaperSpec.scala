package v1

import org.scalatest.{FlatSpec, Matchers}

class BuyNCheaperSpec extends FlatSpec with Matchers with ShoppingCartsFixture {


  val discountedApple = Product(apple.sku, s"Buy 2 Apples for £0.60, means a discount of £0.60", Price(-0.60))
  val discountedOrange = Product(orange.sku, s"Buy 3 Oranges for £0.50, means a discount of £0.25", Price(-0.25))

  val discountedOnApples = BuyNCheaper(apple, 2, Price(0.60))
  val discountedOnOrange = BuyNCheaper(orange, 3, Price(0.50))


  "The buy N cheaper" should "add a discounted offer for every 3 items, when N is 3" in {
    discountedOnOrange.find(Seq()) shouldBe Seq()
    discountedOnOrange.find(Seq(apple, orange)) shouldBe Seq()
    discountedOnOrange.find(Seq(apple, orange, orange, orange)) shouldBe Seq(discountedOrange)
    discountedOnOrange.find(Seq(apple, apple, orange, orange, orange, orange, orange, orange, orange)) shouldBe Seq(discountedOrange, discountedOrange)
  }

  it should "add an offer for every 2 items when N is 2, it means buy one, get one free" in {
    discountedOnApples.find(Seq()) shouldBe Seq()
    discountedOnApples.find(Seq(apple)) shouldBe Seq()
    discountedOnApples.find(Seq(apple, apple)) shouldBe Seq(discountedApple)
    discountedOnApples.find(Seq(apple, apple, orange, apple, apple)) shouldBe Seq(discountedApple, discountedApple)
  }

}